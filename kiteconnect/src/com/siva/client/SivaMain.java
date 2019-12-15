package com.siva.client;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Margin;
import com.zerodhatech.models.Profile;
import com.zerodhatech.models.TokenSet;
import com.zerodhatech.models.User;

import java.io.IOException;

public class SivaMain {

    public static void main (String[] args)
    {
        KiteConnect kiteSdk = new KiteConnect("lwdbg2r7d8up72g7");
        String accTkn = "wX1ML4zQnb56JoyT2rZeoiLTI5k8ohfv";
        kiteSdk.setUserId("HD7592");
        try {
            //User user = invokeKite(kiteSdk);
            //kiteSdk.setAccessToken(user.accessToken);
            kiteSdk.setAccessToken(accTkn);
            Profile profile = kiteSdk.getProfile();
            System.out.println(profile.email);
            Margin margin = kiteSdk.getMargins("CASH");
            System.out.println("Available margin is " + margin.available.cash);
        } catch (KiteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User invokeKite(KiteConnect kiteSdk) throws KiteException, IOException {

        User user = kiteSdk.generateSession("JyH8vL301lhMYQPdnz8Hqlk6Vp02JVRz","4nnjii6rkgyfqfh3u92c7s17n1smo6zx");
        return user;




    }

}