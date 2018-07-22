package co.example.hp.jdchen.mvp.shoppingcar.view.iview;

import co.example.hp.jdchen.base.BaseIView;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.NewsIntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.ShopBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.ShopDeleteBean;

/**
 * Created by hp on 2018/7/13.
 */

public interface ShopIView extends BaseIView{
    void onShopSuccess(ShopBean shopBean);
    void onShopError(String error);

    void onDeleteSuccess(ShopDeleteBean shopDeleteBean);
    void onDeleteError(String error);

    void onNewIntentSuccess(NewsIntentBean newsIntentBean);
    void onNewIntentError(String error);
}
