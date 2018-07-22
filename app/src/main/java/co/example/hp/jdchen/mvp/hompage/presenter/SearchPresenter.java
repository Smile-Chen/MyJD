package co.example.hp.jdchen.mvp.hompage.presenter;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.hompage.model.HomeModel;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeSearchBean;
import co.example.hp.jdchen.mvp.hompage.view.iview.HomeIView;
import co.example.hp.jdchen.mvp.hompage.view.iview.SearchIView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 2018/7/16.
 */

public class SearchPresenter extends BasePresenter<SearchIView>{
    protected HomeModel homeModel;

    public SearchPresenter(SearchIView view) {
        super(view);
    }

    @Override
    protected void onModel() {
        homeModel=new HomeModel();
    }

    //推荐
    public void searchs(String keywords,int sort){
        homeModel.searchs(keywords, sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeSearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeSearchBean homeSearchBean) {
                              if (view!=null){
                                  view.onSearchSuccess(homeSearchBean);
                              }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onSearchError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
