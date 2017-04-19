package com.wenld.flea.ui.fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wenld.baselib.fragment.BaseLazyFragment;
import com.wenld.commontools.FastJsonUtil;
import com.wenld.commontools.StringUtils;
import com.wenld.flea.R;
import com.wenld.flea.bean.Goods;
import com.wenld.flea.common.BaseApiCallback;
import com.wenld.flea.common.ESApi;
import com.wenld.flea.common.SType;
import com.wenld.flea.ui.DetailActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseLazyFragment {

    RecyclerView recyclerView;
    EmptyWrapper emptyWrapper;
    CommonAdapter adapter;
    ArrayList<Goods> dataList = new ArrayList<>();
    ImageView imageView;

    public HomeFragment() {
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_home);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL));


        adapter = new CommonAdapter<Goods>(getContext(), R.layout.list_home, dataList) {
            @Override
            protected void convert(ViewHolder holder, Goods user, int position) {

                ImageView imageView = holder.getView(R.id.imageView_home);
//                if (!StringUtils.isEmpty(user.getPic_location()))
//                    Glide.with(holder.getConvertView().getContext()).load(user.getPic_location())
//                            .dontAnimate()
//                            .into(imageView);
//                else {
                    Glide.with(holder.getConvertView().getContext()).load(user.getImg())
                            .dontAnimate()
                            .into(imageView);
//                }

                holder.setText(R.id.textView_name, dataList.get(position).getTitle());
                holder.setText(R.id.textView_price, String.format(getString(R.string.price_money), StringUtils.processNullStr(dataList.get(position).getPrice() + "")));
            }
        };
        emptyWrapper=new EmptyWrapper(adapter);
        emptyWrapper.setEmptyView(R.layout.layout_empty);
        recyclerView.setAdapter(emptyWrapper);


        imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), SearchActivity.class));
            }
        });

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
    }

    @Override
    protected void DetoryViewAndThing() {
    }

    @Override
    protected void onFirstUserVisible() {
        ESApi.commodityList("", new BaseApiCallback() {
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
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

}

