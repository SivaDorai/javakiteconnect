package com.siva.client;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Instrument;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

public class LoadInstruments {
    public void getInstrumentList(KiteConnect kiteSDK)
    {
        List<Instrument> instrumentList = null;
        try {
            instrumentList = kiteSDK.getInstruments("NSE");
        } catch (KiteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ListIterator<Instrument> insList = instrumentList.listIterator();
        while (insList.hasNext())
        {
            Instrument instrument = insList.next();

            System.out.println("Trading symbol of "  + instrument.name + " is " + instrument.tradingsymbol + " with instrument token " + instrument.getInstrument_token() + "with type " + instrument.instrument_type);
        }
    }
}
