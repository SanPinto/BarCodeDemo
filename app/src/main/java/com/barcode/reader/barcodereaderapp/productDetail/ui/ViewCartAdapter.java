package com.barcode.reader.barcodereaderapp.productDetail.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.barcode.reader.barcodereaderapp.ApplicationUtils;
import com.barcode.reader.barcodereaderapp.DataBaseHelper;
import com.barcode.reader.barcodereaderapp.R;
import com.barcode.reader.barcodereaderapp.productDetail.Presenter.ProductPresenterContract;
import com.barcode.reader.barcodereaderapp.productDetail.model.Product;
import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

/**
 * Created by COMPUTER on 8/13/2017.
 */

public class ViewCartAdapter extends RecyclerView.Adapter<ViewCartAdapter.CartItemHolder> {

    private List<Product> mProducts;
    private Context mContext;
    private ProductPresenterContract.RemoveCartClickListner mClickListner;

    public ViewCartAdapter(Context context, ProductPresenterContract.RemoveCartClickListner listner) {
        mProducts = DataBaseHelper.getInstance().getItemsFromDb();
        mContext = context;
        mClickListner = listner;
    }

    @Override
    public CartItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardItemHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartItemHolder(cardItemHolder);
    }

    @Override
    public void onBindViewHolder(CartItemHolder holder, int position) {
        setCartDetails(holder, position);

    }

    private void setCartDetails(CartItemHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.removeBtn.setTag(position);
        holder.brandName.setText(mProducts.get(position).brandName);
        holder.seller.setText(mProducts.get(position).model);
        holder.price.setText(mContext.getString(R.string.rupee) + mProducts.get(position).price);
        Glide.with(mContext).load(mProducts.get(position).slides.slide1).into(holder.imageView);
        holder.sizeChart.setAdapter(new ArrayAdapter<String>(mContext, R.layout.size_spinner_item, Arrays.asList(mContext.getResources().getStringArray(R.array.size_chart))));
        holder.colorChart.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, ApplicationUtils.getProductList(mProducts.get(position).color)));
    }


    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class CartItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView seller, brandName, price;
        public ImageView imageView;
        public ImageButton removeBtn;
        public Spinner sizeChart, colorChart;

        public CartItemHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.product_image);
            brandName = (TextView) itemView.findViewById(R.id.brand_name);
            seller = (TextView) itemView.findViewById(R.id.seller);
            price = (TextView) itemView.findViewById(R.id.price);
            removeBtn = (ImageButton) itemView.findViewById(R.id.remove_cart);
            sizeChart = (Spinner) itemView.findViewById(R.id.size);
            colorChart = (Spinner) itemView.findViewById(R.id.color);
            removeBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int index = (int) view.getTag();
            if (mClickListner != null) {
                mClickListner.onRemoveFromCart(mProducts.get(index), index);
            }
        }
    }
}
