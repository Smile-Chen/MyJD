package co.example.hp.jdchen.mvp.find.presenter;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.find.model.FindBean;
import co.example.hp.jdchen.mvp.find.model.FindModel;
import co.example.hp.jdchen.mvp.find.view.iview.FindIView;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 2018/7/19.
 */

public class FindPresenter extends BasePresenter<FindIView> {
      protected FindModel findModel;

    public FindPresenter(FindIView view) {
        super(view);
    }

    @Override
    protected void onModel() {
        findModel=new FindModel();
    }
    //推荐
    public void finds() {
        findModel.finds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindBean findBean) {
                           if (view!=null){
                               view.onfindSuccess(findBean);
                           }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onfindError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
