package co.example.hp.jdchen.mvp.find.model;

import co.example.hp.jdchen.activity.RetrofitManager;
import co.example.hp.jdchen.api.API;
import io.reactivex.Observable;

/**
 * Created by hp on 2018/7/19.
 */

public class FindModel {
    public Observable<FindBean>finds(){
        return RetrofitManager.getRetrofit().create(API.class).findbean("http://v.juhe.cn/toutiao/index?type=top&key=dbedecbcd1899c9785b95cc2d17131c5");
    }

}
