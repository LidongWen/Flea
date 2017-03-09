package com.wenld.flea.ui.fragment.discover;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenld.baselib.fragment.BaseLazyFragment;
import com.wenld.flea.R;
import com.wenld.flea.aop.LogonPermission;
import com.wenld.flea.ui.tab.SaleActivity;

import org.aspectj.lang.annotation.Aspect;

/**
 * A simple {@link Fragment} subclass.
 */
@Aspect
public class DiscoverFragment extends BaseLazyFragment {

    ViewPager viewPager;

    Button buttonLeft;
    Button buttonRight;

    ImageView imageView4;

    View add;

    boolean isLeftDiscover = true;
    private TextView textView_add_sale;
    private TextView textView_add_emption;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void initViewsAndEvents(View view) {
        buttonLeft = (Button) view.findViewById(R.id.button_left_discover);
        buttonRight = (Button) view.findViewById(R.id.button_right_discover);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager_discover);
        viewPager.setAdapter(new VpAdapter(getChildFragmentManager()));
        viewPager.addOnPageChangeListener(new PagerChangeListener());

        imageView4 = (ImageView) view.findViewById(R.id.imageView4);

        add = view.findViewById(R.id.add);
        textView_add_sale = (TextView) view.findViewById(R.id.textView_add_sale);
        textView_add_emption = (TextView) view.findViewById(R.id.textView_add_emption);
        initButtonState();  //初始化button状态

        initListener();
    }

    /**
     * 初始化 button 的状态
     */
    private void initButtonState() {
        if (!isLeftDiscover) {
            buttonLeft.setBackground(getResources().getDrawable(R.drawable.discover_tab_left_blue));
            buttonLeft.setTextColor(getResources().getColor(R.color.white));

            buttonRight.setBackground(getResources().getDrawable(R.drawable.discover_tab_right_white));
            buttonRight.setTextColor(getResources().getColor(R.color.blue_m));
        }
    }

    private void initListener() {
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonDiscover(view);
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButtonDiscover(view);
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });
        textView_add_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSale("测试");
            }
        });
        textView_add_emption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), SaleActivity.class);
//                startActivity(intent);
            }
        });
    }
    @LogonPermission
    public void startSale(String str) {
        Intent intent = new Intent(getActivity(), SaleActivity.class);
        startActivity(intent);
    }

    @Override
    protected void DetoryViewAndThing() {

    }


    /**
     * viewpager页面改变监听
     */
    public class PagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //button选中状态改变
            if (position == 0) {
                clickButtonDiscover(buttonLeft);
            } else {
                clickButtonDiscover(buttonRight);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 点击tab滑动viewpager
     *
     * @param position
     */
    public void pagerScrollTo(int position) {
        if (viewPager != null) {
            viewPager.setCurrentItem(position, true);
        }
    }

    /**
     * 点击tab_Button
     *
     * @param view
     */
    public void clickButtonDiscover(View view) {
        //点击 摊位
        if (view.getId() == R.id.button_left_discover && !isLeftDiscover) {
            buttonLeft.setBackground(getResources().getDrawable(R.drawable.discover_tab_left_white));
            buttonLeft.setTextColor(getResources().getColor(R.color.blue_m));

            buttonRight.setBackground(getResources().getDrawable(R.drawable.discover_tab_right_blue));
            buttonRight.setTextColor(getResources().getColor(R.color.white));

            pagerScrollTo(0);
            isLeftDiscover = true;
        }

        //点击 求购
        if (view.getId() == R.id.button_right_discover && isLeftDiscover) {
            buttonLeft.setBackground(getResources().getDrawable(R.drawable.discover_tab_left_blue));
            buttonLeft.setTextColor(getResources().getColor(R.color.white));

            buttonRight.setBackground(getResources().getDrawable(R.drawable.discover_tab_right_white));
            buttonRight.setTextColor(getResources().getColor(R.color.blue_m));

            pagerScrollTo(1);
            isLeftDiscover = false;
        }
    }


    public void toggle() {
        switch (add.getVisibility()) {
            case View.VISIBLE:
                add.setVisibility(View.INVISIBLE);
                break;
            case View.INVISIBLE:
                add.setVisibility(View.VISIBLE);
                break;
        }
    }

}
