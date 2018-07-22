package co.example.hp.jdchen.mvp.classify.presenter;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.classify.model.ClassifyModel;
import co.example.hp.jdchen.mvp.classify.model.bean.ChildContentBean;
import co.example.hp.jdchen.mvp.classify.model.bean.ClassifyBean;
import co.example.hp.jdchen.mvp.classify.view.iview.ClassifyContentIView;
import co.example.hp.jdchen.mvp.classify.view.iview.ClassifyIView;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 2018/7/11.
 */

public class ClassifyContentPresenter extends BasePresenter<ClassifyContentIView>{
    private ClassifyModel classifyModel;

    public ClassifyContentPresenter(ClassifyContentIView view) {
        super(view);
    }
    @Override
    protected void onModel() {
        classifyModel=new ClassifyModel();
    }

    //右边
    public void classifycontents(int pscid,int sort) {
        classifyModel.classifycontents(pscid,sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChildContentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChildContentBean childContentBean) {
                               if (view!=null){
                                   view.onContentSuccess(childContentBean);
                               }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view!=null){
                            view.onContentError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
