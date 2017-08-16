package com.barcode.reader.barcodereaderapp.productDetail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sangeetha on 9/8/17.
 */

public class ProductDetailActivity extends AppCompatActivity implements ProductPresenterContract.ProductView, CirclePageIndicator.RefreshCalledListener, View.OnClickListener, ProductPresenterContract.OnItemClickListner {

    private ViewPager mViewPager;
    private TextView mBrandName, mModel, mPrice;
    private TextView mSSize, mMSize, mLSize, mXLSize, mXLLSize;
    private ProductPresenterImpl mPresenter;
    private PagerAdapter mPagerAdapter;
    private List<String> mSlides = new ArrayList<>();
    private ProgressBar mLoader;
    private CirclePageIndicator mCirclePageIndicator;
    private Button mCartBtn;
    private Product mProduct;
    private LinearLayout mDetailContainer;
    private RecyclerView mColorsListView;
    public int mCount = 0;
    private List<String> mColorsList = new ArrayList<>();
    private ColorsAdapter mColorsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        initViews();
        initPresenter();
        setSlidingAdapter();
        setSizeCharts();
        getProductDetail();

    }

    private void setSlidingAdapter() {
        mPagerAdapter = new SlidingPagerAdapter(getSupportFragmentManager(), mSlides);
        mViewPager.setAdapter(mPagerAdapter);
        mCirclePageIndicator.setViewPager(mViewPager);

    }

    private void initPresenter() {
        mPresenter = new ProductPresenterImpl();
        mPresenter.onCreate(this);
    }

    private void getProductDetail() {
        String api;
        Random randomNo = new Random();
        int random = randomNo.nextInt();
        if(random % 3 == 0) {
            api = getString(R.string.product_url_1);
        } else if(random % 5 == 0) {
            api = getString(R.string.product_url_2);
        } else if(random % 7 == 0){
            api = getString(R.string.product_url_3);
        } else if(random % 24 == 0) {
            api = getString(R.string.product_url_4);
        } else {
            api = getString(R.string.product_url_5);
        }
        mPresenter.getProductDetails(api);
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mBrandName = (TextView) findViewById(R.id.brand_name);
        mModel = (TextView) findViewById(R.id.model);
        mPrice = (TextView) findViewById(R.id.price);
        mLoader = (ProgressBar) findViewById(R.id.loader);
        mSSize = (TextView) findViewById(R.id.small_size);
        mMSize = (TextView) findViewById(R.id.medium_size);
        mLSize = (TextView) findViewById(R.id.large_size);
        mXLSize = (TextView) findViewById(R.id.xlarge_size);
        mXLLSize = (TextView) findViewById(R.id.xxlarge_size);
        mCartBtn = (Button) findViewById(R.id.add_to_cart);
        mDetailContainer = (LinearLayout) findViewById(R.id.product_detail_container);
        mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.circle_page_indicator);
        mColorsListView = (RecyclerView) findViewById(R.id.colors_list_view);
        mCirclePageIndicator.setIsRefreshCalledListener(this);
        mColorsListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mSSize.setOnClickListener(this);
        mMSize.setOnClickListener(this);
        mLSize.setOnClickListener(this);
        mXLSize.setOnClickListener(this);
        mXLLSize.setOnClickListener(this);
        mCartBtn.setOnClickListener(this);

    }


    @Override
    public void showProductDetails(List<Product> product) {
        if (product != null) {
            mSlides.add(product.get(0).slides.slide1);
            mSlides.add(product.get(0).slides.slide2);
            mSlides.add(product.get(0).slides.slide3);
            updateAdapter();
            setProductInfo(product.get(0));
            setAutoScrolling(mSlides.size());
            hideLoader();
        }

    }

    private void setAutoScrolling(final int size) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mCount < size) {
                            mViewPager.setCurrentItem(mCount, true);
                            mCount++;
                        } else {
                            mViewPager.setCurrentItem(0, true);
                            mCount = 0;
                        }
                    }
                });

            }
        }, 500, 1000);
    }

    @Override
    public void onItemAdded() {
        Snackbar.make(mDetailContainer, R.string.item_added, Snackbar.LENGTH_LONG)
                .setActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary)).show();
    }

    @Override
    public void onItemExists() {
        Snackbar.make(mDetailContainer, R.string.item_exists, Snackbar.LENGTH_LONG)
                .setActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary)).show();
    }

    private void hideLoader() {
        if (mLoader != null) {
            mLoader.setVisibility(View.GONE);
        }
    }

    private void setProductInfo(Product product) {
        mProduct = product;
        mBrandName.setText(product.brandName);
        mModel.setText(product.model);
        mColorsAdapter = new ColorsAdapter(mPresenter.getProductList(product.color), this);
        mColorsListView.setAdapter(mColorsAdapter);
        String price = getString(R.string.rupee) + product.price;
        mPrice.setText(price);
    }


    private void setSizeCharts() {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.small_size:
                setSelected(mSSize);
                unselectOtherSizes(mMSize);
                unselectOtherSizes(mLSize);
                unselectOtherSizes(mXLSize);
                unselectOtherSizes(mXLLSize);
                break;
            case R.id.medium_size:
                setSelected(mMSize);
                unselectOtherSizes(mSSize);
                unselectOtherSizes(mLSize);
                unselectOtherSizes(mXLSize);
                unselectOtherSizes(mXLLSize);
                break;
            case R.id.large_size:
                setSelected(mLSize);
                unselectOtherSizes(mSSize);
                unselectOtherSizes(mMSize);
                unselectOtherSizes(mXLSize);
                unselectOtherSizes(mXLLSize);
                break;
            case R.id.xlarge_size:
                setSelected(mXLSize);
                unselectOtherSizes(mSSize);
                unselectOtherSizes(mMSize);
                unselectOtherSizes(mLSize);
                unselectOtherSizes(mXLLSize);
                break;
            case R.id.xxlarge_size:
                setSelected(mXLLSize);
                unselectOtherSizes(mSSize);
                unselectOtherSizes(mMSize);
                unselectOtherSizes(mLSize);
                unselectOtherSizes(mXLSize);
                break;
            case R.id.add_to_cart:
                mPresenter.additemToCart(mProduct);
        }
    }

    private void unselectOtherSizes(TextView textView) {
        textView.setSelected(false);
    }

    private void setSelected(TextView textView) {
        textView.setSelected(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.goto_cart) {
            goToCart();
        }
        return false;
    }

    private void goToCart() {
        Intent intent = new Intent(this, CartDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onColorSelected(int index) {
        if (mColorsListView != null) {
            for (int i = 0; i < mColorsListView.getChildCount(); i++) {
                if (i == index) {
                    mColorsListView.getChildAt(index).setSelected(true);
                } else {
                    mColorsListView.getChildAt(index).setSelected( false);
                }
            }
        }
    }

}
