package com.xpay.pay.sdk;

import android.app.Activity;

import com.switfpass.pay.MainApplication;
import com.switfpass.pay.activity.PayPlugin;
import com.switfpass.pay.bean.RequestMsg;

/**
 * Created by zma on 4/27/17.
 */

public class UnifiedAppPay {
    private static final PaymentApi paymentApi = new PaymentApi();

    public void pay(String appKey, String storeId, String totalFee, Activity main) {
        String token = paymentApi.unifiedOrder(appKey, storeId, "2", totalFee);
        RequestMsg msg = new RequestMsg();
        msg.setTokenId(token);
        msg.setTradeType(MainApplication.WX_APP_TYPE);
        msg.setAppId("WXSD");
        PayPlugin.unifiedAppPay(main, msg);
    }

}
