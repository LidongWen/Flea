package com.wenld.flea.ui.fragment.discover;

import android.content.Intent;
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
import com.wenld.flea.common.SType;
import com.wenld.flea.ui.DetailActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;

import static com.wenld.flea.common.SType.TYPE_SELL;


public class DiscoverLeftFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeLayout;
    ArrayList<Goods> data = new ArrayList<>();
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
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.ic_launcher, TYPE_SELL, "1108888", false));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.test, TYPE_SELL, "1108888", true));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.test, TYPE_SELL, "1108888", true));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.ic_launcher, TYPE_SELL, "1108888", false));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.ic_launcher, TYPE_SELL, "1108888", true));
        data.add(new Goods("学长的台灯", 18, "九成新，这个东西好呀，经过学长几百个日日夜夜的培育，沾有文化气息", "wenld", "1", "2017-03-10", R.mipmap.test, TYPE_SELL, "1108888", true));

        adapter = new CommonAdapter<Goods>(getContext(), R.layout.list_discover_sale, data) {
            @Override
            protected void convert(ViewHolder holder, Goods user, int position) {
                ImageView imageView = holder.getView(R.id.imageView_discover_left);
                Glide.with(holder.getConvertView().getContext()).load(user.getPic_Res())
                        .dontAnimate()
                        .into(imageView);

                holder.setText(R.id.textView_discover_left_author, user.getUser_id());
                holder.setText(R.id.textView_discover_left_description, "描述:" + user.getDescribe());
                holder.setText(R.id.textView_discover_left_time, "时间：" + user.getTime());
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
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this.getActivity(), clazz);
        if(null != bundle) {
            intent.putExtras(bundle);
        }

        this.startActivity(intent);
    }
}
