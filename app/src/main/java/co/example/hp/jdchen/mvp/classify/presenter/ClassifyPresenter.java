package co.example.hp.jdchen.mvp.classify.presenter;

import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.classify.model.bean.ClassifyBean;
import co.example.hp.jdchen.mvp.classify.model.ClassifyModel;
import co.example.hp.jdchen.mvp.classify.view.iview.ClassifyIView;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hp on 2018/7/11.
 */

public class ClassifyPresenter extends BasePresenter<ClassifyIView>{
    private ClassifyModel classifyModel;
    public ClassifyPresenter(ClassifyIView view) {
        super(view);
    }

    @Override
    protected void onModel() {
        classifyModel=new ClassifyModel();
    }
    //左边分类
 public void classifys(){
     classifyModel.classifylefttype()
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe(new Observer<HomeTypeBean>() {
             @Override
             public void onSubscribe(Disposable d) {
             }
             @Override
             public void onNext(HomeTypeBean homeTypeBean) {
            if (view!=null){
                view.onLeftTypeSuccess(homeTypeBean);
            }
             }
             @Override
             public void onError(Throwable e) {
                 if (view!=null){
                     view.onLeftTypeError(e.toString());
                 }
             }
             @Override
             public void onComplete() {

             }
         });
 }
    //右边
    public void classifyrightchild(int cid) {
        classifyModel.classifyclildtype(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ClassifyBean classifyBean) {
                        if (view != null) {
                            view.onRightTypeSuccess(classifyBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.onRightTypeError(e.toString());
                        }
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }


}
