package com.wenld.flea.ui;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wenld.commontools.LogUtil;
import com.wenld.commontools.StringUtils;
import com.wenld.flea.R;
import com.wenld.flea.base.BaseActivity;
import com.wenld.flea.base.DefaultNavigationBar;
import com.wenld.flea.bean.Goods;
import com.wenld.flea.common.SType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by wenld on 2017/3/12.
 */

public class DetailActivity extends BaseActivity {
    public ImageView iv_aty_detail;
    public TextView tv_title_aty_detail;
    public TextView tv_pic_aty_detail;
    public TextView tv_link_aty_detail;
    public TextView tv_memo_aty_detail;
    CheckBox cb_de;

    Goods goods;

    @Override
    protected void initData() {
        goods = (Goods) getIntent().getSerializableExtra(SType.intent_Detail);
        if (goods == null)
            return;
        if (!StringUtils.isEmpty(goods.getPic_location()))
            Glide.with(this).load(goods.getPic_location())
                    .dontAnimate()
                    .into(iv_aty_detail);
        else {
            Glide.with(this).load(goods.getPic_Res())
                    .dontAnimate()
                    .into(iv_aty_detail);
        }

        tv_pic_aty_detail.setText(String.format(getString(R.string.price_money), StringUtils.processNullStr(goods.getPrice() + "")));
        tv_title_aty_detail.setText(StringUtils.processNullStr(goods.getTitle()));
        tv_memo_aty_detail.setText(StringUtils.processNullStr(goods.getDescribe()));
        tv_link_aty_detail.setText(String.format(getString(R.string.lianxifanshi), StringUtils.processNullStr(goods.getLink() + "")));
        cb_de.setChecked(goods.isCollect());
    }

    @Override
    protected void initView() {

        new DefaultNavigationBar.Builder(this, null).setLeftIcon(R.drawable.activity_return).setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setRight("")
                .setTitle("商品详情").create();

        this.iv_aty_detail = (ImageView) findViewById(R.id.iv_aty_detail);
        this.tv_title_aty_detail = (TextView) findViewById(R.id.tv_title_aty_detail);
        this.tv_pic_aty_detail = (TextView) findViewById(R.id.tv_pic_aty_detail);
        this.tv_link_aty_detail = (TextView) findViewById(R.id.tv_link_aty_detail);
        this.tv_memo_aty_detail = (TextView) findViewById(R.id.tv_memo_aty_detail);
        cb_de = (CheckBox) findViewById(R.id.cb_de);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(sticky = true)
    public void Test(String str){
        String  aa=str;
        LogUtil.e("sa",""+aa);
    }
}
