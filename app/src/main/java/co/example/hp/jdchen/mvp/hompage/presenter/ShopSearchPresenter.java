package co.example.hp.jdchen.mvp.hompage.presenter;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.hompage.model.HomeModel;
import co.example.hp.jdchen.mvp.hompage.model.bean.Bean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeAddCartBean;
import co.example.hp.jdchen.mvp.hompage.view.iview.ShopSearchIView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 2018/7/11.
 */

public class ShopSearchPresenter extends BasePresenter<ShopSearchIView>{
        private HomeModel homeModel;

    public ShopSearchPresenter(ShopSearchIView view) {
        super(view);
    }

    @Override
    protected void onModel() {
        homeModel=new HomeModel();
    }
    //商品详情
    public void shopsearchs(int pid){
        homeModel.shopsearchs(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Bean bean) {
                        if (view!=null){
                            view.onShopSearchSuccess(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onShopSearchError(e.toString());
                        }
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
    public void addcarts(int uid,int pid){
        homeModel.addcarts(uid, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeAddCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeAddCartBean homeAddCartBean) {
                        if (view!=null){
                            view.onAddCartSuccess(homeAddCartBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onAddCartError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
