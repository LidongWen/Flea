package com.wenld.flea.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wenld.commontools.FastJsonUtil;
import com.wenld.commontools.StringUtils;
import com.wenld.flea.R;
import com.wenld.flea.aop.LogonPermission;
import com.wenld.flea.base.BaseActivity;
import com.wenld.flea.base.DefaultNavigationBar;
import com.wenld.flea.bean.Goods;
import com.wenld.flea.common.CallBackBaseData;
import com.wenld.flea.common.ESApi;
import com.wenld.flea.common.SType;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;

/**
 * Created by wenld on 2017/3/12.
 */

public class MyDeailActivity extends BaseActivity {
    public RecyclerView recyclerView_aty_list;
    CommonAdapter adapter;
    EmptyWrapper emptyWrapper;
    ArrayList<Goods> dataList = new ArrayList<>();

    @LogonPermission
    @Override
    protected void initData() {

        ESApi.getMyGoods(new CallBackBaseData() {
            @Override
            protected void onAPISuccess(String data) {
                dataList.clear();
                dataList.addAll(FastJsonUtil.getListObjects(data,Goods.class));
                emptyWrapper.notifyDataSetChanged();
            }

            @Override
            protected void onAPIFailure(String msg) {

            }
        });

    }

    @Override
    protected void initView() {

        new DefaultNavigationBar.Builder(this, null).setLeftIcon(R.drawable.activity_return).setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setRight("")
                .setTitle("我的商品").create();

        recyclerView_aty_list = (RecyclerView) findViewById(R.id.recyclerView_aty_list);

        recyclerView_aty_list.setLayoutManager(new StaggeredGridLayoutManager(1, GridLayoutManager.VERTICAL));
        recyclerView_aty_list.setItemAnimator(new DefaultItemAnimator());
        adapter = new CommonAdapter<Goods>(this, R.layout.list_collect, dataList) {
            @Override
            protected void convert(ViewHolder holder, final Goods user, final int position) {
                CheckBox cb_aty_detail = holder.getView(R.id.cb_aty_detail);
                ImageView imageView = holder.getView(R.id.imageView_home);
                    Glide.with(holder.getConvertView().getContext()).load(user.getImg())
                            .dontAnimate()
                            .into(imageView);

                holder.setText(R.id.textView_name, dataList.get(position).getTitle());
                holder.setText(R.id.textView_price, String.format(getString(R.string.price_money), StringUtils.processNullStr(dataList.get(position).getPrice() + "")));

                cb_aty_detail.setOnCheckedChangeListener(null);
                cb_aty_detail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        ESApi.statusGood(user.getId(), isChecked ? "1" : "0", new CallBackBaseData() {
                            @Override
                            protected void onAPISuccess(String data) {

                            }

                            @Override
                            protected void onAPIFailure(String msg) {

                            }
                        });
                    }
                });
            }
        };


        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<Goods>() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, Goods o, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(SType.intent_Detail, o);
                readyGo(DetailActivity.class, bundle);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Goods o, int position) {
                return false;
            }
        });
        emptyWrapper = new EmptyWrapper(adapter);
        emptyWrapper.setEmptyView(R.layout.layout_empty);
        recyclerView_aty_list.setAdapter(emptyWrapper);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_list);
    }

    @Override
    protected void initListener() {

    }
}
