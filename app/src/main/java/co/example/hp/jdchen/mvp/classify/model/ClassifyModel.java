package co.example.hp.jdchen.mvp.classify.model;

import co.example.hp.jdchen.activity.RetrofitManager;
import co.example.hp.jdchen.api.API;
import co.example.hp.jdchen.mvp.classify.model.bean.ChildContentBean;
import co.example.hp.jdchen.mvp.classify.model.bean.ClassifyBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;
import io.reactivex.Observable;

/**
 * Created by hp on 2018/7/11.
 */

public class ClassifyModel {
    private static final String TAG = "ClassifyModel";
    //左边分类
    public Observable<HomeTypeBean>classifylefttype(){
        return RetrofitManager.getRetrofit().create(API.class).hometypes();
    }
   //右边子布局
    public Observable<ClassifyBean>classifyclildtype(int cid){
        return RetrofitManager.getRetrofit().create(API.class).classifychild(cid);
    }
    //右边子布局详情内容
    public Observable<ChildContentBean>classifycontents(int pscid, int sort){
        return RetrofitManager.getRetrofit().create(API.class).classifycontent(pscid,sort);
    }

}
