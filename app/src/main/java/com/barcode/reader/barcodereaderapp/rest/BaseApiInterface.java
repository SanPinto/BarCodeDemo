package com.barcode.reader.barcodereaderapp.rest;

import com.barcode.reader.barcodereaderapp.productDetail.model.Product;
import com.barcode.reader.barcodereaderapp.productDetail.model.ProductDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by sangeetha on 9/8/17.
 */

public interface BaseApiInterface {
    @GET
    Call<ProductDetail> getProductDetails(@Url String url);
}
