package com.wenld.flea.ui.fragment.discover;

/**
 * <p/>
 * Author: 温利东 on 2017/3/7 16:52.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * ViewPager 适配器
 */
public class VpAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> data = new ArrayList<>();

    DiscoverLeftFragment fgLeft;
    DiscoverRightFragment fgRight;

    public VpAdapter(FragmentManager fm) {
        super(fm);
        fgLeft = new DiscoverLeftFragment();
        fgRight = new DiscoverRightFragment();

        data.add(fgLeft);
        data.add(fgRight);
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}