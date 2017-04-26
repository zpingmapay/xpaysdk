package com.xpay.pay.sdk;
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.widget.TextView;

public class MainActivity extends Activity implements MyResultReceiver.Receiver{
    public MyResultReceiver mReceiver;


    public void onCreate(Bundle savedInstanceState) {
        mReceiver = new MyResultReceiver(new Handler());
        mReceiver.setReceiver(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(Intent.ACTION_SYNC, null, this, MyIntentService.class);
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("store_id", "T20170412163933306");
        intent.putExtra("channel", "2");
        intent.putExtra("total_fee", "0.01");
        intent.putExtra("app_key", "2dbfa7ce-4af4-4b3e-abed-929fbaa4a2f1");
        startService(intent);
    }


    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        String result = resultData.getString("result");
        TextView mTextView = (TextView)findViewById(R.id.text);
        mTextView.setText(result);
    }
}