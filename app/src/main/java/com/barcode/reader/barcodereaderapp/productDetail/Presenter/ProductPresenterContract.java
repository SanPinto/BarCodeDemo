package com.barcode.reader.barcodereaderapp.productDetail.Presenter;


import com.barcode.reader.barcodereaderapp.productDetail.model.Product;

import java.util.List;

import retrofit2.Response;

/**
 * Created by sangeetha on 9/8/17.
 */

public class ProductPresenterContract {


    public interface RestAPICallbackListner {

        void onResponseError(String Error);

        <T> void onResponseSuccess(T response);
    }

    public interface ProductPresenter {

        void onCreate(ProductView view);

        void getProductDetails(String url);
    }

    public interface ProductView {


        void showProductDetails(List<Product> product);
    }
}
