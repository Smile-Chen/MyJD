package co.example.hp.jdchen.mvp.my.presenter;

import android.util.Log;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.my.model.bean.HearFileBean;
import co.example.hp.jdchen.mvp.my.model.MyModel;
import co.example.hp.jdchen.mvp.my.model.bean.MBean;
import co.example.hp.jdchen.mvp.my.model.bean.MyAddressBean;
import co.example.hp.jdchen.mvp.my.view.iview.MyCenterIView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * Created by hp on 2018/7/19.
 */

public class MyCenterPresenter extends BasePresenter<MyCenterIView>{
    public MyModel myModel;
    public MyCenterPresenter(MyCenterIView view) {
        super(view);
    }
    @Override
    protected void onModel() {
        myModel=new MyModel();
    }

    //头像
    public void hearfilebeans(int uid, MultipartBody.Part file){
        myModel.hearfilebeans(uid,file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HearFileBean>() {
                    @Override
                    public void accept(HearFileBean hearFileBean) throws Exception {
                        if (view!=null){
                            if(hearFileBean.getCode().equals("0")){
                                //view.onHeardSuccess(hearFileBean);
                                Log.i("okzzz",hearFileBean.getMsg());
                            }
                        }
                    }
                });
    }
    //g获取用户信息
    public void mys(int uid) {
        myModel.mys(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MBean mBean) {
                        if (view != null) {
                            view.onMSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onMError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    }
