package co.example.hp.jdchen.mvp.my.presenter;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.my.model.MyModel;
import co.example.hp.jdchen.mvp.my.model.bean.RegBean;
import co.example.hp.jdchen.mvp.my.view.iview.MyIView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 2018/7/13.
 */

public class RegPresenter extends BasePresenter<MyIView>{
     public MyModel myModel;
    public RegPresenter(MyIView view) {
        super(view);
    }

    @Override
    protected void onModel() {
           myModel=new MyModel();
    }

    //注册
    public void regbeans(String mobile,String password){
        myModel.regbeans(mobile,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegBean regBean) {
                               if (view!=null){
                                   view.onRegSuccess(regBean);
                               }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onRegError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
