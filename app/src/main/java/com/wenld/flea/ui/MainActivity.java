package com.wenld.flea.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wenld.flea.R;
import com.wenld.flea.base.BaseActivity;
import com.wenld.flea.ui.fragment.discover.DiscoverFragment;
import com.wenld.flea.ui.fragment.fenlei.ClassifyFragment;
import com.wenld.flea.ui.fragment.home.HomeFragment;
import com.wenld.flea.ui.fragment.more.MoreFragment;
import com.wenld.flea.ui.tab.TabEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    CommonTabLayout tl_2;
    ViewPager vp_2;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        EventBus.getDefault().postSticky("ssss");
        for (String title : mTitles) {
            mFragments.add(new ClassifyFragment());
        }
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }


        tl_2 = (CommonTabLayout) findViewById(R.id.tl_2);
        vp_2 = (ViewPager) findViewById(R.id.vp_2);

        vp_2.setAdapter(mFragmentPagerAdapter);
        tl_2.setTabData(mTabEntities);

        vp_2.setCurrentItem(0);
        vp_2.setOffscreenPageLimit(mFragments.size());
//        ESApi.account("admin","admin","admin","10111111111", new EngineCallBack<BaseDataModel>() {
//            @Override
//            public void onError(Exception e, int i) {
//
//            }
//
//            @Override
//            public void onResponse(BaseDataModel baseDataModel, int i) {
//                BaseDataModel a= baseDataModel;
//            }
//        });
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initListener() {
        vp_2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tl_2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tl_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp_2.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                }
            }
        });
    }

    private FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new DiscoverFragment();
                case 2:
                    return new ClassifyFragment();
                case 3:
                    return new MoreFragment();
                default:
                    return new Fragment();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

    };



    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"首页", "发现", "分类", "更多"};
    private int[] mIconUnselectIds = {
            R.mipmap.byr, R.mipmap.byn,
            R.mipmap.byl, R.mipmap.byt};
    private int[] mIconSelectIds = {
            R.mipmap.bys, R.mipmap.byo,
            R.mipmap.bym, R.mipmap.byu};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
}
