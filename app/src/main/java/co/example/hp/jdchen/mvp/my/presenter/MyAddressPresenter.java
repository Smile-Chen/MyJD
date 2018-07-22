package co.example.hp.jdchen.mvp.my.presenter;

import android.util.Log;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.my.model.MyModel;
import co.example.hp.jdchen.mvp.my.model.bean.DefaultAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.GetDefaultaddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.HearFileBean;
import co.example.hp.jdchen.mvp.my.model.bean.MyAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.NewAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.UpdateAddressBean;
import co.example.hp.jdchen.mvp.my.view.iview.MyAddressIView;
import co.example.hp.jdchen.mvp.my.view.iview.MyCenterIView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * Created by hp on 2018/7/20.
 */

public class MyAddressPresenter extends BasePresenter<MyAddressIView> {
    public MyModel myModel;

    public MyAddressPresenter(MyAddressIView view) {
        super(view);
    }

    @Override
    protected void onModel() {
        myModel=new MyModel();
    }

    //获取常用地址列表
    public void myaddress(int uid) {
        myModel.myaddress(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyAddressBean myAddressBean) {
                        if (view!=null){
                            view.onMyadressSuccess(myAddressBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onMyadressError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //获取常用地址列表
    public void defaultaddress(int uid,int addrid,int atatus) {
        myModel.defaultaddress(uid, addrid, atatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DefaultAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DefaultAddressBean defaultAddressBean) {
                            if (view!=null){
                                view.onDefaultadressSuccess(defaultAddressBean);
                            }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onDefaultadressError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //获取常用地址列表
    public void getdefaultaddress(int uid) {
        myModel.getdefaultaddress(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetDefaultaddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetDefaultaddressBean getDefaultaddressBean) {
                        if (view!=null){
                            view.onGetDefaultadressSuccess(getDefaultaddressBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onGetDefaultadressError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
