package com.barcode.reader.barcodereaderapp.productDetail.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.barcode.reader.barcodereaderapp.DataBaseHelper;
import com.barcode.reader.barcodereaderapp.R;
import com.barcode.reader.barcodereaderapp.productDetail.Presenter.ProductPresenterContract;
import com.barcode.reader.barcodereaderapp.productDetail.model.Product;

/**
 * Created by COMPUTER on 8/13/2017.
 */

public class ViewCartFragment extends Fragment implements ProductPresenterContract.RemoveCartClickListner {

    private RecyclerView mRecyclerView;
    private Button mPlaceOrder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_cart, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mPlaceOrder = (Button) view.findViewById(R.id.place_order);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setAdapter();
    }

    private void setAdapter() {
        mRecyclerView.setAdapter(new ViewCartAdapter(getActivity(), this));
    }

    @Override
    public void onRemoveFromCart(Product product, int index) {
        DataBaseHelper.getInstance().removeItem(product, this, index);
    }

    @Override
    public void onItemRemoved(int pos) {
        Toast.makeText(getActivity(), "Item Removed From Cart Successfully", Toast.LENGTH_SHORT).show();
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
