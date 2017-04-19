package com.wenld.flea.ui;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wenld.commontools.StringUtils;
import com.wenld.flea.R;
import com.wenld.flea.base.BaseActivity;
import com.wenld.flea.base.DefaultNavigationBar;
import com.wenld.flea.bean.Goods;
import com.wenld.flea.common.SType;

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
            Glide.with(this).load(goods.getImg())
                    .dontAnimate()
                    .into(iv_aty_detail);

        tv_pic_aty_detail.setText(String.format(getString(R.string.price_money), StringUtils.processNullStr(goods.getPrice() + "")));
        tv_title_aty_detail.setText(StringUtils.processNullStr(goods.getTitle()));
        tv_memo_aty_detail.setText(StringUtils.processNullStr(goods.getDescribe()));
        tv_link_aty_detail.setText(String.format(getString(R.string.lianxifanshi), StringUtils.processNullStr(goods.getContact() + "")));
        cb_de.setChecked("1".equals(goods.getStatus()));
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
        super.onDestroy();
    }

}
