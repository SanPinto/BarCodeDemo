package com.barcode.reader.barcodereaderapp.productDetail.Presenter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.barcode.reader.barcodereaderapp.DataBaseHelper;
import com.barcode.reader.barcodereaderapp.R;
import com.barcode.reader.barcodereaderapp.productDetail.interpreter.ProductDetailInterpreter;
import com.barcode.reader.barcodereaderapp.productDetail.model.Product;
import com.barcode.reader.barcodereaderapp.productDetail.model.ProductDetail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;

/**
 * Created by sangeetha on 10/8/17.
 */

public class ProductPresenterImpl implements ProductPresenterContract.ProductPresenter, ProductPresenterContract.RestAPICallbackListner {


    private ProductPresenterContract.ProductView mView;

    @Override
    public void onCreate(ProductPresenterContract.ProductView view) {
        mView = view;

    }

    @Override
    public void getProductDetails(String url) {
        ProductDetailInterpreter productDetailInterpreter = new ProductDetailInterpreter();
        productDetailInterpreter.downloadProductdetails(url, this);
    }

    @Override
    public void additemToCart(Product product) {
        if (product != null) {
            Realm realm = DataBaseHelper.getInstance().getRealmInstance();
            if (realm != null && DataBaseHelper.getInstance().ifItemNotAdded(product.id, realm)) {
                realm.beginTransaction();
                realm.copyToRealm(product);
                realm.commitTransaction();
                mView.onItemAdded();
            } else {
                mView.onItemExists();
            }

        }
    }

    @Override
    public List<String> getProductList(String color) {
        String[] colorsArray = color.split(",");
        if(colorsArray.length > 0)
        return Arrays.asList(colorsArray);
        else {
            List<String> colors = new ArrayList<>();
            colors.add(color);
            return colors;
        }

    }


    @Override
    public void onResponseError(String Error) {
        Log.d("Product", Error);

    }

    @Override
    public <T> void onResponseSuccess(T response) {
        ProductDetail productDetail = (ProductDetail) response;
        mView.showProductDetails(productDetail.product);

    }


}
