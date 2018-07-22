package co.example.hp.jdchen.mvp.shoppingcar.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.Activit;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.IntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.UpdateIntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.presenter.IntentPresenter;
import co.example.hp.jdchen.mvp.shoppingcar.view.adapter.IntentAdapter;
import co.example.hp.jdchen.mvp.shoppingcar.view.iview.IntentIView;

/**
 * Created by hp on 2018/7/20.
 */

public class IntentActivity extends Activit<IntentPresenter> implements IntentIView {


    @BindView(R.id.shop_intent_recycler)
    RecyclerView shopIntentRecycler;
    @BindView(R.id.shop_intent_unpaid)
    Button shopIntentUnpaid;
    @BindView(R.id.shop_intent_paid)
    Button shopIntentPaid;
    @BindView(R.id.shop_intent_canc)
    Button shopIntentCanc;

    private int uid;
    private float price;
    private int status;
    private int orderid;
    private static final String TAG = "IntentActivity";
    private List<IntentBean.DataBean> list = new ArrayList<>();

    @Override
    protected IntentPresenter getPresenter() {
        return new IntentPresenter(this);
    }

    @Override
    protected int getfragmentId() {
        return R.layout.shop_intent;
    }

    @Override
    protected void initData() {
        SharedPreferences mobile = getSharedPreferences("mobile", context().MODE_PRIVATE);
        boolean flag = mobile.getBoolean("flag", false);
        uid = mobile.getInt("uid", 0);
        if (flag) {
            prenter.intents(uid,status);
        }


    }

    @Override
    protected void initLisenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onIntentSuccess(IntentBean intentBean) {
        final List<IntentBean.DataBean> data = intentBean.getData();
        list.clear();
        list.addAll(data);
        final IntentAdapter intentAdapter = new IntentAdapter(list, IntentActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(IntentActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        shopIntentRecycler.setLayoutManager(linearLayoutManager);
        shopIntentRecycler.setAdapter(intentAdapter);

        intentAdapter.setOnIntentButtonClick(new IntentAdapter.OnIntentButtonClick() {
            @Override
            public void onIntentButton(final int position) {
                orderid = data.get(position).getOrderid();
                status = data.get(position).getStatus();
                final AlertDialog dialog = new AlertDialog.Builder(IntentActivity.this).create();
                final View inflate = View.inflate(IntentActivity.this, R.layout.intenttype, null);
                Button intentTypeBtnNopayment = inflate.findViewById(R.id.intenttype_btn_nopayment);
                Button intentTypeBtnPayment = inflate.findViewById(R.id.intenttype_btn_payment);
                Button intentTypeBtnNull = inflate.findViewById(R.id.intenttype_btn_null);
                dialog.setTitle("请选择订单类型");
                dialog.setView(inflate);
                intentTypeBtnNopayment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        prenter.updateintents(uid,0,data.get(position).getOrderid());
                        dialog.dismiss();
                    }
                });

                intentTypeBtnPayment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        prenter.updateintents(uid,1,data.get(position).getOrderid());
                        dialog.dismiss();
                    }
                });
                intentTypeBtnNull.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        prenter.updateintents(uid,2,data.get(position).getOrderid());
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }

        });


    }

    @Override
    public void onIntentError(String error) {

    }

    @Override
    public void onUpdateIntentSuccess(UpdateIntentBean updateIntentBean) {
                    if (updateIntentBean.getCode().equals("0")){
                        Toast.makeText(IntentActivity.this,"成功",Toast.LENGTH_SHORT).show();
                          initData();
                    }

    }

    @Override
    public void onUpdateIntentError(String error) {

    }

    @Override
    public Context context() {
        return null;
    }

    @OnClick({R.id.shop_intent_unpaid, R.id.shop_intent_paid, R.id.shop_intent_canc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_intent_unpaid:
                      status=0;
              initData();
                break;
            case R.id.shop_intent_paid:
                status=1;
               initData();
                break;
            case R.id.shop_intent_canc:
                status=2;
               initData();
                break;
        }
    }



}
