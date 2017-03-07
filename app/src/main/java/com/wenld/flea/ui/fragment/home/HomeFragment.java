package com.wenld.flea.ui.fragment.home;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wenld.baselib.fragment.BaseLazyFragment;
import com.wenld.flea.R;
import com.wenld.flea.bean.Goods;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseLazyFragment {

    RecyclerView recyclerView;
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
                Glide.with(holder.getConvertView().getContext()).load(user.getPic_location())
                        .dontAnimate()
                        .into(imageView);

                holder.setText(R.id.textView_name, data.get(position).getTitle());
                holder.setText(R.id.textView_price, "描述:" + String.valueOf(data.get(position).getPrice()) + "￥");
            }
        };
        recyclerView.setAdapter(adapter);


        imageView= (ImageView) view.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(),SearchActivity.class));
            }
        });
    }

    @Override
    protected void DetoryViewAndThing() {

    }
    @Override
    protected void onFirstUserVisible() {
        data.add(new Goods());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onUserVisible() {

    }
    @Override
    protected void onUserInvisible() {

    }

}

