package com.wenld.flea.fragment.home;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wenld.flea.R;
import com.wenld.flea.bean.Goods;

import java.util.ArrayList;

/**
 * Created by fiona on 16-3-13.
 */
public class ListViewAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    ArrayList<Goods> data;
    ArrayList<int[]> location;
    LayoutInflater inflater;
    Context context;
    OnItemSelected onItemSelected;

    public ListViewAdapter(Context context, ArrayList<Goods> data, ArrayList<int[]> location) {
        this.data = data;
        this.context = context;
        this.location = location;
        if (context instanceof OnItemSelected) {
            onItemSelected = (OnItemSelected) context;
        }
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_search, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        int start = location.get(position)[0];
        int end = location.get(position)[1];
        //html不起作用
        String title = data.get(position).getTitle();
        String key = "<u>" + title.substring(start, end + 1) + "</u>";
        String header = title.substring(0, start);
        String footer = title.substring(end + 1);
        holder.textViewTitle.setText(header + Html.fromHtml(key) + footer);
        holder.textViewAuthor.setText(data.get(position).getUserName());
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //保存历史记录
        onItemSelected.onItemSelected();

//        Intent intent = new Intent(context, ProductActivity.class);
//        intent.putExtra(App.ACTION_GOODS, data.get(position));
//        context.startActivity(intent);
    }

    private class Holder {
        TextView textViewTitle;
        TextView textViewAuthor;

        public Holder(View view) {
            textViewTitle = (TextView) view.findViewById(R.id.textView2);
            textViewAuthor = (TextView) view.findViewById(R.id.textView3);
        }
    }

    public interface OnItemSelected {
        /**
         * 保存历史记录
         */
        void onItemSelected();
    }
}
