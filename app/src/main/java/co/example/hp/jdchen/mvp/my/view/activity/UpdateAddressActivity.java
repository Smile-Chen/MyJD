package co.example.hp.jdchen.mvp.my.view.activity;

import android.content.Context;
import android.content.Intent;
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
import co.example.hp.jdchen.mvp.my.presenter.UpdateAddressPresenter;
import co.example.hp.jdchen.mvp.my.view.iview.NewAddressIView;
import co.example.hp.jdchen.mvp.my.view.iview.UpdateAddressIView;

/**
 * Created by hp on 2018/7/20.
 */

public class UpdateAddressActivity extends Activit<UpdateAddressPresenter> implements UpdateAddressIView {

    @BindView(R.id.my_updateaddress_name)
    EditText myUpdateaddressName;
    @BindView(R.id.my_updateaddress_mobile)
    EditText myUpdateaddressMobile;
    @BindView(R.id.my_updateaddress_addr)
    EditText myUpdateaddressAddr;
    @BindView(R.id.my_updateaddress_save)
    Button myUpdateaddressSave;
    private int uid;
    public String name;
    private String addr;
    private String mobile;
    private int addrid;

    @Override
    protected int getfragmentId() {
        return R.layout.my_updateaddress;
    }



    @Override
    protected void initData() {

        myUpdateaddressSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = myUpdateaddressName.getText().toString();
                mobile = myUpdateaddressMobile.getText().toString();
                addr = myUpdateaddressAddr.getText().toString();
                if (UpdateAddressActivity.this.name.equals("") && mobile.equals("") && addr.equals("")) {
                    Toast.makeText(UpdateAddressActivity.this, "无内容", Toast.LENGTH_SHORT).show();
                } else {
                    prenter.updateaddress(uid, addrid, mobile,name);

                }

            }
        });


    }

    @Override
    protected void initLisenter() {

    }

    @Override
    protected void initView() {
        SharedPreferences preferences = UpdateAddressActivity.this.getSharedPreferences("mobile", Context.MODE_PRIVATE);
        boolean flag = preferences.getBoolean("flag", false);
        SharedPreferences.Editor edit = preferences.edit();
        uid = preferences.getInt("uid", 0);
        Intent intent = getIntent();
        addrid = intent.getIntExtra("addrid", 0);
        String name1 = intent.getStringExtra("name");
        String mobile1 = intent.getStringExtra("mobile");
        String addr1 = intent.getStringExtra("addr");
        Toast.makeText(this,"===="+addrid,Toast.LENGTH_SHORT).show();

        myUpdateaddressName.setText(name1);
        myUpdateaddressMobile.setText(mobile1);
        myUpdateaddressAddr.setText(addr1);

    }

    @Override
    protected UpdateAddressPresenter getPresenter() {
        return new UpdateAddressPresenter(this);
    }


    @Override
    public void onUpdateadressSuccess(UpdateAddressBean updateAddressBean) {

        if (updateAddressBean.getCode().equals("0")) {
            Toast.makeText(UpdateAddressActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(UpdateAddressActivity.this, updateAddressBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpdateadressError(String error) {

    }

    @Override
    public Context context() {
        return null;
    }

}
