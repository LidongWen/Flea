package com.wenld.flea.ui.fragment.discover;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wenld.flea.R;
import com.wenld.flea.bean.Goods;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverRightFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ArrayList<Goods> data = new ArrayList<>();
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeLayout;
    CommonAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover_right, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_discover_right);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_dis_right);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.colorAccent, R.color.green_m, R.color.red_m);
        initView();
    }

    void initView() {
        data = new ArrayList<>();
        data.add(new Goods());
        adapter = new CommonAdapter<Goods>(getContext(), R.layout.list_discover_emption, data) {
            @Override
            protected void convert(ViewHolder holder, Goods user, int position) {

                ImageView imageView = holder.getView(R.id.dis_right_sd);
                Glide.with(holder.getConvertView().getContext()).load(user.getPic_location())
                        .dontAnimate()
                        .into(imageView);

                holder.setText(R.id.dis_right_classify, user.getClassify());
                holder.setText(R.id.dis_right_describe, user.getDescribe());
                holder.setText(R.id.dis_right_author, user.getUserName());
                holder.setText(R.id.textView_purchase_time, user.getTime());

            }
        };

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, GridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onRefresh() {
    }

}
