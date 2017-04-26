package com.xpay.pay.sdk.util;

import android.util.Log;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.util.Map;

/**
 * Created by zma on 4/26/17.
 */

public class HttpUtils {
    private static final String TAG = HttpUtils.class.getSimpleName();

    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String doGet(String path, int timeout) {
        return doGet(path, null, timeout);
    }

    public static final String doGet(String path, Map<String, String> headers, int timeout) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(path);
            Log.d(TAG, "doGet() "+path);
            if (url != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(timeout);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("GET");
                if(headers!=null) {
                    for(String key : headers.keySet()) {
                        httpURLConnection.setRequestProperty(key, headers.get(key));
                    }
                }

                int responseCode=httpURLConnection.getResponseCode();
                if(responseCode==200)
                {
                    inputStream=httpURLConnection.getInputStream();
                }
                return IOUtils.toString(inputStream, DEFAULT_ENCODING);
            }

        } catch (Exception e) {
            Log.d(TAG, "failed to do get ", e);
        }  finally {
            IOUtils.close(inputStream);
            IOUtils.close(httpURLConnection);
        }
        return "";
    }

    public static final String doPost(String path, String body, int timeout) {
        return doPost(path, body, null, timeout);
    }

    public static final String doPost(String path, String body, Map<String, String> headers, int timeout) {
        try {
            Log.d(TAG, "doPost() "+path);
            URL url = new URL(path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(timeout);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            int len = 0;
            byte[] mydata = null;
            if(body!=null && body.trim().length()>0) {
                mydata = body.getBytes();
                len = mydata.length;
            }

            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Content-Length", String.valueOf(len));
            if(headers!=null) {
                for(String key : headers.keySet()) {
                    urlConnection.setRequestProperty(key, headers.get(key));
                }
            }

            urlConnection.setRequestMethod("POST");
            OutputStream outputStream = urlConnection.getOutputStream();
            if(len>0) {
                outputStream.write(mydata);
            }
            int responseCode = urlConnection.getResponseCode();
            Log.d(TAG, "response code = "+responseCode);
            if (responseCode == 200) {
                return IOUtils.toString(urlConnection.getInputStream(), DEFAULT_ENCODING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}











