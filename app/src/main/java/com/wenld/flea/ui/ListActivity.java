package com.wenld.flea.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wenld.commontools.StringUtils;
import com.wenld.flea.R;
import com.wenld.flea.base.BaseActivity;
import com.wenld.flea.base.DefaultNavigationBar;
import com.wenld.flea.bean.Goods;
import com.wenld.flea.common.SType;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import static com.wenld.flea.common.SType.TYPE_SELL;

/**
 * Created by wenld on 2017/3/12.
 */

public class ListActivity extends BaseActivity {
    public RecyclerView recyclerView_aty_list;
    CommonAdapter adapter;
    ArrayList<Goods> data = new ArrayList<>();
    @Override
    protected void initData() {
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.ic_launcher, TYPE_SELL,"1108888"));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.test, TYPE_SELL,"1108888"));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.test, TYPE_SELL,"1108888"));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.ic_launcher, TYPE_SELL,"1108888"));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.ic_launcher, TYPE_SELL,"1108888"));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.test, TYPE_SELL,"1108888"));

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {

        new DefaultNavigationBar.Builder(this, null).setLeftIcon(R.drawable.activity_return).setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setRight("")
                .setTitle("").create();

        recyclerView_aty_list = (RecyclerView) findViewById(R.id.recyclerView_aty_list);

        recyclerView_aty_list.setLayoutManager(new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL));

        adapter = new CommonAdapter<Goods>(this, R.layout.list_home, data) {
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
        recyclerView_aty_list.setAdapter(adapter);




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
