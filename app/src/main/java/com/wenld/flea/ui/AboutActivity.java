package com.wenld.flea.ui;

import android.view.View;

import com.wenld.flea.R;
import com.wenld.flea.base.BaseActivity;
import com.wenld.flea.base.DefaultNavigationBar;

/**
 * Created by wenld on 2017/3/12.
 */

public class AboutActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        new DefaultNavigationBar.Builder(this, null).setLeftIcon(R.drawable.activity_return).setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setRight("")
                .setTitle("关于").create();

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void initListener() {

    }

}
