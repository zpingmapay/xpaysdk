package com.xpay.pay.sdk;

import android.util.Log;

import com.xpay.pay.sdk.util.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zma on 4/26/17.
 */

public class TokenApi {
    private static final String TAG = TokenApi.class.getSimpleName();

    public String getToken(String appKey) {
        String path = "http://10.0.2.2:8080/xpay/tokens/"+appKey;
        String content = HttpUtils.doGet(path, 3000);
        Log.d(TAG, "Get token result "+content);
        try {
            JSONObject jsonObj = new JSONObject(content);
            return jsonObj.optString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
