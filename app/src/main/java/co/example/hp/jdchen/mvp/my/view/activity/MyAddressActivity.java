package co.example.hp.jdchen.mvp.my.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.Activit;
import co.example.hp.jdchen.mvp.my.model.bean.DefaultAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.GetDefaultaddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.MyAddressBean;
import co.example.hp.jdchen.mvp.my.presenter.MyAddressPresenter;
import co.example.hp.jdchen.mvp.my.view.adapter.MyAddressAdapter;
import co.example.hp.jdchen.mvp.my.view.iview.MyAddressIView;
import co.example.hp.jdchen.mvp.my.view.iview.UpdateAddressIView;

/**
 * Created by hp on 2018/7/20.
 */

public class MyAddressActivity extends Activit<MyAddressPresenter> implements MyAddressIView {


    @BindView(R.id.my_address_recycler)
    RecyclerView myAddressRecycler;
    @BindView(R.id.my_address_newaddress)
    TextView myAddressNewaddress;
    private static final String TAG = "MyAddressActivity";
    private int uid;
    private int addrid;
    private int status;
    private MyAddressAdapter myAddressAdapter;

    @Override
    protected MyAddressPresenter getPresenter() {
        return new MyAddressPresenter(this);
    }

    @Override
    protected int getfragmentId() {
        return R.layout.my_address;
    }

    @Override
    protected void initData() {
        SharedPreferences preferences = MyAddressActivity.this.getSharedPreferences("mobile", Context.MODE_PRIVATE);
        boolean flag = preferences.getBoolean("flag", false);
        SharedPreferences.Editor edit = preferences.edit();
        uid = preferences.getInt("uid", 0);
        if (flag) {
            prenter.myaddress(uid);
         //   prenter.getdefaultaddress(uid);
        }


    }

    @Override
    protected void initLisenter() {
        myAddressNewaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddressActivity.this, NewAddressActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void initView() {


    }

    @Override
    public void onMyadressSuccess(MyAddressBean myAddressBean) {

        final List<MyAddressBean.DataBean> data = myAddressBean.getData();
        myAddressAdapter = new MyAddressAdapter(data, MyAddressActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyAddressActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAddressRecycler.setLayoutManager(linearLayoutManager);
        myAddressRecycler.setAdapter(myAddressAdapter);

        myAddressAdapter.setOnUpdateClickLister(new MyAddressAdapter.OnUpdateClickLister() {
            @Override
            public void onUpdateClick(int position) {
                Intent intent = new Intent(MyAddressActivity.this, UpdateAddressActivity.class);
                String name = data.get(position).getName();
                String mobile = data.get(position).getMobile();
                String addr = data.get(position).getAddr();
                status = data.get(position).getStatus();
                addrid = data.get(position).getAddrid();
                intent.putExtra("addrid", addrid);
                  intent.putExtra("name",name);
                intent.putExtra("mobile",mobile);
                intent.putExtra("addr",addr);
                Log.d(TAG, "onUpdateClick:AAAAAAAAAAAAAAAAAA========="+ addrid);
                startActivity(intent);
            }
        });
        myAddressAdapter.setOndefutClickLister(new MyAddressAdapter.OnDefutClickLister() {
            @Override
            public void ondefutClickLister(int position) {
                Toast.makeText(MyAddressActivity.this,"===="+data.get(position).getAddrid(),Toast.LENGTH_SHORT).show();
                status=1;
                prenter.defaultaddress(uid,data.get(position).getAddrid(),1);

            }
        });
    }

    @Override
    public void onMyadressError(String error) {

    }

    @Override
    public void onDefaultadressSuccess(DefaultAddressBean defaultAddressBean) {
                   if (defaultAddressBean.getCode().equals("0")){
                       Toast.makeText(MyAddressActivity.this,"默认地址设置成功",Toast.LENGTH_SHORT).show();
                       initData();
                   }
    }

    @Override
    public void onDefaultadressError(String error) {

    }

    @Override
    public void onGetDefaultadressSuccess(GetDefaultaddressBean getDefaultaddressBean) {
        if (getDefaultaddressBean.getCode().equals("0")){
            Toast.makeText(MyAddressActivity.this,"获取默认地址设置成功",Toast.LENGTH_SHORT).show();
            initData();
        }
    }

    @Override
    public void onGetDefaultadressError(String error) {

    }

    @Override
    public Context context() {
        return null;
    }

}
