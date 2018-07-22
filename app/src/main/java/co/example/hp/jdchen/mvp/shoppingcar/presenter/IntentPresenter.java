package co.example.hp.jdchen.mvp.shoppingcar.presenter;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.shoppingcar.model.ShopModel;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.IntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.NewsIntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.ShopBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.ShopDeleteBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.UpdateIntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.view.activity.IntentActivity;
import co.example.hp.jdchen.mvp.shoppingcar.view.iview.IntentIView;
import co.example.hp.jdchen.mvp.shoppingcar.view.iview.ShopIView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 2018/7/13.
 */

public class IntentPresenter extends BasePresenter<IntentIView> {
    public ShopModel shopModel;

    public IntentPresenter(IntentIView view) {
        super(view);
        this.shopModel = shopModel;
    }

    @Override
    protected void onModel() {
        shopModel = new ShopModel();
    }

    public void intents(int uid,int status) {
        shopModel.intents(uid,status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IntentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IntentBean intentBean) {
                         if (view!=null){
                             view.onIntentSuccess(intentBean);
                         }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onIntentError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void updateintents(int uid,int status,int orderid) {
        shopModel.updateintents(uid, status, orderid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateIntentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateIntentBean updateIntentBean) {
                              if(view!=null){
                                  view.onUpdateIntentSuccess(updateIntentBean);
                              }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(view!=null){
                            view.onUpdateIntentError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}