package com.siva.client;

import com.zerodhatech.kiteconnect.KiteConnect;


public class SivaMain {

    public static void main (String[] args)
    {
        //0rEl8mpHy03k95cjkWa8mcNFGKTvgZUB
        AuthenticateUser au = new AuthenticateUser();
        KiteConnect kiteSdk = au.initKiteSDK();
//        kiteSdk = au.activateSession("0rEl8mpHy03k95cjkWa8mcNFGKTvgZUB", kiteSdk);
          String accTkn =   "0rEl8mpHy03k95cjkWa8mcNFGKTvgZUB";
        //String accTkn = kiteSdk.getAccessToken(); //day access token to be renewed every day
        System.out.println("Session activated with valid Access token " + accTkn);
        LoadInstruments li = new LoadInstruments();
        li.getInstrumentList(kiteSdk);
    }


}