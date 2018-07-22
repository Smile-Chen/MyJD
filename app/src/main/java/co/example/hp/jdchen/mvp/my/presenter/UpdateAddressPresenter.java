package co.example.hp.jdchen.mvp.my.presenter;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.my.model.MyModel;
import co.example.hp.jdchen.mvp.my.model.bean.NewAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.UpdateAddressBean;
import co.example.hp.jdchen.mvp.my.view.iview.NewAddressIView;
import co.example.hp.jdchen.mvp.my.view.iview.UpdateAddressIView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 2018/7/20.
 */

public class UpdateAddressPresenter extends BasePresenter<UpdateAddressIView> {
    public MyModel myModel;

    public UpdateAddressPresenter(UpdateAddressIView view) {
        super(view);
    }

    @Override
    protected void onModel() {
        myModel=new MyModel();
    }

    //修改常用地址列表
    public void updateaddress(int uid,int addrid,String mobile,String name) {
        myModel.updateaddress(uid, addrid, mobile, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateAddressBean updateAddressBean) {
                        if (view!=null){
                            view.onUpdateadressSuccess(updateAddressBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onUpdateadressError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
