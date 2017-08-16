package com.barcode.reader.barcodereaderapp;

import android.app.Application;

/**
 * Created by COMPUTER on 8/13/2017.
 */

public class BarCodeDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRealmConfiguration();

    }

    private void initRealmConfiguration() {
        DataBaseHelper.getInstance().initRealm(this);
    }
}
