package com.barcode.reader.barcodereaderapp.productDetail.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sangeetha on 9/8/17.
 */

public class Product {
    public String id;
    @SerializedName("brand_name")
    public String brandName;
    public String type;
    public String color;
    public String size;
    public String price;
    public String model;
    public Slides slides;
}
