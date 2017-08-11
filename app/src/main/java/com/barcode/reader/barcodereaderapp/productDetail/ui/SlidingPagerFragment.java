package com.barcode.reader.barcodereaderapp.productDetail.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.barcode.reader.barcodereaderapp.R;
import com.bumptech.glide.Glide;

/**
 * Created by sangeetha on 10/8/17.
 */

public class SlidingPagerFragment extends Fragment {

    private String mImageUrl;
    private ImageView mImageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extactArguments();
    }

    private void extactArguments() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mImageUrl = bundle.getString("IMG_URL");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_slider, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mImageView = (ImageView) view.findViewById(R.id.sliding_img);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showSlidingImage();

    }

    private void showSlidingImage() {
        Glide.with(getActivity())
                .load(mImageUrl)
                .into(mImageView);
    }

    public static SlidingPagerFragment getInstance(String imageUrl) {
        SlidingPagerFragment fragment = new SlidingPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("IMG_URL", imageUrl);
        fragment.setArguments(bundle);
        return fragment;
    }
}
