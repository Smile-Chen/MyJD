package co.example.hp.jdchen.mvp.my.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.Activit;
import co.example.hp.jdchen.mvp.my.model.bean.NewAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.UpdateAddressBean;
import co.example.hp.jdchen.mvp.my.presenter.NewAddressPresenter;
import co.example.hp.jdchen.mvp.my.view.iview.NewAddressIView;

/**
 * Created by hp on 2018/7/20.
 */

public class NewAddressActivity extends Activit<NewAddressPresenter> implements NewAddressIView {
    @BindView(R.id.my_newaddress_name)
    EditText myNewaddressName;
    @BindView(R.id.my_newaddress_mobile)
    EditText myNewaddressMobile;
    @BindView(R.id.my_newaddress_addr)
    EditText myNewaddressAddr;
    @BindView(R.id.my_newaddress_save)
    Button myNewaddressSave;
    private int uid;
    public String name;
    private String addr;
    private String mobile;

    @Override
    protected int getfragmentId() {
        return R.layout.my_newaddress;
    }

    @Override
    protected NewAddressPresenter getPresenter() {
        return new NewAddressPresenter(this);
    }
    @Override
    protected void initData() {
        final SharedPreferences preferences = NewAddressActivity.this.getSharedPreferences("mobile", Context.MODE_PRIVATE);
        boolean flag = preferences.getBoolean("flag", false);
        SharedPreferences.Editor edit = preferences.edit();
        uid = preferences.getInt("uid", 0);
        myNewaddressSave.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                name = myNewaddressName.getText().toString();
                mobile = myNewaddressMobile.getText().toString();
                addr = myNewaddressAddr.getText().toString();
                   if (name.equals("")&&mobile.equals("")&&addr.equals("")){
                       Toast.makeText(NewAddressActivity.this,"无内容",Toast.LENGTH_SHORT).show();
                   }else {
                       prenter.newmyaddress(uid,addr,mobile,name);
                   }

            }
        });



    }

    @Override
    protected void initLisenter() {

    }

    @Override
    protected void initView() {

    }


    @Override
    public void onNewadressSuccess(NewAddressBean newAddressBean) {
             if (newAddressBean.getCode().equals("0")){
                 Toast.makeText(NewAddressActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                       finish();
             }else {
                 Toast.makeText(NewAddressActivity.this,newAddressBean.getMsg(),Toast.LENGTH_SHORT).show();
             }
    }

    @Override
    public void onNewadressError(String error) {

    }


    @Override
    public Context context() {
        return null;
    }

}
