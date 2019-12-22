package com.siva.client;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.ticker.KiteTicker;

import java.io.IOException;
import java.util.ArrayList;


public class SivaMain {

    public static void main (String[] args)
    {
        String APIKey = "lwdbg2r7d8up72g7";
        AuthenticateUser au = new AuthenticateUser();
        KiteConnect kiteSdk = au.initKiteSDK();
        //kiteSdk = au.activateSession("oumE7iXm6PlmYc0w9OYLhxc7XlG5e9cw", kiteSdk);
        String accTkn =   "WKRnlyw4tpMpbvYo4AbvAmndaREk7UGq";
        //String accTkn = kiteSdk.getAccessToken(); //day access token to be renewed every day
        System.out.println("Session activated with valid Access token " + accTkn);
//         Code to load instruments
//        LoadInstruments li = new LoadInstruments();
//        li.getInstrumentList(kiteSdk);
        kiteSdk.setAccessToken(accTkn);
        // Code to trigger Real time data for SBI
        ArrayList<Long> instrumentTokens = new ArrayList<Long>();
        instrumentTokens.add(Long.valueOf(779521));
        TradeData tick = new TradeData();
        //activate web socket to get live feed
        //tick.getRealTimeTradeData(accTkn, APIKey,instrumentTokens);


        //code to load historical data
        try {
            tick.getHistoricalData(kiteSdk);
        } catch (KiteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}