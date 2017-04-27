package com.xpay.pay.sdk.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zma on 4/27/17.
 */

public class AppConfig {
    private Properties properties;
    public static final AppConfig XPayConfig = new AppConfig(load("xpay.properties"));

    public AppConfig(Properties properties) {
        this.properties = properties;
    }

    private static Properties load(String propFileName) {
        Properties props = new Properties();
        try {
            props.load(CommonUtils.getApplicationContext().getAssets().open(propFileName));
        } catch (Exception e) {
            try {
                InputStream is = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream(propFileName);
                props.load(is);
            } catch (IOException e1) {
               e.printStackTrace();
            }
        }
        return props;
    }

    public String getProperty(String key) {
        return properties.getProperty(key, "");
    }

    public int getProperty(String key, int defaultValue) {
        String val = properties.getProperty(key, "");
        if ((val!=null)) {
            return Integer.valueOf(val);
        }
        return defaultValue;
    }

    public boolean getProperty(String key, boolean defaultValue) {
        String val = properties.getProperty(key, "");
        if ((val!=null)) {
            return Boolean.valueOf(val);
        }
        return defaultValue;
    }
}
