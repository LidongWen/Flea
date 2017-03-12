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
import com.wenld.flea.bean.User;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import static com.wenld.flea.common.SType.TYPE_SELL;


public class DiscoverLeftFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeLayout;
    ArrayList<Goods> data = new ArrayList<>();
    CommonAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover_left, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_dis_left);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.colorAccent, R.color.green_m, R.color.red_m);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_discover_left);

        initView();
    }

    void initView() {
        data.add(new Goods("学长的台灯", 18, "九成新", "wenld", "1", "2017-03-10", R.mipmap.ic_launcher + "", TYPE_SELL));
        adapter = new CommonAdapter<Goods>(getContext(), R.layout.list_discover_sale, data) {
            @Override
            protected void convert(ViewHolder holder, Goods user, int position) {
                ImageView imageView = holder.getView(R.id.imageView_discover_left);
                Glide.with(holder.getConvertView().getContext()).load(user.getPic_location())
                        .dontAnimate()
                        .into(imageView);

                holder.setText(R.id.textView_discover_left_author, user.getUser_id());
                holder.setText(R.id.textView_discover_left_description, "描述:" + user.getDescribe());
            }
        };
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, GridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        //开始网络请求
        mSwipeLayout.setRefreshing(false);
    }

    //从本地取得数据
    private ArrayList<User> getData() {
        return null;
    }

}
