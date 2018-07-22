package co.example.hp.jdchen.api;



import co.example.hp.jdchen.mvp.classify.model.bean.ChildContentBean;
import co.example.hp.jdchen.mvp.classify.model.bean.ClassifyBean;
import co.example.hp.jdchen.mvp.find.model.FindBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.Bean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeAddCartBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeSearchBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;
import co.example.hp.jdchen.mvp.my.model.bean.DefaultAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.GetDefaultaddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.HearFileBean;
import co.example.hp.jdchen.mvp.my.model.bean.LoginBean;
import co.example.hp.jdchen.mvp.my.model.bean.MyAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.NewAddressBean;
import co.example.hp.jdchen.mvp.my.model.bean.RegBean;
import co.example.hp.jdchen.mvp.my.model.bean.UpdateAddressBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.IntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.NewsIntentBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.ShopBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.ShopDeleteBean;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.UpdateIntentBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by hp on 2018/7/11.
 */

public interface API {
    //登录
       @GET(ConsantAPI.login_url)
       Observable<LoginBean>logins(@Query("mobile") String mobile,@Query("password")String password);
       //注册
    @GET(ConsantAPI.reg_url)
    Observable<RegBean>regs(@Query("mobile") String mobile, @Query("password")String password);
       //获取头像
    @Multipart
       @POST(ConsantAPI.headfile_url)
       Observable<HearFileBean>hearfile(@Query("uid") int uid, @Part MultipartBody.Part file);


       //搜索框
       @GET(ConsantAPI.search_url)
       Observable<HomeSearchBean>searchbeans(@Query("keywords") String keywords, @Query("sort")int sort);


    //首页广告（轮播图+京东秒杀+最底部的为你推荐）
    @GET(ConsantAPI.home_url)
    Observable<HomeBean> homes();

        //商品详情
        @GET(ConsantAPI.shopsearch_url)
        Observable<Bean> shopsearch(@Query("pid")int pid);

    //商品分类接口（此接口用于首页九宫格，和底部页签分类页）
    @GET(ConsantAPI.home_type_url)
    Observable<HomeTypeBean> hometypes();
    //商品子分类接口
    @GET(ConsantAPI.classify_childtype_url)
    Observable<ClassifyBean>classifychild(@Query("cid")int cid);

    //商品子分类接口  详情
    @GET(ConsantAPI.classify_content_url)
    Observable<ChildContentBean>classifycontent(@Query("pscid")int pscid, @Query("sort")int sort);

    //查询购物车
     @GET(ConsantAPI.shop_url)
     Observable<ShopBean>shopcars(@Query("uid")int uid);

     //加入购物车
     @GET(ConsantAPI.addcart_url)
     Observable<HomeAddCartBean>addcartbean(@Query("uid")int uid,@Query("pid")int pid);
     //删除购物车
     @GET(ConsantAPI.deletecart_url)
     Observable<ShopDeleteBean>deletecartbean(@Query("uid")int uid, @Query("pid")int pid);

     //发现
     @GET()
     Observable<FindBean>findbean(@Url String url);

        //订单列表
        @GET(ConsantAPI.indent_url)
        Observable<IntentBean>intentbean(@Query("uid")int uid,@Query("status")int status);
        //创建订单
        @GET(ConsantAPI.newindent_url)
        Observable<NewsIntentBean>newintentbean(@Query("uid")int uid,@Query("price")float price);
          //修改订单状态
          @GET(ConsantAPI.updateindent_url)
          Observable<UpdateIntentBean>updateintentbean(@Query("uid")int uid, @Query("status")int status,@Query("orderid")int orderid);

        //常用收货地址列表
     @GET(ConsantAPI.myaddress_url)
     Observable<MyAddressBean>myaddressbean(@Query("uid")int uid);
     //添加常用收获地址
     @GET(ConsantAPI.newaddress_url)
     Observable<NewAddressBean>newddressbean(@Query("uid")int uid,@Query("addr")String addr,@Query("mobile")String mobile,@Query("name")String name);
    //添加常用收获地址
    @GET(ConsantAPI.updateaddress_url)
    Observable<UpdateAddressBean>updateaddressbean(@Query("uid")int uid, @Query("addrid")int addrid, @Query("mobile")String mobile, @Query("name")String name);
     //设置默认地址
     @GET(ConsantAPI.defaultaddress_url)
     Observable<DefaultAddressBean>defaultaddressbean(@Query("uid")int uid, @Query("addrid")int addrid,@Query("status")int status);
       //获取默认地址
       @GET(ConsantAPI.getdefaultaddress_url)
       Observable<GetDefaultaddressBean>getdefaultaddressbean(@Query("uid")int uid);
}
