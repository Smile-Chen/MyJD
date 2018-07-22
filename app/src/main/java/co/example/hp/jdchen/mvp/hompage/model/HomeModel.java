package co.example.hp.jdchen.mvp.hompage.model;

import co.example.hp.jdchen.activity.RetrofitManager;
import co.example.hp.jdchen.api.API;
import co.example.hp.jdchen.mvp.hompage.model.bean.Bean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeAddCartBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeSearchBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;
import io.reactivex.Observable;

/**
 * Created by hp on 2018/7/11.
 */

public class HomeModel {
    //轮播图
    public Observable<HomeBean>home(){
        return RetrofitManager.getRetrofit().create(API.class).homes();
    }
    //商品详情
    public Observable<Bean>shopsearchs(int pid){
        return RetrofitManager.getRetrofit().create(API.class).shopsearch(pid);
    }

    //九宫格分类
    public Observable<HomeTypeBean>homtype(){
        return RetrofitManager.getRetrofit().create(API.class).hometypes();
    }
    //搜索框
    public Observable<HomeSearchBean>searchs(String keywords,int sort){
        return RetrofitManager.getRetrofit().create(API.class).searchbeans(keywords,sort);
    }
   //添加到购物车
    public Observable<HomeAddCartBean>addcarts(int uid,int pid){
        return RetrofitManager.getRetrofit().create(API.class).addcartbean(uid, pid);
    }


}
