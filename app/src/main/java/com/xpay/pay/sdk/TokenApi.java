package com.xpay.pay.sdk;

import android.util.Log;

import com.xpay.pay.sdk.util.AppConfig;
import com.xpay.pay.sdk.util.HttpUtils;
import com.xpay.pay.sdk.util.LocalStorage;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zma on 4/26/17.
 */

public class TokenApi {
    private static final String TAG = TokenApi.class.getSimpleName();
    private static final LocalStorage localStorage = new LocalStorage("xpay.token");

    public String getToken(String appKey) {
        String token = localStorage.get(appKey);
        Log.d(TAG, "Load token result "+token);
        if(isTokenExpired(token)) {
            token = fetchToken(appKey);
            if(token!=null && token.trim().length()>0) {
                localStorage.set(appKey, token);
            }
        }
        return token;
    }

    private String fetchToken(String appKey) {
        String baseUrl = AppConfig.XPayConfig.getProperty("xpay.base.endpoint");
        String tokenUrl = AppConfig.XPayConfig.getProperty("xpay.token");
        int timeout = AppConfig.XPayConfig.getProperty("xpay.timeout", 3000);
        String path = baseUrl+"/"+tokenUrl+"/"+appKey;
        //String path = "http://10.0.2.2:8080/xpay/tokens/"+appKey;
        String content = HttpUtils.doGet(path, timeout);

        try {
            JSONObject jsonObj = new JSONObject(content);
            String token = jsonObj.optString("token");
            if(token!=null && token.trim().length()>0) {
                return token;
            }
         } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final long token_timeout = 24*60*60*1000L;
    private boolean isTokenExpired(String token) {
        if(token == null || token.trim().length()==0 || token.length()<50 || token.indexOf("-")>0) {
            return true;
        }
        try {
            long tokenTime = Long.valueOf(token.substring(10, 13));
            return System.currentTimeMillis() - tokenTime < token_timeout;
        } catch(Exception e) {
            return true;
        }
    }
}
