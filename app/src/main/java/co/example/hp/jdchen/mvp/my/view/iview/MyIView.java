package co.example.hp.jdchen.mvp.my.view.iview;

import co.example.hp.jdchen.base.BaseIView;
import co.example.hp.jdchen.mvp.my.model.bean.LoginBean;
import co.example.hp.jdchen.mvp.my.model.bean.RegBean;

/**
 * Created by hp on 2018/7/13.
 */

public interface MyIView extends BaseIView{
    //登录
    void onMySuccess(LoginBean loginBean);
    void onMyError(String error);
    //注册
    void onRegSuccess(RegBean regBean);
    void onRegError(String error);

}
