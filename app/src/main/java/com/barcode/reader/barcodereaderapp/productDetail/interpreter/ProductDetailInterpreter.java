package com.barcode.reader.barcodereaderapp.productDetail.interpreter;

import com.barcode.reader.barcodereaderapp.productDetail.Presenter.ProductPresenterContract;
import com.barcode.reader.barcodereaderapp.productDetail.model.Product;
import com.barcode.reader.barcodereaderapp.productDetail.model.ProductDetail;
import com.barcode.reader.barcodereaderapp.rest.ApiClientUtil;
import com.barcode.reader.barcodereaderapp.rest.BaseApiInterface;
import com.barcode.reader.barcodereaderapp.rest.RestApiResponseCallback;

import retrofit2.Call;

/**
 * Created by sangeetha on 10/8/17.
 */

public class ProductDetailInterpreter {

    public void downloadProductdetails(String url, ProductPresenterContract.RestAPICallbackListner listner) {
        BaseApiInterface apiInterface = ApiClientUtil.getInstance().getApiClient().create(BaseApiInterface.class);
        Call<ProductDetail> productDetailCall = apiInterface.getProductDetails(url);
        productDetailCall.enqueue(new RestApiResponseCallback<ProductDetail>(listner));
    }
}
