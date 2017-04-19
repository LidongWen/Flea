package com.wenld.flea.ui;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.wenld.commontools.StringUtils;
import com.wenld.flea.R;
import com.wenld.flea.base.BaseActivity;
import com.wenld.flea.base.DefaultNavigationBar;
import com.wenld.flea.bean.Goods;
import com.wenld.flea.common.SType;

/**
 * Created by wenld on 2017/3/12.
 */

public class DetailQiuGouActivity extends BaseActivity {
    public TextView tv_title_aty_detail_qiugou;
    public TextView tv_pic_aty_detail_qiugou;
    public TextView tv_link_aty_detail_qiugou;
    public TextView tv_memo_aty_detail_qiugou;
    CheckBox cb_de;

    Goods goods;

    @Override
    protected void initData() {
        goods = (Goods) getIntent().getSerializableExtra(SType.intent_Detail);
//        if (goods == null)
//            return;
//        if (!StringUtils.isEmpty(goods.getPic_location()))
//            Glide.with(this).load(goods.getPic_location())
//                    .dontAnimate()
//                    .into(iv_aty_detail_qiugou);
//        else {
//            Glide.with(this).load(goods.getPic_Res())
//                    .dontAnimate()
//                    .into(iv_aty_detail_qiugou);
//        }

        tv_pic_aty_detail_qiugou.setText(String.format(getString(R.string.price_money), StringUtils.processNullStr(goods.getPrice() + "")));
        tv_title_aty_detail_qiugou.setText(String.format(getString(R.string.qiugou), StringUtils.processNullStr(goods.getTitle())));
        tv_memo_aty_detail_qiugou.setText(StringUtils.processNullStr(goods.getDescribe()));
        tv_link_aty_detail_qiugou.setText(String.format(getString(R.string.lianxifanshi), StringUtils.processNullStr(goods.getContact() + "")));
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

        this.tv_title_aty_detail_qiugou = (TextView) findViewById(R.id.tv_title_aty_detail_qiugou);
        this.tv_pic_aty_detail_qiugou = (TextView) findViewById(R.id.tv_pic_aty_detail_qiugou);
        this.tv_link_aty_detail_qiugou = (TextView) findViewById(R.id.tv_link_aty_detail_qiugou);
        this.tv_memo_aty_detail_qiugou = (TextView) findViewById(R.id.tv_memo_aty_detail_qiugou);
        cb_de = (CheckBox) findViewById(R.id.cb_de);

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_detail_qiugou);
    }

    @Override
    protected void initListener() {

    }

}
