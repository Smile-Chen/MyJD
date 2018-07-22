package co.example.hp.jdchen.mvp.my.view.iview;

import co.example.hp.jdchen.base.BaseIView;
import co.example.hp.jdchen.mvp.my.model.bean.NewAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.UpdateAddressBean;

/**
 * Created by hp on 2018/7/20.
 */

public interface UpdateAddressIView extends BaseIView{

    void onUpdateadressSuccess(UpdateAddressBean updateAddressBean);
    void onUpdateadressError(String error);
}
