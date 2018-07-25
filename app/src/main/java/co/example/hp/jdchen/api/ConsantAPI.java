package co.example.hp.jdchen.api;

/**
 * Created by hp on 2018/7/11.
 */

public class ConsantAPI {
    public static final String BASE_URL="https://www.zhaoapi.cn/";

    //登录
    public static final String login_url="user/login";
    //注册
    public static final String reg_url="user/reg";
      //获取头像
    public static final String headfile_url="file/upload";
    //获得用户信息   
    public static final String my_url="user/getUserInfo";

    //搜索框
    public static final String search_url="product/searchProducts";
    //首页广告（轮播图+京东秒杀+最底部的为你推荐）
    public static final String home_url="ad/getAd";

    //详情https://www.zhaoapi.cn/product/getProductDetail
    public static final String shopsearch_url="product/getProductDetail";

    //商品分类接口（此接口用于首页九宫格，和底部页签分类页）
    public static final String home_type_url="product/getCatagory";
     //商品子分类接口
     public static final String classify_childtype_url="product/getProductCatagory";
    public static final String classify_content_url="product/getProducts";
     //购物车
     public static final String shop_url="product/getCarts";
     //加入购物车
     public static final String addcart_url="product/addCart";
     //删除购物车
     public static final String deletecart_url="product/deleteCart";

     //发现
     public static final String find_url="http://v.juhe.cn/toutiao/index?type=top&key=dbedecbcd1899c9785b95cc2d17131c5";

     //订单列表
    public static final String indent_url="product/getOrders";
    //创建订单
    public static final String newindent_url="product/createOrder";
    //修改订单
    public static final String updateindent_url="product/updateOrder";

    //常用收货地址列表
    public static final String myaddress_url="user/getAddrs";
    //添加常用收获地址
    public static final String newaddress_url="user/addAddr";
    //修改常用的收货地址
    public static final String updateaddress_url="user/updateAddr";

    //设置默认地址
    public static final String defaultaddress_url="user/setAddr";
      //获取默认地址
      public static final String getdefaultaddress_url="user/getDefaultAddr";

}
