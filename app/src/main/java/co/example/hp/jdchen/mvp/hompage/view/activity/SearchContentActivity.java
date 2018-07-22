package co.example.hp.jdchen.mvp.hompage.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.Activit;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeSearchBean;
import co.example.hp.jdchen.mvp.hompage.presenter.SearchPresenter;
import co.example.hp.jdchen.mvp.hompage.view.adapter.SearchAdapter;
import co.example.hp.jdchen.mvp.hompage.view.iview.SearchIView;

/**
 * Created by hp on 2018/7/16.
 */

public class SearchContentActivity extends Activit<SearchPresenter> implements SearchIView {
    @BindView(R.id.home_search_recycler)
    RecyclerView homeSearchRecycler;
    @BindView(R.id.search_button_sales)
    Button searchButtonSales;
    @BindView(R.id.search_button_price)
    Button searchButtonPrice;
    @BindView(R.id.search_button_on)
    Button searchButtonOn;
    public String keywords;
    public int sort;
    public int pid;
    private static final String TAG = "SearchContentActivity";
    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSearchSuccess(HomeSearchBean homeSearchBean) {

        final List<HomeSearchBean.DataBean> data = homeSearchBean.getData();
        final SearchAdapter searchAdapter = new SearchAdapter(data, context());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeSearchRecycler.setLayoutManager(gridLayoutManager);
        homeSearchRecycler.setAdapter(searchAdapter);
             searchAdapter.setOnSearchChilkin(new SearchAdapter.OnSearchChilkin() {
                 @Override
                 public void onSearchSuccess(int position) {
                     pid = data.get(position).getPid();
                     Intent intent = new Intent(SearchContentActivity.this, HomeRecommendActivity.class);
                     Bundle bundle = new Bundle();
                     bundle.putInt("pid", pid);
                     intent.putExtras(bundle);
                     startActivity(intent);
                 }
             });

    }

    @Override
    public void onSearchError(String error) {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        keywords = extras.getString("keywords");
        sort = extras.getInt("sort");
        prenter.searchs(keywords, sort);
        searchButtonPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 价格按钮");
                sort=2;
                prenter.searchs(keywords, sort);
                //  searchAdapter.notifyDataSetChanged();
            }
        });
        searchButtonSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 销量按钮");
                sort=1;
                prenter.searchs(keywords, sort);
                //  searchAdapter.notifyDataSetChanged();
            }
        });
        searchButtonOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 默认按钮");
                sort=0;
                prenter.searchs(keywords, sort);
                //  searchAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initLisenter() {

    }

    @Override
    protected void initView() {
        searchButtonPrice = findViewById(R.id.search_button_price);
        searchButtonSales = findViewById(R.id.search_button_sales);
        searchButtonOn = findViewById(R.id.search_button_on);

    }

    @Override
    protected SearchPresenter getPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected int getfragmentId() {
        return R.layout.home_search;
    }

}
