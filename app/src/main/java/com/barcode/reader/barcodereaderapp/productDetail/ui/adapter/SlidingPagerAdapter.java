package com.barcode.reader.barcodereaderapp.productDetail.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.barcode.reader.barcodereaderapp.productDetail.ui.SlidingPagerFragment;

import java.util.List;

/**
 * Created by sangeetha on 10/8/17.
 */

public class SlidingPagerAdapter extends FragmentPagerAdapter {
    private List<String> mSlides;

    public SlidingPagerAdapter(FragmentManager fm, List<String> slides) {
        super(fm);
        mSlides = slides;
    }

    @Override
    public Fragment getItem(int position) {
        return SlidingPagerFragment.getInstance(mSlides.get(position));
    }

    @Override
    public int getCount() {
        return mSlides.size();
    }
}
