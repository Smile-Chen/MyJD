package co.example.hp.jdchen.mvp.classify.view.iview;

import co.example.hp.jdchen.base.BaseIView;
import co.example.hp.jdchen.mvp.classify.model.bean.ClassifyBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;

/**
 * Created by hp on 2018/7/11.
 */

public interface ClassifyIView extends BaseIView{

   void onLeftTypeSuccess(HomeTypeBean homeTypeBean);
   void onLeftTypeError(String error);

   void onRightTypeSuccess(ClassifyBean classifyBean);
   void onRightTypeError(String error);
}
