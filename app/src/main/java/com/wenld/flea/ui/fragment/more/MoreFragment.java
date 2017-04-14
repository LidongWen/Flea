package com.wenld.flea.ui.fragment.more;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenld.baselib.fragment.BaseLazyFragment;
import com.wenld.flea.App;
import com.wenld.flea.R;
import com.wenld.flea.ui.AboutActivity;
import com.wenld.flea.ui.CollectionActivity;
import com.wenld.flea.ui.MyDeailActivity;

/**
 * <p/>
 * Author: 温利东 on 2017/3/16 16:24.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */

public class MoreFragment extends BaseLazyFragment {
    public ImageView iv_icon_layout_user_header_a;
    public TextView tvName_layout_user_header_a;
    public ImageView QrImage;
    public LinearLayout layout_myCollection;
    public LinearLayout myInfo;
    public LinearLayout mydeail;
    public LinearLayout aboutMe;
    public LinearLayout logonOut;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_more;
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void initViewsAndEvents(View rootView) {
        this.iv_icon_layout_user_header_a = (ImageView) rootView.findViewById(R.id.iv_icon_layout_user_header_a);
        this.tvName_layout_user_header_a = (TextView) rootView.findViewById(R.id.tvName_layout_user_header_a);
        this.QrImage = (ImageView) rootView.findViewById(R.id.QrImage);
        this.layout_myCollection = (LinearLayout) rootView.findViewById(R.id.layout_myCollection);
        this.myInfo = (LinearLayout) rootView.findViewById(R.id.myInfo);
        this.mydeail = (LinearLayout) rootView.findViewById(R.id.mydeail);
        this.aboutMe = (LinearLayout) rootView.findViewById(R.id.aboutMe);
        this.logonOut = (LinearLayout) rootView.findViewById(R.id.logonOut);

        layout_myCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(CollectionActivity.class);
            }
        });
        mydeail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(MyDeailActivity.class);
            }
        });
        aboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readyGo(AboutActivity.class);

            }
        });
        logonOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getInstance().logonOut();
            }
        });
    }

    @Override
    protected void DetoryViewAndThing() {

    }
}
