package co.example.hp.jdchen.mvp.my.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.Activit;
import co.example.hp.jdchen.mvp.my.model.bean.LoginBean;
import co.example.hp.jdchen.mvp.my.model.bean.RegBean;
import co.example.hp.jdchen.mvp.my.presenter.MyPresenter;
import co.example.hp.jdchen.mvp.my.view.iview.MyIView;

/**
 * Created by hp on 2018/7/13.
 */

public class LoginActivity extends Activit<MyPresenter> implements MyIView {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.login_mobile)
    EditText loginMobile;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_startlogin)
    Button loginStartlogin;
    public String mobile;
    public String password;
    @BindView(R.id.lofin_register)
    TextView lofinRegister;
    private SharedPreferences preferences;
    private String icon;
    private String nickname;
    private int uid;

    @Override
    protected void initData() {
        loginStartlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = loginMobile.getText().toString();
                password = loginPassword.getText().toString();
                prenter.loginbeans(mobile, password);
                Log.d(TAG, "initData:aaaaaa=== " + prenter);
            }
        });
    }

    @Override
    protected MyPresenter getPresenter() {
        return new MyPresenter(this);
    }

    @Override
    protected int getfragmentId() {
        return R.layout.my_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initLisenter() {
        lofinRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                Toast.makeText(LoginActivity.this, "快注册成为新用户吧", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


    }

    @Override
    public void onMySuccess(final LoginBean loginBean) {
        Log.d(TAG, "initData: ===" + loginBean.getCode());
        if (loginBean.getCode().equals("0")) {

            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            mobile = loginMobile.getText().toString();
            password = loginPassword.getText().toString();
            icon = loginBean.getData().getIcon();
            uid = loginBean.getData().getUid();
            nickname = loginBean.getData().getNickname();
            SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("mobile", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("mobile", mobile);
            edit.putString("nickname", nickname);
            edit.putString("icon", icon);
            edit.putInt("uid", uid);
            edit.putBoolean("flag", true);
            edit.commit();
            Intent intent = new Intent();
            setResult(2,intent);
            finish();
            Log.d(TAG, "onMySuccess: UID==" + uid);
            Log.d(TAG, "onMySuccess:昵称==" + nickname);
            Toast.makeText(this, loginBean.getData().getMobile().toString() + "欢迎您", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onMyError(String error) {
    }

    @Override
    public void onRegSuccess(RegBean regBean) {

    }

    @Override
    public void onRegError(String error) {

    }

    @Override
    public Context context() {
        return this;
    }

}

