package co.example.hp.jdchen.mvp.shoppingcar.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.BaseActivity;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.NewsIntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.ShopBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.ShopDeleteBean;
import co.example.hp.jdchen.mvp.shoppingcar.presenter.ShopPresenter;
import co.example.hp.jdchen.mvp.shoppingcar.view.activity.IntentActivity;
import co.example.hp.jdchen.mvp.shoppingcar.view.adapter.ShopAdapter;
import co.example.hp.jdchen.mvp.shoppingcar.view.iview.ShopIView;

/**
 * Created by hp on 2018/7/11.
 */

public class ShoppingCarFragment extends BaseActivity<ShopPresenter> implements ShopIView {

    private ExpandableListView shopExpandable;
    private CheckBox shopCheckall;
    private TextView shopTotalprice;
    private Button shopSettlement;
    private ShopAdapter shopAdapter;
    private int uid;
    private List<ShopBean.DataBean> shopBeanData;
    private static final String TAG = "ShoppingCarFragment";
    private float totaNumber;
    private float totaPrice;

    @Override
    protected ShopPresenter getPresenter() {
        return new ShopPresenter(this);
    }

    @Override
    protected void initData() {
      SharedPreferences mobile = getActivity().getSharedPreferences("mobile", Context.MODE_PRIVATE);
      boolean flag = mobile.getBoolean("flag", false);
           uid = mobile.getInt("uid",0);
     Log.d(TAG, "initData:拿到的Uid== "+uid);
          if (flag){
              prenter.shops(uid);
           }

    }

    @Override
    protected int getfragmentId() {
        return R.layout.jd_shppingcar;
    }

    @Override
    protected void initView(View view) {
        shopExpandable = view.findViewById(R.id.shop_expandable);
        shopCheckall = view.findViewById(R.id.shop_checkall);
        shopTotalprice = view.findViewById(R.id.shop_totalprice);
        shopSettlement = view.findViewById(R.id.shop_settlement);
    }

    @Override
    protected void initLisenter() {
        shopCheckall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean allCheckedSelected = shopAdapter.isAllCheckedSelected();
                shopAdapter.changedShopAllStatus(!allCheckedSelected);
                shopAdapter.notifyDataSetChanged();
                refreshSelectedAndTotalPriceAndTotalNumber();
            }
        });
       //点击结算跳转到订单页面
        shopSettlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean allCheckedSelected = shopAdapter.isAllCheckedSelected();
                Log.d(TAG, "onClick:价格==== " + totaPrice);
                if (totaPrice >1) {
                    prenter.newintents(uid,totaPrice);
                } else {
                    Toast.makeText(getActivity(), "亲，您还没选中物品呢" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onShopSuccess(ShopBean shopBean) {
        shopBeanData = shopBean.getData();
        shopAdapter = new ShopAdapter(shopBeanData);
             shopAdapter.setShopAdapter(new ShopAdapter.OnCarcheckChangListener() {

                 private int pid;

                 @Override
                 public void onSellerCheckedChang(int groupPosition) {
                     //商家被点击
                     boolean currentSellerAllselectes = shopAdapter.isCurrentSellerAllselectes(groupPosition);
                     shopAdapter.changeSellShopName(groupPosition,!currentSellerAllselectes);
                     //刷新数据
                     shopAdapter.notifyDataSetChanged();
                     refreshSelectedAndTotalPriceAndTotalNumber();
                 }

                 @Override
                 public void onProductCheckedChang(int groupPosition, int childPosition) {
                         //商品
                     shopAdapter.changeSellShopinng(groupPosition,childPosition);
                     shopAdapter.notifyDataSetChanged();
                         //刷新
                     refreshSelectedAndTotalPriceAndTotalNumber();
                 }

                 @Override
                 public void onProductNumberChange(int groupPosition, int childPosition, int numbe) {
                            //加减
                     shopAdapter.changeSellNumber(groupPosition,childPosition,numbe);
                     shopAdapter.notifyDataSetChanged();
                     refreshSelectedAndTotalPriceAndTotalNumber();
                 }

                 @Override
                 public void onDelectCartsChange(int groupPosition, int childPosition) {
                     pid = shopBeanData.get(groupPosition).getList().get(childPosition).getPid();
                     AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                     builder.setMessage("你确定要抛弃我吗？");
                     builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             prenter.deletecarts(uid, pid);
                         }
                     });
                     builder.setNegativeButton("取消",null);
                      builder.show();

                 }
             });
               shopExpandable.setAdapter(shopAdapter);
                for (int i = 0; i < shopBeanData.size(); i++) {
           shopExpandable.expandGroup(i);
       }

        //刷新
        refreshSelectedAndTotalPriceAndTotalNumber();
    }

    @Override
   public void onShopError(String error) {
    }

    @Override
    public void onDeleteSuccess(ShopDeleteBean shopDeleteBean) {
                   initData();
        Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_SHORT).show();
        shopAdapter.notifyDataSetChanged();
        refreshSelectedAndTotalPriceAndTotalNumber();
    }

    @Override
    public void onDeleteError(String error) {

    }

    @Override
    public void onNewIntentSuccess(NewsIntentBean newsIntentBean) {

        if (newsIntentBean.getCode().equals("0")){
            Intent intent = new Intent(getActivity(), IntentActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onNewIntentError(String error) {

    }

    @Override
    public Context context() {
        return null;
    }
    //刷新选中状态和总价
    private void refreshSelectedAndTotalPriceAndTotalNumber() {
        //判断商品是否全被选中
        boolean allCheckedSelected = shopAdapter.isAllCheckedSelected();
        //设置给全选checkbox
        shopCheckall.setChecked(allCheckedSelected);
         //计算总价
        totaPrice = shopAdapter.caculateTotaPrice();
        shopTotalprice.setText("总价： ￥ "+ totaPrice);
        //计算总数量
        totaNumber = shopAdapter.caculateTotaNumber();
        shopSettlement.setText("结算（"+ totaNumber +")");

    }



}
