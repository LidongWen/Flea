package com.wenld.flea.fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    CommonAdapter adapter;
    ArrayList<Goods> data = new ArrayList<>();

    ImageView imageView;
//    public Handler handler = new MyHandler();

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data.add(new Goods());
        /**
         * recycler设置
         */
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
}

