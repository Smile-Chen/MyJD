package co.example.hp.jdchen.mvp.my.presenter;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.my.model.bean.LoginBean;
import co.example.hp.jdchen.mvp.my.model.MyModel;
import co.example.hp.jdchen.mvp.my.view.iview.MyIView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 2018/7/13.
 */

public class MyPresenter extends BasePresenter<MyIView>{
     public MyModel myModel;
    public MyPresenter(MyIView view) {
        super(view);
    }

    @Override
    protected void onModel() {
           myModel=new MyModel();
    }

    //登录
    public void loginbeans(String mobile,String password){
        myModel.loginbeans(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                               if (view!=null){
                                   view.onMySuccess(loginBean);
                               }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onMyError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
