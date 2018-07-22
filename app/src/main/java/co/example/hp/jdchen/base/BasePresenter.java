package co.example.hp.jdchen.base;

/**
 * Created by hp on 2018/7/11.
 */

public abstract class BasePresenter<V extends BaseIView> {
    protected V view;

    public BasePresenter(V view) {
        this.view = view;
        onModel();
    }

    protected abstract void onModel();

}
