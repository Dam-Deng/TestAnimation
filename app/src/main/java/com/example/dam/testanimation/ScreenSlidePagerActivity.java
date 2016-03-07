package com.example.dam.testanimation;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ScreenSlidePagerActivity extends FragmentActivity implements ScreenSlidePageFragment.OnFragmentInteractionListener {


    private static final int NUM_PAGES = 5;
    private ViewPager pager = null;
    private PagerAdapter pagerAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide_pager);

        pager = (ViewPager) findViewById(R.id.page);

        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        pager.setPageTransformer(true , new ZoomOutPageTransformer());

    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View page, float position) {

            int pageH = page.getHeight();
            int pageW = page.getWidth();

            if (position < -1) {
                page.setAlpha(0);
            } else if (position <= 1) {
                //計算縮放比例
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float verMargin = pageH * (1 - scaleFactor) / 2;
                float horMargin = pageW * (1 - scaleFactor) / 2;
//
                if (position < 0) {
                    page.setTranslationX(horMargin - verMargin / 2);
                } else {
                    page.setTranslationX(-horMargin + verMargin / 2);
                }

//                    page.setTranslationX(pageW);

                //最好是等比縮放
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);

                page.setAlpha(
                        MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA)
                );
            }else{
                page.setAlpha(0);
            }


        }
    }

}
