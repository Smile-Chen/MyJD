package co.example.hp.jdchen.mvp.my.view.iview;

import co.example.hp.jdchen.base.BaseIView;
import co.example.hp.jdchen.mvp.my.model.bean.MyAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.NewAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.UpdateAddressBean;

/**
 * Created by hp on 2018/7/20.
 */

public interface NewAddressIView extends BaseIView{

    void onNewadressSuccess(NewAddressBean newAddressBean);
    void onNewadressError(String error);
}
