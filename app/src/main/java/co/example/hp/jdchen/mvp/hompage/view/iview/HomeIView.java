package co.example.hp.jdchen.mvp.hompage.view.iview;

import co.example.hp.jdchen.base.BaseIView;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;

/**
 * Created by hp on 2018/7/11.
 */

public interface HomeIView extends BaseIView{
   void onHomeSuccess(HomeBean homeBean);
   void onHomeError(String error);

   void onHomeTypeSuccess(HomeTypeBean homeTypeBean);
   void onHomeTypeError(String error);

}
