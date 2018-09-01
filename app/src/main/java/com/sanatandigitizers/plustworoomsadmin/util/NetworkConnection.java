package com.sanatandigitizers.plustworoomsadmin.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkConnection {
        private static NetworkInfo wifiInfo,mobileInfo;
        public static boolean isConnected(Context context)
        {
            ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            try {
                wifiInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                mobileInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            if(wifiInfo!=null && wifiInfo.isAvailable()&& wifiInfo.isConnected())
            {
                return true;
            }
            if (mobileInfo!=null && mobileInfo.isAvailable()&& mobileInfo.isConnected())
            {
                return true;
            }
            else {
                return false;
            }
        }


}
