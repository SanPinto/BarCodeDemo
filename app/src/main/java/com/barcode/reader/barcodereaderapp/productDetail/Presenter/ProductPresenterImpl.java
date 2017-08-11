package com.barcode.reader.barcodereaderapp.productDetail.Presenter;

import android.util.Log;
import android.widget.Toast;

import com.barcode.reader.barcodereaderapp.productDetail.interpreter.ProductDetailInterpreter;
import com.barcode.reader.barcodereaderapp.productDetail.model.Product;
import com.barcode.reader.barcodereaderapp.productDetail.model.ProductDetail;

import retrofit2.Response;

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
    public void onResponseError(String Error) {
        Log.d("Product", Error);

    }

    @Override
    public <T> void onResponseSuccess(T response) {
        ProductDetail productDetail = (ProductDetail) response;
        mView.showProductDetails(productDetail.product);

    }



}
