package co.example.hp.jdchen.mvp.shoppingcar.view.iview;

import co.example.hp.jdchen.base.BaseIView;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.IntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.NewsIntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.UpdateIntentBean;

/**
 * Created by hp on 2018/7/20.
 */

public interface IntentIView extends BaseIView{
    void onIntentSuccess(IntentBean intentBean);
    void onIntentError(String error);

    void onUpdateIntentSuccess(UpdateIntentBean updateIntentBean);
    void onUpdateIntentError(String error);


}
