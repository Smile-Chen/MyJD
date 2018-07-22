package co.example.hp.jdchen.mvp.shoppingcar.model;


import co.example.hp.jdchen.activity.RetrofitManager;
import co.example.hp.jdchen.api.API;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.IntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.NewsIntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.ShopBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.ShopDeleteBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.UpdateIntentBean;
import io.reactivex.Observable;

/**
 * Created by hp on 2018/7/13.
 */

public class ShopModel {
//购物车
    public Observable<ShopBean> shops(int uid){
        return RetrofitManager.getRetrofit().create(API.class).shopcars(uid);
    }
//删除购物车
public Observable<ShopDeleteBean> deletecarts(int uid, int pid){
    return RetrofitManager.getRetrofit().create(API.class).deletecartbean(uid, pid);

}
//获取订单列表
public Observable<IntentBean> intents(int uid,int status){
    return RetrofitManager.getRetrofit().create(API.class).intentbean(uid,status);
}
//添加订单
public Observable<NewsIntentBean> newsintents(int uid,float price) {
    return RetrofitManager.getRetrofit().create(API.class).newintentbean(uid, price);
}
//修改订单状态
public Observable<UpdateIntentBean> updateintents(int uid,int status,int orderid) {
    return RetrofitManager.getRetrofit().create(API.class).updateintentbean(uid, status, orderid);
}


}
