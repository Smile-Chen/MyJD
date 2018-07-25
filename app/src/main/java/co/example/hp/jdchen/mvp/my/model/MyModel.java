package co.example.hp.jdchen.mvp.my.model;

import co.example.hp.jdchen.activity.RetrofitManager;
import co.example.hp.jdchen.api.API;
import co.example.hp.jdchen.mvp.my.model.bean.DefaultAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.GetDefaultaddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.HearFileBean;
import co.example.hp.jdchen.mvp.my.model.bean.LoginBean;
import co.example.hp.jdchen.mvp.my.model.bean.MBean;
import co.example.hp.jdchen.mvp.my.model.bean.MyAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.NewAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.RegBean;
import co.example.hp.jdchen.mvp.my.model.bean.UpdateAddressBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * Created by hp on 2018/7/13.
 */

public class MyModel {
    //登录
      public Observable<LoginBean> loginbeans(String mobile, String password){
     return RetrofitManager.getRetrofit().create(API.class).logins(mobile,password);
    }
    //注册
    public Observable<RegBean> regbeans(String mobile, String password){
        return RetrofitManager.getRetrofit().create(API.class).regs(mobile, password);
    }
    //获取用户信息
    public Observable<MBean> mys(int uid){
        return RetrofitManager.getRetrofit().create(API.class).mybeans(uid);
    }


    //头像
    public Observable<HearFileBean> hearfilebeans(int uid, MultipartBody.Part file){
        return RetrofitManager.getRetrofit().create(API.class).hearfile(uid,file);
    }
    //获取常用收货列表
    public Observable<MyAddressBean> myaddress(int uid){
        return RetrofitManager.getRetrofit().create(API.class).myaddressbean(uid);
    }
    //新增常用收货地址
    public Observable<NewAddressBean> newaddress(int uid,String addr,String mobile,String name){
        return RetrofitManager.getRetrofit().create(API.class).newddressbean(uid, addr, mobile, name);
    }
    //修改常用收货地址
    public Observable<UpdateAddressBean> updateaddress(int uid, int addrid, String mobile, String name){
        return RetrofitManager.getRetrofit().create(API.class).updateaddressbean(uid, addrid, mobile, name);
    }
    //设置默认地址
    public Observable<DefaultAddressBean> defaultaddress(int uid, int addrid,int status){
        return RetrofitManager.getRetrofit().create(API.class).defaultaddressbean(uid, addrid, status);
    }
    //获取默认地址
    public Observable<GetDefaultaddressBean> getdefaultaddress(int uid){
        return RetrofitManager.getRetrofit().create(API.class).getdefaultaddressbean(uid);
    }


}
