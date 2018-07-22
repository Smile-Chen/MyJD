package co.example.hp.jdchen.mvp.hompage.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sunfusheng.marqueeview.MarqueeView;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.BaseActivity;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeBean;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;
import co.example.hp.jdchen.mvp.hompage.presenter.HomePresenter;
import co.example.hp.jdchen.mvp.hompage.view.activity.HomeRecommendActivity;
import co.example.hp.jdchen.mvp.hompage.view.activity.HomeSearchActivity;
import co.example.hp.jdchen.mvp.hompage.view.adapter.RecyclerRcommendeAdapter;
import co.example.hp.jdchen.mvp.hompage.view.adapter.RecyclerSkilleAdapter;
import co.example.hp.jdchen.mvp.hompage.view.adapter.RecyclerTypeAdapter;
import co.example.hp.jdchen.mvp.hompage.view.iview.HomeIView;

/**
 * Created by hp on 2018/7/11.
 */

public class HomeFragment extends BaseActivity<HomePresenter> implements HomeIView {
    public int pid;
    @BindView(R.id.title_search)
    EditText titleSearch;
    @BindView(R.id.home_marquee)
    MarqueeView homeMarquee;
    @BindView(R.id.title_sao)
    ImageView titleSao;

    private Banner homeBanner;
    private List<String> list_banner = new ArrayList<>();
    private List<String> list_title = new ArrayList<>();
    private RecyclerView homeRecyclerType;
    private RecyclerView homeRecyclerRecommend;
    private RecyclerView homeRecyclerSkill;
    private static final String TAG = "HomeFragment";

    @Override
    protected int getfragmentId() {
        return R.layout.jd_home;
    }

    @Override
    protected void initView(View view) {
        homeBanner = view.findViewById(R.id.home_banner);
        homeRecyclerType = view.findViewById(R.id.home_recycler_type);
        homeRecyclerRecommend = view.findViewById(R.id.home_recycler_recommend);
        homeRecyclerSkill = view.findViewById(R.id.home_recycler_seckill);
        titleSearch = view.findViewById(R.id.title_search);
        titleSao = view.findViewById(R.id.title_sao);
        homeMarquee = view.findViewById(R.id.home_marquee);

        //跑马灯
        List<String> info = new ArrayList<>();
        info.add("每天喝一杯水....");
        info.add("长时间熬夜容易....");
        info.add("全球杯最新事件....");
        info.add("网红穿着时尚....");
        homeMarquee.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);


    }


    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initLisenter() {
        titleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeSearchActivity.class);
                startActivity(intent);
            }
        });
        titleSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"欢迎进入扫一扫",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onHomeSuccess(HomeBean homeBean) {
        //轮播图
        List<HomeBean.DataBean> data = homeBean.getData();
        for (int i = 0; i < data.size(); i++) {
            String icon = data.get(i).getIcon();
            String title = data.get(i).getTitle();
            list_title.add(title);
            list_banner.add(icon);
        }
        homeBanner.setImageLoader(new GlideImageLoader());
        homeBanner.setImages(list_banner);
        homeBanner.setBannerTitles(list_title);
        homeBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        homeBanner.start();
        //秒杀
        final List<HomeBean.MiaoshaBean.ListBeanX> skilllist = homeBean.getMiaosha().getList();
        Log.d(TAG, "onHomeTypeSuccess: " + skilllist);
        RecyclerSkilleAdapter recyclerSkilleAdapter = new RecyclerSkilleAdapter(skilllist, context());
        GridLayoutManager gridLayoutManagerskill = new GridLayoutManager(getActivity(), 1);
        gridLayoutManagerskill.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeRecyclerSkill.setLayoutManager(gridLayoutManagerskill);
        homeRecyclerSkill.setAdapter(recyclerSkilleAdapter);
        recyclerSkilleAdapter.setOnSkillCilke(new RecyclerSkilleAdapter.OnSkillCilke() {
            @Override
            public void onSkillSuccess(int position) {
                pid = skilllist.get(position).getPid();
                Intent intent = new Intent(getActivity(), HomeRecommendActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pid", pid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //推荐
        final List<HomeBean.TuijianBean.ListBean> recommendlist = homeBean.getTuijian().getList();
        Log.d(TAG, "onHomeTypeSuccess: " + recommendlist);
        RecyclerRcommendeAdapter recyclerRcommendeAdapter = new RecyclerRcommendeAdapter(recommendlist, getContext());
        GridLayoutManager gridLayoutManagerrecommend = new GridLayoutManager(getActivity(), 2);
        gridLayoutManagerrecommend.setOrientation(LinearLayoutManager.VERTICAL);
        homeRecyclerRecommend.setLayoutManager(gridLayoutManagerrecommend);
        homeRecyclerRecommend.setAdapter(recyclerRcommendeAdapter);

        recyclerRcommendeAdapter.setOnRecommendClick(new RecyclerRcommendeAdapter.OnRecommendClick() {


            @Override
            public void onContentSuccess(int position) {
                pid = recommendlist.get(position).getPid();
                Intent intent = new Intent(getActivity(), HomeRecommendActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pid", pid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

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
    public void onHomeError(String error) {

    }

    @Override
    public void onHomeTypeSuccess(HomeTypeBean homeTypeBean) {
        //九宫格分类
        List<HomeTypeBean.DataBean> data = homeTypeBean.getData();
        Log.d(TAG, "onHomeTypeSuccess: " + data.get(0).getIcon());
        RecyclerTypeAdapter recyclerTypeAdapter = new RecyclerTypeAdapter(data, context());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeRecyclerType.setLayoutManager(gridLayoutManager);
        homeRecyclerType.setAdapter(recyclerTypeAdapter);
    }

    @Override
    public void onHomeTypeError(String error) {

    }

    @Override
    protected void initData() {
        prenter.home();
        prenter.homtype();
    }

    @Override
    public Context context() {
        return null;
    }


}
