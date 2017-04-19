package com.wenld.flea.ui.fragment.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wenld.commontools.StringUtils;
import com.wenld.flea.R;
import com.wenld.flea.base.BaseActivity;
import com.wenld.flea.bean.Goods;
import com.wenld.flea.view.tagview.FlowLayout;
import com.wenld.flea.view.tagview.TagAdapter;
import com.wenld.flea.view.tagview.TagFlowLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;


public class SearchActivity extends BaseActivity {

    CategoryTagAdapter categoryTagAdapter;

    ArrayList<Goods> listData = new ArrayList<>();  //所有出售物品
    ArrayList<Goods> showData = new ArrayList<>();  //匹配的物品
    ArrayList<String> location = new ArrayList<>();

    CommonAdapter adapter;

    int start;
    int end;

    private EditText editTextSearch;
    private RelativeLayout relativeLayout2;
    private TextView textViewSearch;
    private TextView textView15;
    private TagFlowLayout idUploadTagLayout;
    private RecyclerView listViewSearch;

    @Override
    protected void initData() {
        listViewSearch.setLayoutManager(new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL));

        location.add("娃娃");
        location.add("苍井空");
        location.add("钓鱼岛");
        categoryTagAdapter = new CategoryTagAdapter(location.toArray(new String[location.size()]));
        idUploadTagLayout.setAdapter(categoryTagAdapter);
//        idUploadTagLayout.setMaxSelectCount(0);

        adapter = new CommonAdapter<Goods>(this, R.layout.list_home, showData) {
            @Override
            protected void convert(ViewHolder holder, Goods user, int position) {

                ImageView imageView = holder.getView(R.id.imageView_home);
                Glide.with(holder.getConvertView().getContext()).load(user.getImg())
                        .dontAnimate()
                        .into(imageView);

                holder.setText(R.id.textView_name, showData.get(position).getTitle());
                holder.setText(R.id.textView_price, "描述:" + String.valueOf(showData.get(position).getPrice()) + "￥");
            }
        };

        listViewSearch.setAdapter(adapter);
        //输入监听
        editTextSearch.addTextChangedListener(new TextChangedListener());

        //点击键盘的搜索
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    clickCancel(editTextSearch);
                }
                return false;
            }
        });
    }

    public void clickCancel(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextSearch.getWindowToken(), 0);
        finish();   //  取消
    }

    public void initView() {
        editTextSearch = (EditText) findViewById(R.id.editText_search);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.relativeLayout2);
        textViewSearch = (TextView) findViewById(R.id.textView_search);
        textView15 = (TextView) findViewById(R.id.textView15);
        idUploadTagLayout = (TagFlowLayout) findViewById(R.id.id_upload_tag_layout);
        listViewSearch = (RecyclerView) findViewById(R.id.listView_search);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void initListener() {
        idUploadTagLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                editTextSearch.setText(location.get(position));
//                MyLog.i("position-->", position);
//
//                if (mValue == position) {
//
//                } else {
//                    mSelectPosSet.clear();
//                    mValue = position;
//                    mSelectPosSet.add(mValue);
//                }
//                categoryTagAdapter.setSelectedList(mValue);
                return true;
            }
        });
    }


    /**
     * 输入框监听器
     */
    private class TextChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            filterData(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    private class CategoryTagAdapter extends TagAdapter {
        public CategoryTagAdapter(String[] datas) {
            super(datas);
        }

        @Override
        public View getView(FlowLayout parent, int position, Object o) {
            TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.layout_tag_text, idUploadTagLayout, false);
            tv.setText(o.toString());
            return tv;
        }
    }

    String input = "";

    void filterData(String filterStr) {
        if (input.equals(filterStr))
            return;
        input = filterStr;

        if (TextUtils.isEmpty(filterStr)) {
            showData.clear();
            showData.addAll(listData);
        } else {
            showData.clear();
            for (Goods goods : listData) {
                String name = StringUtils.processNullStr(goods.getTitle());
                if (name.contains(filterStr)) {
                    showData.add(goods);
                }
            }
        }
        if (showData.size() > 0)
            adapter.notifyDataSetChanged();
        else
            Toast.makeText(SearchActivity.this, "竹篮打水一场空，你什么也没搜到", Toast.LENGTH_SHORT).show();
    }
}
