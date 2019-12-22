package com.siva.client;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.HistoricalData;
import com.zerodhatech.models.Tick;
import com.zerodhatech.ticker.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TradeData {

    KiteTicker tickerProvider = null;
    public void getRealTimeTradeData(String AccessToken, String APIKey, final ArrayList<Long> tokens)
    {
        tickerProvider = new KiteTicker(AccessToken,APIKey);

        tickerProvider.setOnConnectedListener(new OnConnect() {
            @Override
            public void onConnected() {
                /** Subscribe ticks for token.
                 * By default, all tokens are subscribed for modeQuote.
                 * */
                tickerProvider.subscribe(tokens);
                tickerProvider.setMode(tokens, KiteTicker.modeFull);
            }
        });

        tickerProvider.setOnDisconnectedListener(new OnDisconnect() {
            @Override
            public void onDisconnected() {
                System.out.println("Socket disconnected");
            }
        });

        tickerProvider.setOnErrorListener(new OnError() {
            @Override
            public void onError(Exception exception) {
                //handle here.
            }

            @Override
            public void onError(KiteException kiteException) {
                //handle here.
            }

            @Override
            public void onError(String error) {
                System.out.println(error);
            }
        });

        tickerProvider.setOnTickerArrivalListener(new OnTicks() {
            @Override
            public void onTicks(ArrayList<Tick> ticks) {
                NumberFormat formatter = new DecimalFormat();
                System.out.println("ticks size "+ticks.size());
                if(ticks.size() > 0) {

                    System.out.println("open interest "+formatter.format(ticks.get(0).getOi()));
                    System.out.println("day Open "+formatter.format(ticks.get(0).getOpenPrice()));
                    System.out.println("day High  "+formatter.format(ticks.get(0).getHighPrice()));
                    System.out.println("day High  "+formatter.format(ticks.get(0).getLowPrice()));
                    System.out.println("last price "+ticks.get(0).getLastTradedPrice());
                    System.out.println("Volume traded "+ticks.get(0).getVolumeTradedToday());
                    System.out.println("change "+formatter.format(ticks.get(0).getChange()));
                    System.out.println(ticks.get(0).getMarketDepth().get("buy").size());
                }
            }
        });

        // Make sure this is called before calling connect.
        tickerProvider.setTryReconnection(true);

        try {
            //maximum retries and should be greater than 0
            tickerProvider.setMaximumRetries(10);

            //set maximum retry interval in seconds
            tickerProvider.setMaximumRetryInterval(30);
        } catch (KiteException e) {
            e.printStackTrace();
        }
        /** connects to com.zerodhatech.com.zerodhatech.ticker server for getting live quotes*/
        tickerProvider.connect();

        /** You can check, if websocket connection is open or not using the following method.*/
        boolean isConnected = tickerProvider.isConnectionOpen();
        System.out.println(isConnected);
    }

    public void getHistoricalData(KiteConnect kiteConnect) throws KiteException, IOException {
        /** Get historical data dump, requires from and to date, intrument token, interval, continuous (for expired F&O contracts), oi (open interest)
         * returns historical data object which will have list of historical data inside the object.*/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date from =  new Date();
        Date to = new Date();
        try {
            from = formatter.parse("2019-12-20 09:15:00");
            to = formatter.parse("2019-12-20 15:30:00");
        }catch (ParseException e) {
            e.printStackTrace();
        }
        //for SBI alone
        HistoricalData historicalData = kiteConnect.getHistoricalData(from, to, "779521", "15minute", false, true);
        System.out.println(historicalData.dataArrayList.size());
        System.out.println(historicalData.dataArrayList.get(0).volume);
        System.out.println(historicalData.dataArrayList.get(historicalData.dataArrayList.size() - 1).volume);
        System.out.println(historicalData.dataArrayList.get(0).oi);
    }

}
