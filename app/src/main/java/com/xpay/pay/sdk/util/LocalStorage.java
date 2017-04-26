package com.xpay.pay.sdk.util;

import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zma on 4/26/17.
 */

public class LocalStorage {
    private String name;
    private SharedPreferences sp;

    public LocalStorage(String name) {
        this.name = name;
        sp = CommonUtils.getApplicationContext().getSharedPreferences(name, MODE_PRIVATE);
    }

    public String get(String key) {
        return sp.getString(key, null);
    }

    public void set(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
