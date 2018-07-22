package co.example.hp.jdchen.mvp.my.presenter;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.my.model.MyModel;
import co.example.hp.jdchen.mvp.my.model.bean.MyAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.NewAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.UpdateAddressBean;
import co.example.hp.jdchen.mvp.my.view.iview.MyAddressIView;
import co.example.hp.jdchen.mvp.my.view.iview.NewAddressIView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 2018/7/20.
 */

public class NewAddressPresenter extends BasePresenter<NewAddressIView> {
    public MyModel myModel;

    public NewAddressPresenter(NewAddressIView view) {
        super(view);
    }

    @Override
    protected void onModel() {
        myModel=new MyModel();
    }

    //获取常用地址列表
    public void newmyaddress(int uid,String addr,String mobile,String name) {
        myModel.newaddress(uid, addr, mobile, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewAddressBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewAddressBean newAddressBean) {
                        if (view!=null){
                            view.onNewadressSuccess(newAddressBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onNewadressError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
