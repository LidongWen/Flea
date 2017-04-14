package com.wenld.flea.ui.tab;

import android.Manifest;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.LocalMediaLoader;
import com.luck.picture.lib.model.PictureConfig;
import com.wenld.flea.R;
import com.wenld.flea.base.BaseActivity;
import com.wenld.flea.base.DefaultNavigationBar;
import com.yalantis.ucrop.entity.LocalMedia;

import java.io.File;
import java.util.List;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

import static com.luck.picture.lib.model.FunctionConfig.COPY_MODEL_1_1;
import static com.luck.picture.lib.model.FunctionConfig.MODE_SINGLE;


public class SaleActivity extends BaseActivity {

    ImageView imageView;

    String title;               //  物品名称
    String price;               //  价格
    String contact;              //  联系方式
    String describe;             //  描述
    String classify;             //  分类 （暂时为电器）
    String userID;           //  用户id (暂时为1)

    public File file;                //  物品图片

    EditText editTextTitle;
    EditText editTextPrice;
    EditText editTextContact;
    EditText editTextDescribe;
    Spinner spinner;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        new DefaultNavigationBar.Builder(this, null).setLeftIcon(R.drawable.activity_return).setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setRight("提交").setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSure(v);
            }
        }).setTitle("出售商品").create();

        imageView = (ImageView) findViewById(R.id.imageView7);
        editTextTitle = (EditText) findViewById(R.id.textView21_edit_sale_title);
        editTextPrice = (EditText) findViewById(R.id.textView23_edit_sale_price);
        editTextContact = (EditText) findViewById(R.id.textView25_edit_sale_contact);
        editTextDescribe = (EditText) findViewById(R.id.textView29_edit_sale_describe);
        spinner = (Spinner) findViewById(R.id.spinner_edit_sale);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        Fresco.initialize(this);
        setContentView(R.layout.activity_sale);
    }

    @Override
    protected void initListener() {

        final String[] data = getResources().getStringArray(R.array.classify);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classify = data[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 点击提交
     *
     * @param view
     */
    public void clickSure(View view) {
//        title = editTextTitle.getText().toString();
//        price = editTextPrice.getText().toString();
//        contact = editTextContact.getText().toString();
//        describe = editTextDescribe.getText().toString();
//
//        if (title != null && price != null && contact != null && describe != null && file != null) {
//            HashMap<String, String> map = new HashMap<>();
//            map.put("title", title);
//            map.put("price", price);
//            map.put("contact", contact);
//            map.put("describe", describe);
//            map.put("classify", classify);
//            map.put("user_id", userID);
//
//
////            UploadImpl.getInstance(this).addGoods(file, map, true);
////
////            //发网络请求(出售物品)
////            NetQueryImpl.getInstance(this).getSaleGoods();
//
//            Toast.makeText(SaleActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
//            finish();
//
//        } else {
//            Toast.makeText(SaleActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
//        }
    }

    /**
     * 点击添加图片
     *
     * @param view
     */
    public void clickAddPicture(View view) {
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
        }
        FunctionConfig config = new FunctionConfig();
        config.setType(LocalMediaLoader.TYPE_IMAGE);
        config.setCopyMode(COPY_MODEL_1_1);
        config.setCompress(false); //是否压缩
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
        config.setThemeStyle(ContextCompat.getColor(this, R.color.colorPrimary));
        config.setCompleteColor(ContextCompat.getColor(this, R.color.colorPrimary));
        config.setPreviewColor(ContextCompat.getColor(this, R.color.colorPrimary));

        PictureConfig.init(config); // 先初始化参数配置，在启动相册
        PictureConfig.getPictureConfig().openPhoto(this, resultCallback); // 启动相册并设置回调函数
    }

    /**
     * 图片回调方法
     */
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
            selectedMedia = resultList;
            if (selectedMedia != null) {
                Glide.with(SaleActivity.this).load(selectedMedia.get(0).getPath()).dontAnimate().into(imageView);
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
        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
    }
}
