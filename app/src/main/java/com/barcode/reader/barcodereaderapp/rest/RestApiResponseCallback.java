package com.barcode.reader.barcodereaderapp.rest;

import com.barcode.reader.barcodereaderapp.productDetail.Presenter.ProductPresenterContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sangeetha on 9/8/17.
 */

public class RestApiResponseCallback<T> implements Callback<T> {
    private ProductPresenterContract.RestAPICallbackListner mCallback;

    public RestApiResponseCallback(ProductPresenterContract.RestAPICallbackListner listner) {
        mCallback = listner;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response != null) {
            mCallback.onResponseSuccess(response.body());

        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t != null) {

            mCallback.onResponseError(t.getMessage());
        }

    }
}
