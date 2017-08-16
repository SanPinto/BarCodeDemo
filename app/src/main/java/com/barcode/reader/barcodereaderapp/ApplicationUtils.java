package com.barcode.reader.barcodereaderapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sangeetha on 16/8/17.
 */

public class ApplicationUtils {

    public static List<String> getProductList(String color) {
        String[] colorsArray = color.split(",");
        if(colorsArray.length > 0)
            return Arrays.asList(colorsArray);
        else {
            List<String> colors = new ArrayList<>();
            colors.add(color);
            return colors;
        }

    }
}
