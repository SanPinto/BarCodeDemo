package com.barcode.reader.barcodereaderapp.productDetail.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.barcode.reader.barcodereaderapp.R;

/**
 * Created by COMPUTER on 8/13/2017.
 */

public  class CartDetailActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_detail);
        addCartDetailFragment();
    }

    private void addCartDetailFragment() {
        ViewCartFragment fragment = new ViewCartFragment();
            try {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.cart_detail_holder, fragment);
                transaction.commitAllowingStateLoss();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
    }


}
