package com.wenld.flea.ui;

import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wenld.commontools.FastJsonUtil;
import com.wenld.commontools.SPUtils;
import com.wenld.flea.App;
import com.wenld.flea.R;
import com.wenld.flea.base.BaseActivity;
import com.wenld.flea.bean.User;
import com.wenld.flea.common.BaseApiCallback;
import com.wenld.flea.common.ESApi;
import com.wenld.flea.common.SType;
import com.wenld.flea.ui.fragment.more.LogonEvent;

import org.greenrobot.eventbus.EventBus;

import static com.wenld.flea.R.id.btn_login;
import static com.wenld.flea.R.id.input_email;
import static com.wenld.flea.R.id.input_password;
import static com.wenld.flea.R.id.link_signup;

/**
 * <p/>
 * Author: 温利东 on 2017/3/9 15:34.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";

    private EditText _emailText;
    private EditText _passwordText;
    private AppCompatButton _loginButton;
    private TextView _signupLink;


    @Override
    protected void initData() {
        _emailText.setText((String) SPUtils.get(LoginActivity.this, SType.Logon_file, SType.Logon_no, ""));
        _passwordText.setText((String) SPUtils.get(LoginActivity.this, SType.Logon_file, SType.Logon_pwd, ""));
//        login();
    }

    String email;
    String password;

    public void login() {
        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        email = _emailText.getText().toString();
        password = _passwordText.getText().toString();

        ESApi.logon(email, password, new BaseApiCallback() {
            @Override
            protected void onAPISuccess(String data) {
                try {
                    SPUtils.put(LoginActivity.this, SType.Logon_file, SType.Logon_no, email);
                    SPUtils.put(LoginActivity.this, SType.Logon_file, SType.Logon_pwd, password);
                    App.getInstance().user = FastJsonUtil.getObject(data, User.class);
                    onLoginSuccess();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onAPIFailure(String msg) {
                onLoginFailed();
            }
        });
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        EventBus.getDefault().post(new LogonEvent());
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty()) {
            _emailText.setError("不能为空");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("密码 4-10 位");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }

    protected void initView() {

        _emailText = (EditText) findViewById(input_email);
        _passwordText = (EditText) findViewById(input_password);
        _loginButton = (AppCompatButton) findViewById(btn_login);
        _signupLink = (TextView) findViewById(link_signup);

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initListener() {
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

}