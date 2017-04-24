package com.wenld.flea.ui.fragment.more;

import android.Manifest;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.LocalMediaLoader;
import com.luck.picture.lib.model.PictureConfig;
import com.wenld.baselib.fragment.BaseLazyFragment;
import com.wenld.flea.App;
import com.wenld.flea.R;
import com.wenld.flea.common.AppConfig;
import com.wenld.flea.common.CallBackBaseData;
import com.wenld.flea.common.ESApi;
import com.wenld.flea.ui.AboutActivity;
import com.wenld.flea.ui.CollectionActivity;
import com.wenld.flea.ui.LoginActivity;
import com.wenld.flea.ui.MyDeailActivity;
import com.yalantis.ucrop.entity.LocalMedia;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

import static com.luck.picture.lib.model.FunctionConfig.COPY_MODEL_1_1;
import static com.luck.picture.lib.model.FunctionConfig.MODE_SINGLE;

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
                Toast.makeText(v.getContext(), "退出登录成功", Toast.LENGTH_SHORT).show();
            }
        });
        changeInfo(new LogonEvent());
        iv_icon_layout_user_header_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (App.getInstance().user != null) {
                    clickAddPicture();
                } else {
                    readyGo(LoginActivity.class);
                }
            }
        });
        EventBus.getDefault().register(this);
    }

    @Override
    protected void DetoryViewAndThing() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void changeInfo(LogonEvent event) {
        if (App.getInstance().user != null) {
            tvName_layout_user_header_a.setText(App.getInstance().user.getName());
            Glide.with(this).load(AppConfig.img_url + App.getInstance().user.getImage())
                    .dontAnimate()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv_icon_layout_user_header_a);
        } else {
            tvName_layout_user_header_a.setText("请登录");
            iv_icon_layout_user_header_a.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        }
    }

    /**
     * 点击添加图片
     */
    public void clickAddPicture() {
        PermissionGen.needPermission(this, 100,
                new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }
        );
    }

    public static List<LocalMedia> selectedMedia; // 已选图片合集

    /**
     * 选择图片
     */

    public void selectPic() {
//
        if (selectedMedia != null && selectedMedia.size() != 0) {
            selectedMedia.clear();
        } else {
            selectedMedia = new ArrayList<>();
        }
        FunctionConfig config = new FunctionConfig();
        config.setType(LocalMediaLoader.TYPE_IMAGE);
        config.setCopyMode(COPY_MODEL_1_1);
        config.setCompress(true); //是否压缩
        config.setMaxSelectNum(1); //最大可选数量
//        config.setSelectMode(MODE_MULTIPLE);
        config.setSelectMode(MODE_SINGLE);
        config.setShowCamera(false); //是否显示相机
        config.setEnablePreview(true); //是否预览
        config.setEnableCrop(false); //是否裁剪
        config.setPreviewColor(Color.CYAN); //预览文字颜色
        config.setCompleteColor(R.color.red); //完成文字颜色
        config.setPreviewBottomBgColor(Color.BLACK); //预览界面底部背景色
        config.setBottomBgColor(Color.WHITE); //选择图片页面底部背景色
        config.setSelectMedia(selectedMedia); //已选图片集合
        config.setCompressFlag(2);
        config.setThemeStyle(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        config.setCompleteColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        config.setPreviewColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        PictureConfig.init(config); // 先初始化参数配置，在启动相册
        PictureConfig.getPictureConfig().openPhoto(getContext(), resultCallback); // 启动相册并设置回调函数
    }

    /**
     * 图片回调方法
     */
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
            selectedMedia.addAll(resultList);
            if (selectedMedia != null) {
                Glide.with(getContext()).load(selectedMedia.get(0).getPath()).dontAnimate().into(iv_icon_layout_user_header_a);
                ESApi.modify(new File(selectedMedia.get(0).getPath()), new CallBackBaseData() {
                    @Override
                    protected void onAPISuccess(String data) {

                    }

                    @Override
                    protected void onAPIFailure(String msg) {

                    }
                });
            }
        }
    };

    @PermissionSuccess(requestCode = 100)
    public void doSomething() {
        selectPic();//  Toast.makeText(this, "Contact permission is granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething() {
//        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
    }
}
