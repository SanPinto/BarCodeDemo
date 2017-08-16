package com.barcode.reader.barcodereaderapp.productDetail.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sangeetha on 9/8/17.
 */

public class Product extends RealmObject{
    @PrimaryKey
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
