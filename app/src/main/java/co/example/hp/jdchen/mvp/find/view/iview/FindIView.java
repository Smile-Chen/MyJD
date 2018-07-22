package co.example.hp.jdchen.mvp.find.view.iview;

import co.example.hp.jdchen.base.BaseIView;
import co.example.hp.jdchen.mvp.find.model.FindBean;

/**
 * Created by hp on 2018/7/19.
 */

public interface FindIView extends BaseIView{

    void onfindSuccess(FindBean findBean);
    void onfindError(String error);
}
