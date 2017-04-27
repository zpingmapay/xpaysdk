package com.xpay.pay.sdk;
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.widget.TextView;

        import com.switfpass.pay.MainApplication;
        import com.switfpass.pay.activity.PayPlugin;
        import com.switfpass.pay.bean.RequestMsg;
        import com.xpay.pay.sdk.util.AppConfig;

public class MainActivity extends Activity implements MyResultReceiver.Receiver{
    public MyResultReceiver mReceiver;
    private static final PaymentApi paymentApi = new PaymentApi();
    public static Activity activity;

    public void onCreate(Bundle savedInstanceState) {
        mReceiver = new MyResultReceiver(new Handler());
        mReceiver.setReceiver(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        final Intent intent = new Intent(Intent.ACTION_SYNC, null, this, MyIntentService.class);
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("channel", "2");
        intent.putExtra("total_fee", "0.01");
        startService(intent);

    }


    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        String result = resultData.getString("result");
        TextView mTextView = (TextView)findViewById(R.id.text);
        mTextView.setText(result);
    }

}