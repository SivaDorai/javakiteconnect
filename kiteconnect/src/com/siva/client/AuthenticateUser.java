package com.siva.client;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;

import java.io.IOException;

public class AuthenticateUser {

    private String APIKey = "lwdbg2r7d8up72g7";
    private String APISecret = "4nnjii6rkgyfqfh3u92c7s17n1smo6zx";
    public KiteConnect initKiteSDK()
    {
        KiteConnect kiteSdk = new KiteConnect(APIKey);
        kiteSdk.setUserId("HD7592");
        return kiteSdk;
    }

    public KiteConnect activateSession(String reqToken, KiteConnect kitesdk)  {

        User user = null;
        try {
            user = kitesdk.generateSession(reqToken,APISecret);
        } catch (KiteException e) {
            System.out.println("Invalid request token, generate new token manually");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("SDK activated " + user.accessToken);
        kitesdk.setAccessToken(user.accessToken);

        return  kitesdk;
    }
}
