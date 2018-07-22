package co.example.hp.jdchen.mvp.hompage.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.Activit;
import co.example.hp.jdchen.mvp.hompage.model.bean.Bean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeAddCartBean;
import co.example.hp.jdchen.mvp.hompage.presenter.ShopSearchPresenter;
import co.example.hp.jdchen.mvp.hompage.view.iview.ShopSearchIView;

/**
 * Created by hp on 2018/7/16.
 */

public class HomeRecommendActivity extends Activit<ShopSearchPresenter> implements ShopSearchIView {

    private static final String TAG = "HomeRecommendActivity";
    public int pid;
    @BindView(R.id.home_recommend_content_image)
    Banner homeRecommendContentImage;
    @BindView(R.id.home_recommend_content_title)
    TextView homeRecommendContentTitle;
    @BindView(R.id.home_recommend_content_sunheard)
    TextView homeRecommendContentSunheard;
    @BindView(R.id.home_recommend_content_price)
    TextView homeRecommendContentPrice;
    @BindView(R.id.home_Btn_addtocar)
    Button homeBtnAddtocar;
    @BindView(R.id.home_Btn_buynow)
    Button homeBtnBuynow;
    private List<String> list_banner = new ArrayList<>();
    private List<String> list_title = new ArrayList<>();
    private int uid;

    @Override
    protected void initData() {
        homeRecommendContentImage = findViewById(R.id.home_recommend_content_image);
        homeRecommendContentTitle = findViewById(R.id.home_recommend_content_title);
        homeRecommendContentSunheard = findViewById(R.id.home_recommend_content_sunheard);
        homeRecommendContentPrice = findViewById(R.id.home_recommend_content_price);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        pid = extras.getInt("pid");
        prenter.shopsearchs(pid);
        Log.d(TAG, "initData: ID===" + pid);


    }

    @Override
    protected void initLisenter() {


    }
    //加入购物车和立即购买
    @OnClick({R.id.home_Btn_addtocar, R.id.home_Btn_buynow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_Btn_addtocar:
                SharedPreferences mobile = getSharedPreferences("mobile", Context.MODE_PRIVATE);
                boolean flag = mobile.getBoolean("flag", false);
                uid = mobile.getInt("uid", 0);
                if (flag){
                    prenter.addcarts(uid,pid);
                }else {
                    Toast.makeText(HomeRecommendActivity.this,"先去登录吧",Toast.LENGTH_SHORT).show();

                }


                break;
            case R.id.home_Btn_buynow:
                Toast.makeText(HomeRecommendActivity.this,"上帝，请先检查您的钱包     稍后为您服务",Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    protected void initView() {

    }

    @Override
    protected ShopSearchPresenter getPresenter() {
        return new ShopSearchPresenter(this);
    }

    @Override
    protected int getfragmentId() {
        return R.layout.home_recommend_content;
    }


    @Override
    public void onShopSearchSuccess(Bean bean) {
        Bean.DataBean data = bean.getData();
        homeRecommendContentTitle.setText(data.getTitle());
        homeRecommendContentSunheard.setText(data.getSubhead());
        homeRecommendContentPrice.setText("￥ " + data.getPrice());
        String[] icon = data.getImages().split("\\|");
        List<String> list = Arrays.asList(icon);
        String title = data.getTitle();
        //轮播图
        for (int i = 0; i < list.size(); i++) {
            list_title.add(title);
            list_banner.add(icon[0]);
        }
        homeRecommendContentImage.setImageLoader(new GlideImageLoader());
        homeRecommendContentImage.setImages(list);
        homeRecommendContentImage.setBannerTitles(list_title);
        homeRecommendContentImage.setBannerStyle(BannerConfig.NUM_INDICATOR);
        homeRecommendContentImage.start();


    }

    @Override
    public void onShopSearchError(String error) {

    }

    @Override
    public void onAddCartSuccess(HomeAddCartBean homeAddCartBean) {
        String code = homeAddCartBean.getCode();
        String msg = homeAddCartBean.getMsg();
        if (code.equals("0")){
            Toast.makeText(HomeRecommendActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(HomeRecommendActivity.this,"=="+msg,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onAddCartError(String error) {

    }

    //轮播图
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }

        @Override
        public ImageView createImageView(Context context) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
            return simpleDraweeView;
        }
    }

    @Override
    public Context context() {
        return null;
    }

}
