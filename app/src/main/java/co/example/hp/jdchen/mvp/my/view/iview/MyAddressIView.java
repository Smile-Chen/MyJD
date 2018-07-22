package co.example.hp.jdchen.mvp.my.view.iview;

import co.example.hp.jdchen.base.BaseIView;
import co.example.hp.jdchen.mvp.my.model.bean.DefaultAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.GetDefaultaddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.MyAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.UpdateAddressBean;

/**
 * Created by hp on 2018/7/20.
 */

public interface MyAddressIView extends BaseIView{

    void onMyadressSuccess(MyAddressBean myAddressBean);
    void onMyadressError(String error);

    void onDefaultadressSuccess(DefaultAddressBean defaultAddressBean);
    void onDefaultadressError(String error);

    void onGetDefaultadressSuccess(GetDefaultaddressBean getDefaultaddressBean);
    void onGetDefaultadressError(String error);

}
