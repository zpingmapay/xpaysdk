package com.xpay.pay.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.xpay.pay.sdk.util.CommonUtils;
import com.xpay.pay.sdk.util.HttpUtils;
import com.xpay.pay.sdk.util.LocalStorage;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zma on 4/26/17.
 */

public class TokenApi {
    private static final String TAG = TokenApi.class.getSimpleName();
    private static final LocalStorage localStorage = new LocalStorage("xpay.token");

    public String getToken(String appKey) {
        String token = localStorage.get(appKey);
        Log.d(TAG, "Load token result "+token);
        if(token == null || token.trim().length() == 0) {
            token = fetchToken(appKey);
            if(token!=null && token.trim().length()>0) {
                localStorage.set(appKey, token);
            }
        }
        return token;
    }

    public String fetchToken(String appKey) {
        String path = "http://10.0.2.2:8080/xpay/tokens/"+appKey;
        String content = HttpUtils.doGet(path, 3000);

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
}
