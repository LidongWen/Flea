package com.wenld.flea.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wenld.baselib.navigatbar.AbsNavigationBar;
import com.wenld.flea.R;


/**
 * Created by wenld on 2017/2/27.
 */

public class DefaultNavigationBar extends AbsNavigationBar<DefaultNavigationBar.Builder.DefalutNavationParams> {

    public DefaultNavigationBar(Builder.DefalutNavationParams parent) {
        super(parent);
    }
    @Override
    public int bindLayoutId() {
        return R.layout.topbar_common_single_text;
    }

    @Override
    public void applyVIew() {

        setImageResource(R.id.iv_back, getmParams().leftIconRes);
        setImageResource(R.id.iv_Right, getmParams().rightIconRes);
        setText(R.id.tv_title, getmParams().title);
        setText(R.id.tv_Right, getmParams().rightTv);
        setOnClickListener(R.id.iv_back, getmParams().leftOnClickListener);
        setOnClickListener(R.id.tv_Right, getmParams().rightOnClickListener);
    }

    public static class Builder extends AbsNavigationBar.Builder {
        DefalutNavationParams params;

        public Builder(Context context, ViewGroup parent) {
            super(context, parent);
            params = new DefalutNavationParams(context, parent);
        }

        public Builder setTitle(String title) {
            params.title = title;
            return this;
        }

        public Builder setRight(String right) {
            params.rightTv = right;
            return this;
        }

        public Builder setLeft(String left) {
            params.leftTv = left;
            return this;
        }

        public Builder setLeftIcon(int iconRes) {
            params.leftIconRes = iconRes;
            return this;
        }

        public Builder setRightIcon(int iconRes) {
            params.rightIconRes = iconRes;
            return this;
        }

        public Builder setTitleBackgroundColor(int bgColor) {
            params.bgColor = bgColor;
            return this;
        }

        public Builder setLeftOnClickListener(View.OnClickListener onClickListener) {
            params.leftOnClickListener = onClickListener;
            return this;
        }

        public Builder setRightOnClickListener(View.OnClickListener onClickListener) {
            params.rightOnClickListener = onClickListener;
            return this;
        }

        @Override
        public DefaultNavigationBar create() {
            DefaultNavigationBar navigationBar = new DefaultNavigationBar(params);
            return navigationBar;
        }

        public static class DefalutNavationParams extends AbsNavigationBar.Builder.AbsNavigationBarParams {
//            public SparseArray<CharSequence> mTextArray = new SparseArray();
//            public SparseArray<View.OnClickListener> mClickArray = new SparseArray();

            //标题
            public String title;
            //左边图片资源
            public int leftIconRes;
            //右边图片资源
            public int rightIconRes;
            //左边的点击事件
            public View.OnClickListener leftOnClickListener;
            //右边的点击事件
            public View.OnClickListener rightOnClickListener;
            public String leftTv;
            public String rightTv;
            public int bgColor;

            public DefalutNavationParams(Context context, ViewGroup parent) {
                super(context, parent);
            }
        }
    }
}
