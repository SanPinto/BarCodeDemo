package com.barcode.reader.barcodereaderapp.productDetail.Presenter;


import com.barcode.reader.barcodereaderapp.productDetail.model.Product;

import java.util.List;

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

        void additemToCart(Product product);

        List<String> getProductList(String color);
    }

    public interface ProductView {


        void showProductDetails(List<Product> product);

        void onItemAdded();

        void onItemExists();
    }

    public interface OnItemClickListner {
        void onColorSelected(int index);
    }

    public interface RemoveCartClickListner {

        void onRemoveFromCart(Product product, int index);

        void onItemRemoved(int pos);

    }


}
