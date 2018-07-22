package co.example.hp.jdchen.mvp.hompage.view.iview;

import co.example.hp.jdchen.base.BaseIView;
import co.example.hp.jdchen.mvp.hompage.model.bean.Bean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeAddCartBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeSearchBean;

/**
 * Created by hp on 2018/7/11.
 */

public interface ShopSearchIView extends BaseIView{
   void onShopSearchSuccess(Bean Bean);
   void onShopSearchError(String error);

   void onAddCartSuccess(HomeAddCartBean homeAddCartBean);
   void onAddCartError(String error);

}
