package co.example.hp.jdchen.mvp.my.view.iview;

import co.example.hp.jdchen.base.BaseIView;
import co.example.hp.jdchen.mvp.my.model.bean.HearFileBean;
import co.example.hp.jdchen.mvp.my.model.bean.MyAddressBean;

/**
 * Created by hp on 2018/7/13.
 */

public interface MyCenterIView extends BaseIView{
    //头像
    void onHeardSuccess(HearFileBean hearFileBean);
    void onHeardError(String error);


}
