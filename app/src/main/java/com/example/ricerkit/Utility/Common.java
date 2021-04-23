package com.example.ricerkit.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Common {

    public static boolean isConnectedToInternet (Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(context.CONNECTIVITY_SERVICE);

        if(connectivityManager!= null) {
            NetworkInfo [] info = connectivityManager.getAllNetworkInfo();
            if (info!= null) {

                for (int i=0; i<info.length; i++) {
                    if (info[1].getState() == NetworkInfo.State.CONNECTED)
                        return true;

                }
            }


        }



        return false;


    }

}
