package com.xpay.pay.sdk;

import android.util.Log;

import com.xpay.pay.sdk.util.AppConfig;
import com.xpay.pay.sdk.util.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zma on 4/26/17.
 */

public class PaymentApi {
    private static final String TAG = PaymentApi.class.getSimpleName();
    private static final TokenApi tokenApi = new TokenApi();

    public String unifedOrder(String appKey, String storeId, String channel, String totalFee) {
        String baseUrl = AppConfig.XPayConfig.getProperty("xpay.base.endpoint");
        String unifiedUrl = AppConfig.XPayConfig.getProperty("xpay.unifiedorder");
        int timeout = AppConfig.XPayConfig.getProperty("xpay.timeout", 3000);

        String path = baseUrl+unifiedUrl;
        //String path = "http://10.0.2.2:8080/xpay/rest/v1/pay/unifiedorder?";
        StringBuffer sb = new StringBuffer(path);
        sb.append("?");
        sb.append("storeId=").append(storeId);
        sb.append("&payChannel=").append(channel);
        sb.append("&totalFee=").append(totalFee);
        sb.append("&orderTime=20170420172715");
        sb.append("&ip=127.0.0.1");

        String token = tokenApi.getToken(appKey);
        Map<String, String> map = new HashMap<String, String>();
        map.put("Access_token", token);
        String content = HttpUtils.doPost(sb.toString(), null, map, timeout);
        Log.d(TAG, "UifedOrder result "+content);
        try {
            JSONObject jsonObj = new JSONObject(content);
            JSONObject data = jsonObj.getJSONObject("data");
            String result = null;
            if(data != null) {
                result = data.optString("codeUrl");
                Log.d(TAG, "result1 "+result);
                if(result == null || result.trim().length()==0) {
                    result = data.optString("tokenId");
                    Log.d(TAG, "result2 "+result);
                }
            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
