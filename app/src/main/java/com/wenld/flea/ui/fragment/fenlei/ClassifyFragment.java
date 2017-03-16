package com.wenld.flea.ui.fragment.fenlei;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wenld.flea.R;
import com.wenld.flea.ui.ListActivity;

import java.util.ArrayList;

/**
 * 分类
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends Fragment {

    RecyclerView recyclerView;

    public ClassifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classify, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_classify);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new RvAdapter(getActivity()));

        return view;
    }

    public class RvAdapter extends RecyclerView.Adapter<RvAdapter.Holder> {

        ArrayList<String> listTitle = new ArrayList<>();
        ArrayList<Integer> listColor = new ArrayList<>();
        ArrayList<Integer> listImage = new ArrayList<>();

        Context context;

        /**
         * recycler适配器
         *
         * @param context
         */
        public RvAdapter(Context context) {
            this.context = context;
            listTitle.add("数码");
            listTitle.add("电器");
            listTitle.add("日常用品");
            listTitle.add("书籍");
            listTitle.add("服饰");
            listTitle.add("体育用品");
            listTitle.add("其他");

            listColor.add(0xaa00CC00);
            listColor.add(0xaaFF9900);
            listColor.add(0xaa9933FA);
            listColor.add(0xaa6699FF);
            listColor.add(0xaaFF9900);
            listColor.add(0xaa9933FA);
            listColor.add(0xaa6699FF);

            listImage.add(R.drawable.classify_shuma);
            listImage.add(R.drawable.classify_dianqi);
            listImage.add(R.drawable.classify_richang);
            listImage.add(R.drawable.classify_shuji);
            listImage.add(R.drawable.classify_fushi);
            listImage.add(R.drawable.classify_tiyu);
            listImage.add(R.drawable.classify_qita);
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_classify, parent, false);
            Holder holder = new Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(Holder holder, final int position) {
            holder.textView.setText(listTitle.get(position));
            holder.relativeLayout.setBackgroundColor(listColor.get(position));
            holder.imageView.setImageResource(listImage.get(position));

            holder.relativeLayout.setId(position);

        }

        @Override
        public int getItemCount() {
            return 7;
        }

        public class Holder extends RecyclerView.ViewHolder {
            RelativeLayout relativeLayout;
            ImageView imageView;
            TextView textView;

            public Holder(View view) {
                super(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ListActivity.class);
                        intent.putExtra("name", listTitle.get(getAdapterPosition()));
                        intent.putExtra("classify", getAdapterPosition()+"");
                        startActivity(intent);
                    }
                });
                relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout_classify);
                imageView = (ImageView) view.findViewById(R.id.imageView_classify);
                textView = (TextView) view.findViewById(R.id.textView_classify);
            }
        }
    }

}
