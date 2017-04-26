package com.xpay.pay.sdk.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by zma on 4/26/17.
 */

public class IOUtils {
    public static final String toString(InputStream is, String encode) {
        StringBuffer total = new StringBuffer();
        String line;
        try {
            if(is == null) {
                Log.d(IOUtils.class.getSimpleName(), "inputstream is null");
            }
            BufferedReader r = new BufferedReader(new InputStreamReader(is, encode));
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total.toString();
    }

    public static final void close(InputStream is) {
        try {
            if(is!=null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final void close(HttpURLConnection connection) {
        if(connection!=null) {
            connection.disconnect();
        }
    }
}
