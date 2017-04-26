package com.xpay.pay.sdk;
        import android.app.IntentService;
        import android.content.Intent;
        import android.util.Log;
        import android.os.Bundle;
        import android.os.ResultReceiver;

        import com.xpay.pay.sdk.util.HttpUtils;

public class MyIntentService extends IntentService {
    private static final String TAG = MyIntentService.class.getSimpleName();
    private static final TokenApi tokenApi = new TokenApi();
    private static final PaymentApi paymentApi = new PaymentApi();

    public MyIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent() ");
        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String appKey = intent.getStringExtra("app_key");
        String storeId = intent.getStringExtra("store_id");
        String channel = intent.getStringExtra("channel");
        String totalFee = intent.getStringExtra("total_fee");
        String result = paymentApi.unifedOrder(appKey, storeId, channel, totalFee);
        Bundle b = new Bundle();
        b.putString("result", result);
        try {
            receiver.send(0, b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
