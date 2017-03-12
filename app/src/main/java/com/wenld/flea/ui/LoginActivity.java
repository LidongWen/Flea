package com.wenld.flea.ui;

import android.app.ProgressDialog;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wenld.flea.App;
import com.wenld.flea.R;
import com.wenld.flea.base.BaseActivity;
import com.wenld.flea.bean.User;

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

    }

    String email;
    String password;

    public void login() {
        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        email = _emailText.getText().toString();
        password = _passwordText.getText().toString();

// TODO: 2017/3/9  登录
        User user=new User(""+R.mipmap.ic_launcher,"wenld","wenld");
        App.getInstance().user=user;// FastJsonUtil.getObject(data,User.class);
        onLoginSuccess();
//        ESApi.logon(email, password, new BaseApiCallback() {
//            @Override
//            protected void onAPISuccess(String data) {
//                try {
//                    SPUtils.put(LoginActivity.this, SType.Logon_file, SType.Logon_no, email);
//                    SPUtils.put(LoginActivity.this, SType.Logon_file, SType.Logon_pwd, password);
//                    App.getInstance().user= FastJsonUtil.getObject(data,User.class);
//                    onLoginSuccess();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//
//                }
//            }
//
//            @Override
//            protected void onAPIFailure(String msg) {
//                onLoginFailed();
//            }
//        });
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
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

        if (email.isEmpty() ) {
            _emailText.setError("enter a valid user_id");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
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

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }

    private void submit() {
        // validate
        String email = _emailText.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = _passwordText.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}