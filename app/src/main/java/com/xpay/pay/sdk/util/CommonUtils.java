package com.xpay.pay.sdk.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by zma on 4/26/17.
 */

public class CommonUtils {
    public static final Context getApplicationContext() {
        try {
            Application application = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null, (Object[]) null);
            return application.getApplicationContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
