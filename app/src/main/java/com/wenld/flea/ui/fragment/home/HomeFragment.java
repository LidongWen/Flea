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
import com.wenld.commontools.StringUtils;
import com.wenld.flea.R;
import com.wenld.flea.bean.Goods;
import com.wenld.flea.common.SType;
import com.wenld.flea.ui.DetailActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;

import static com.wenld.flea.common.SType.TYPE_SELL;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseLazyFragment {

    RecyclerView recyclerView;
    EmptyWrapper emptyWrapper;
    CommonAdapter adapter;
    ArrayList<Goods> data = new ArrayList<>();
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


        adapter = new CommonAdapter<Goods>(getContext(), R.layout.list_home, data) {
            @Override
            protected void convert(ViewHolder holder, Goods user, int position) {

                ImageView imageView = holder.getView(R.id.imageView_home);
                if (!StringUtils.isEmpty(user.getPic_location()))
                    Glide.with(holder.getConvertView().getContext()).load(user.getPic_location())
                            .dontAnimate()
                            .into(imageView);
                else {
                    Glide.with(holder.getConvertView().getContext()).load(user.getPic_Res())
                            .dontAnimate()
                            .into(imageView);
                }

                holder.setText(R.id.textView_name, data.get(position).getTitle());
                holder.setText(R.id.textView_price, String.format(getString(R.string.price_money), StringUtils.processNullStr(data.get(position).getPrice() + "")));
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
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.ic_launcher, TYPE_SELL, "1108888",false));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.test, TYPE_SELL, "1108888",true));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.test, TYPE_SELL, "1108888",true));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.ic_launcher, TYPE_SELL, "1108888",false));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.ic_launcher, TYPE_SELL, "1108888",true));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.test, TYPE_SELL, "1108888",true));

        emptyWrapper.notifyDataSetChanged();
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

}

