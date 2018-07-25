package co.example.hp.jdchen.mvp.my.view.iview;

import co.example.hp.jdchen.base.BaseIView;
import co.example.hp.jdchen.mvp.my.model.bean.LoginBean;
import co.example.hp.jdchen.mvp.my.model.bean.MBean;
import co.example.hp.jdchen.mvp.my.model.bean.RegBean;

/**
 * Created by hp on 2018/7/13.
 */

public interface MIView extends BaseIView{

    //获取用户信息
    void onMSuccess(MBean mBean);
    void onMError(String error);

}
