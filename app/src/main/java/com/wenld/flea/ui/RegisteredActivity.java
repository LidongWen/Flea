package com.wenld.flea.ui;

import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wenld.flea.R;
import com.wenld.flea.base.BaseActivity;
import com.wenld.flea.base.DefaultNavigationBar;
import com.wenld.flea.common.BaseApiCallback;
import com.wenld.flea.common.ESApi;

/**
 * <p/>
 * Author: wenld on 2017/4/14 16:40.
 * blog: http://www.jianshu.com/u/99f514ea81b3
 * github: https://github.com/LidongWen
 */

public class RegisteredActivity extends BaseActivity implements View.OnClickListener {
    private EditText input_email;
    private EditText input_password;
    private EditText input_nicheng;
    private EditText input_tel;
    private AppCompatButton btn_login;

    @Override
    protected void initData() {

    }


    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_registered);
    }

    @Override
    protected void initListener() {

    }

    protected void initView() {
        input_email = (EditText) findViewById(R.id.input_email);
        input_email.setOnClickListener(this);
        input_password = (EditText) findViewById(R.id.input_password);
        input_password.setOnClickListener(this);
        input_nicheng = (EditText) findViewById(R.id.input_nicheng);
        input_nicheng.setOnClickListener(this);
        input_tel = (EditText) findViewById(R.id.input_tel);
        input_tel.setOnClickListener(this);
        btn_login = (AppCompatButton) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        new DefaultNavigationBar.Builder(this, null).setLeftIcon(R.drawable.activity_return).setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setRight("")
                .setTitle("注册账号").create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String email = input_email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请输入 账号", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = input_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入 密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String nicheng = input_nicheng.getText().toString().trim();
        if (TextUtils.isEmpty(nicheng)) {
            Toast.makeText(this, "请输入 昵称 ", Toast.LENGTH_SHORT).show();
            return;
        }

        String tel = input_tel.getText().toString().trim();
        if (TextUtils.isEmpty(tel)) {
            Toast.makeText(this, "请输入 手机", Toast.LENGTH_SHORT).show();
            return;
        }

        ESApi.account(email, password, nicheng, tel, new BaseApiCallback() {
            @Override
            protected void onAPISuccess(String data) {
                Toast.makeText(RegisteredActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            protected void onAPIFailure(String msg) {
                Toast.makeText(RegisteredActivity.this, "注册失败：" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
