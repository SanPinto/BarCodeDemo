package com.barcode.reader.barcodereaderapp.productDetail.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.barcode.reader.barcodereaderapp.R;
import com.barcode.reader.barcodereaderapp.productDetail.Presenter.ProductPresenterContract;
import com.barcode.reader.barcodereaderapp.productDetail.Presenter.ProductPresenterImpl;
import com.barcode.reader.barcodereaderapp.productDetail.model.Product;
import com.barcode.reader.barcodereaderapp.productDetail.ui.adapter.SlidingPagerAdapter;
import com.barcode.reader.barcodereaderapp.views.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sangeetha on 9/8/17.
 */

public class ProductDetailActivity extends AppCompatActivity implements ProductPresenterContract.ProductView, CirclePageIndicator.RefreshCalledListener {

    private ViewPager mViewPager;
    private TextView mBrandName, mModel, mPrice, mColor;
    private TextView mSSize, mMSize, mLSize, mXLSize, mXLLSize;
    private ProductPresenterImpl mPresenter;
    private PagerAdapter mPagerAdapter;
    private List<String> mSlides = new ArrayList<>();
    private ProgressBar mLoader;
    private CirclePageIndicator mCirclePageIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        initViews();
        initPresenter();
        getProductDetail();

    }

    private void initPresenter() {
        mPresenter = new ProductPresenterImpl();
        mPresenter.onCreate(this);
    }

    private void getProductDetail() {
        mPresenter.getProductDetails(getString(R.string.product_url));
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mBrandName = (TextView) findViewById(R.id.brand_name);
        mModel = (TextView) findViewById(R.id.model);
        mPrice = (TextView) findViewById(R.id.price);
        mColor = (TextView) findViewById(R.id.color);
        mLoader = (ProgressBar) findViewById(R.id.loader);
        mSSize = (TextView) findViewById(R.id.small_size);
        mMSize = (TextView) findViewById(R.id.medium_size);
        mLSize = (TextView) findViewById(R.id.large_size);
        mXLSize = (TextView) findViewById(R.id.xlarge_size);
        mXLLSize = (TextView) findViewById(R.id.xxlarge_size);
        mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.circle_page_indicator);
        mCirclePageIndicator.setIsRefreshCalledListener(this);
        mPagerAdapter = new SlidingPagerAdapter(getSupportFragmentManager(), mSlides);
        mViewPager.setAdapter(mPagerAdapter);
        mCirclePageIndicator.setViewPager(mViewPager);

    }


    @Override
    public void showProductDetails(List<Product> product) {
        if (product != null) {
            mSlides.add(product.get(0).slides.slide1);
            mSlides.add(product.get(0).slides.slide2);
            mSlides.add(product.get(0).slides.slide3);
            updateAdapter();
            setProductInfo(product.get(0));
            hideLoader();
        }

    }

    private void hideLoader() {
        if (mLoader != null) {
            mLoader.setVisibility(View.GONE);
        }
    }

    private void setProductInfo(Product product) {
        mBrandName.setText(product.brandName);
        mColor.setText(product.color);
        mModel.setText(product.model);
        mPrice.setText(product.price);
        setProductSize(product.size);
    }

    private void setProductSize(String productSize) {
        setDefaultSizes();
        switch (productSize) {
            case "S":
                setText(mSSize, productSize);
                break;

            case "M":
                setText(mMSize, productSize);
                break;

            case "L":
                setText(mLSize, productSize);
                break;

            case "XL":
                setText(mXLSize, productSize);
                break;

            case "XXL":
                setText(mXLLSize, productSize);
                break;
        }
    }

    private void setDefaultSizes() {
        mSSize.setText(R.string.small);
        mMSize.setText(R.string.medium);
        mLSize.setText(R.string.large);
        mXLSize.setText(R.string.x_large);
        mXLLSize.setText(R.string.xll_large);
    }

    private void setText(TextView textView, String size) {
        textView.setText(size);
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

    }

    private void updateAdapter() {
        if (mPagerAdapter != null) {
            mPagerAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onRefreshcalled() {
        return false;
    }
}
