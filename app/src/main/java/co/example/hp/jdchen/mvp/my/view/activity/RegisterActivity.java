package co.example.hp.jdchen.mvp.my.view.activity;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.Activit;
import co.example.hp.jdchen.mvp.my.model.bean.LoginBean;
import co.example.hp.jdchen.mvp.my.model.bean.RegBean;
import co.example.hp.jdchen.mvp.my.presenter.RegPresenter;
import co.example.hp.jdchen.mvp.my.view.iview.MyIView;

/**
 * Created by hp on 2018/7/13.
 */

public class RegisterActivity extends Activit<RegPresenter> implements MyIView {


    @BindView(R.id.my_reg_et_mobile)
    EditText myRegEtMobile;
    @BindView(R.id.my_reg_et_password)
    EditText myRegEtPassword;
    @BindView(R.id.my_reg_btn_reg)
    Button myRegBtnReg;

    @Override
    protected RegPresenter getPresenter() {
        return new RegPresenter(this);
    }

    @Override
    protected int getfragmentId() {
        return R.layout.my_register;
    }

    @Override
    protected void initData() {
        myRegBtnReg.setOnClickListener(new View.OnClickListener() {

            private String password;
            private String mobile;

            @Override
            public void onClick(View v) {
                mobile = myRegEtMobile.getText().toString();
                password = myRegEtPassword.getText().toString();
               prenter.regbeans(mobile,password);
            }
        });

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onMySuccess(LoginBean loginBean) {


    }

    @Override
    public void onMyError(String error) {

    }

    @Override
    public void onRegSuccess(RegBean regBean) {
        if (regBean.getCode().equals("0")) {
            Toast.makeText(this, "恭喜您，注册成功，", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "快去登录吧", Toast.LENGTH_LONG).show();
            finish();

        } else {
            Toast.makeText(this,regBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRegError(String error) {

    }

    @Override
    protected void initLisenter() {

    }

    @Override
    public Context context() {
        return null;
    }



}
