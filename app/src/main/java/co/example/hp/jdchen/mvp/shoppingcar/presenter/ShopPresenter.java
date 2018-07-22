package co.example.hp.jdchen.mvp.shoppingcar.presenter;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.NewsIntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.ShopBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.ShopDeleteBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.ShopModel;
import co.example.hp.jdchen.mvp.shoppingcar.view.iview.ShopIView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 2018/7/13.
 */

public class ShopPresenter extends BasePresenter<ShopIView>{
     public ShopModel shopModel;
    public ShopPresenter(ShopIView view) {
        super(view);
    }

    @Override
    protected void onModel() {
       shopModel=new ShopModel();
    }
    public void shops(int uid){
       shopModel.shops(uid)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<ShopBean>() {
                   @Override
                   public void onSubscribe(Disposable d) {
                   }
                   @Override
                   public void onNext(ShopBean shopBean) {
                                 if (view!=null){
                                     view.onShopSuccess(shopBean);
                                 }
                   }
                   @Override
                   public void onError(Throwable e) {
                       if (view!=null){
                           view.onShopError(e.toString());
                       }
                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }
    public void deletecarts(int uid,int pid){
        shopModel.deletecarts(uid,pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopDeleteBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopDeleteBean shopDeleteBean) {
                              if (view!=null){
                                  view.onDeleteSuccess(shopDeleteBean);
                              }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onDeleteError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void newintents(int uid,float price) {
        shopModel.newsintents(uid,price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsIntentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsIntentBean newsIntentBean) {

                        if (view!=null){
                            view.onNewIntentSuccess(newsIntentBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (view!=null){
                            view.onNewIntentError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
