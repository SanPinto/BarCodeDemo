package com.barcode.reader.barcodereaderapp;

import android.content.Context;

import com.barcode.reader.barcodereaderapp.productDetail.model.Product;
import com.barcode.reader.barcodereaderapp.productDetail.ui.ViewCartFragment;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by COMPUTER on 8/13/2017.
 */

public class DataBaseHelper {

    private static DataBaseHelper mDbHelper;

    private DataBaseHelper() {

    }

    public static DataBaseHelper getInstance() {
        if (mDbHelper == null) {
            mDbHelper = new DataBaseHelper();
        }
        return mDbHelper;
    }

    public void initRealm(Context context) {
        // Initialize Realm
        Realm.init(context);
        // configure Realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }

    public Realm getRealmInstance() {
        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();
        return realm;
    }

    public boolean ifItemNotAdded(String id, Realm realm) {
        Product product = realm.where(Product.class).equalTo("id", id).findFirst();
        if (product == null) {
            return true;
        }
        return false;
    }

    public List<Product> getItemsFromDb() {
        Realm realm = getRealmInstance();
        List<Product> products = realm.where(Product.class).findAll();
        return products;
    }

    public void removeItem(Product product, ViewCartFragment listener, int pos) {
        Realm realm = getRealmInstance();
        RealmResults<Product> results = realm.where(Product.class).equalTo("id", product.id).findAll();
        realm.beginTransaction();
        results.remove(0);
        realm.commitTransaction();
        listener.onItemRemoved(pos);
    }
}
