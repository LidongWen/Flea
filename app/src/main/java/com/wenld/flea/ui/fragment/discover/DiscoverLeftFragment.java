package com.wenld.flea.ui.fragment.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wenld.commontools.FastJsonUtil;
import com.wenld.flea.R;
import com.wenld.flea.bean.Goods;
import com.wenld.flea.bean.User;
import com.wenld.flea.common.BaseApiCallback;
import com.wenld.flea.common.ESApi;
import com.wenld.flea.common.SType;
import com.wenld.flea.ui.DetailActivity;
import com.wenld.flea.view.AutoRecycleDevider;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;


public class DiscoverLeftFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeLayout;
    ArrayList<Goods> dataList = new ArrayList<>();
    CommonAdapter adapter;
    EmptyWrapper emptyWrapper;

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

        adapter = new CommonAdapter<Goods>(getContext(), R.layout.list_discover_sale, dataList) {
            @Override
            protected void convert(ViewHolder holder, Goods user, int position) {
                ImageView imageView = holder.getView(R.id.imageView_discover_left);
                Glide.with(holder.getConvertView().getContext()).load(user.getImg())
                        .dontAnimate()
                        .into(imageView);

                holder.setText(R.id.textView_discover_left_author, user.getUid());
                holder.setText(R.id.textView_discover_left_description, "描述:" + user.getDescribe());
                holder.setText(R.id.textView_discover_left_time, "时间：" + user.getAddtime());
            }
        };
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, GridLayoutManager.VERTICAL));
        emptyWrapper = new EmptyWrapper(adapter);
        emptyWrapper.setEmptyView(R.layout.layout_empty);
        recyclerView.setAdapter(emptyWrapper);

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
        recyclerView.addItemDecoration(new AutoRecycleDevider(getContext(), LinearLayoutManager.VERTICAL));


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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        //开始网络请求
        mSwipeLayout.setRefreshing(false);
    }

    //从本地取得数据
    private ArrayList<User> getDataList() {
        return null;
    }
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this.getActivity(), clazz);
        if(null != bundle) {
            intent.putExtras(bundle);
        }

        this.startActivity(intent);
    }
}
