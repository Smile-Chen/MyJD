package co.example.hp.jdchen.mvp.classify.view.iview;

import co.example.hp.jdchen.base.BaseIView;
import co.example.hp.jdchen.mvp.classify.model.bean.ChildContentBean;
import co.example.hp.jdchen.mvp.classify.model.bean.ClassifyBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;

/**
 * Created by hp on 2018/7/11.
 */

public interface ClassifyContentIView extends BaseIView{

   void onContentSuccess(ChildContentBean childContentBean);
   void onContentError(String error);
}
