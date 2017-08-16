package com.barcode.reader.barcodereaderapp.productDetail.ui;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.barcode.reader.barcodereaderapp.R;
import com.barcode.reader.barcodereaderapp.productDetail.Presenter.ProductPresenterContract;
import com.barcode.reader.barcodereaderapp.views.TintableImageview;

import java.util.List;

/**
 * Created by COMPUTER on 8/14/2017.
 */

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ColorsHolder> {
    private ProductPresenterContract.OnItemClickListner mClickListner;
    private List<String> mColors;

    public ColorsAdapter(List<String> colorsList, ProductPresenterContract.OnItemClickListner listener) {
        mColors = colorsList;
        mClickListner = listener;
    }

    @Override
    public ColorsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View colorsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_item, parent, false);
        return new ColorsHolder(colorsView);
    }

    @Override
    public void onBindViewHolder(ColorsHolder holder, int position) {
     setColorCode(holder, mColors.get(position), position);
    }

    private void setColorCode(ColorsHolder holder, String colorCode, int position) {
        holder.itemView.setTag(position);
        StateListDrawable drawable = (StateListDrawable) holder.colorView.getBackground();
        drawable.setColorFilter(Color.parseColor(colorCode), PorterDuff.Mode.SRC_ATOP);
        Log.d("Colors", colorCode);
    }


    @Override
    public int getItemCount() {
        return mColors.size();
    }

    public class ColorsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView colorView;

        public ColorsHolder(View itemView) {
            super(itemView);
            colorView = (ImageView) itemView.findViewById(R.id.color);
            colorView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mClickListner != null) {
                int index = (int) view.getTag();
                mClickListner.onColorSelected(index);
            }
        }
    }
}
