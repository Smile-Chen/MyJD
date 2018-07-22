package co.example.hp.jdchen.mvp.hompage.presenter;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeBean;
import co.example.hp.jdchen.mvp.hompage.model.HomeModel;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;
import co.example.hp.jdchen.mvp.hompage.view.iview.HomeIView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 2018/7/11.
 */

public class HomePresenter extends BasePresenter<HomeIView>{
        private HomeModel homeModel;
    public HomePresenter(HomeIView view) {
        super(view);
    }

    @Override
    protected void onModel() {
        homeModel=new HomeModel();
    }
    //轮播图
    public void home(){
        homeModel.home()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (view!=null){
                            view.onHomeSuccess(homeBean);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onHomeError(e.toString());
                        }
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
    //九宫格分类
 public void homtype(){
     homeModel.homtype()
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe(new Observer<HomeTypeBean>() {
             @Override
             public void onSubscribe(Disposable d) {

             }

             @Override
             public void onNext(HomeTypeBean homeTypeBean) {
            if (view!=null){
                view.onHomeTypeSuccess(homeTypeBean);
            }
             }

             @Override
             public void onError(Throwable e) {
                 if (view!=null){
                     view.onHomeTypeError(e.toString());
                 }
             }

             @Override
             public void onComplete() {

             }
         });
 }




}
