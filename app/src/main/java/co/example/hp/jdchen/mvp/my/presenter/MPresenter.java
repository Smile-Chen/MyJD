package co.example.hp.jdchen.mvp.my.presenter;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.my.model.MyModel;
import co.example.hp.jdchen.mvp.my.model.bean.MBean;
import co.example.hp.jdchen.mvp.my.view.iview.MIView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 2018/7/23.
 */

public class MPresenter extends BasePresenter<MIView>{
          public MyModel myModel;

    public MPresenter(MIView view) {
        super(view);
    }

    @Override
    protected void onModel() {
        myModel=new MyModel();
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
                              if (view!=null){
                                  view.onMSuccess(mBean);
                              }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onMError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
