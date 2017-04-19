package com.wenld.flea.ui.fragment.discover;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenld.flea.R;
import com.wenld.flea.bean.Goods;
import com.wenld.flea.common.SType;
import com.wenld.flea.ui.DetailQiuGouActivity;
import com.wenld.flea.view.AutoRecycleDevider;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverRightFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ArrayList<Goods> data = new ArrayList<>();
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeLayout;
    CommonAdapter adapter;
    EmptyWrapper emptyWrapper;

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

        adapter = new CommonAdapter<Goods>(getContext(), R.layout.list_discover_emption, data) {
            @Override
            protected void convert(ViewHolder holder, Goods user, int position) {

//                ImageView imageView = holder.getView(R.id.dis_right_sd);
//                Glide.with(holder.getConvertView().getContext()).load(user.getPic_location()).placeholder(R.mipmap.timg)
//                        .dontAnimate()
//                        .into(imageView);
//
//                holder.setText(R.id.dis_right_classify, String.format(getString(R.string.qiugou), StringUtils.processNullStr(user.getTitle())));
//                holder.setText(R.id.dis_right_describe, user.getDescribe());
//                holder.setText(R.id.dis_right_author, user.getUser_id());
//                holder.setText(R.id.textView_purchase_time, user.getTime());

            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        emptyWrapper = new EmptyWrapper(adapter);
        emptyWrapper.setEmptyView(R.layout.layout_empty);
        recyclerView.setAdapter(emptyWrapper);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<Goods>() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, Goods o, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(SType.intent_Detail, o);
                readyGo(DetailQiuGouActivity.class, bundle);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Goods o, int position) {
                return false;
            }
        });
        recyclerView.addItemDecoration(new AutoRecycleDevider(getContext(), LinearLayoutManager.VERTICAL));
    }


    @Override
    public void onRefresh() {
        mSwipeLayout.setRefreshing(false);
        emptyWrapper.notifyDataSetChanged();
    }
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this.getActivity(), clazz);
        if(null != bundle) {
            intent.putExtras(bundle);
        }

        this.startActivity(intent);
    }
}
