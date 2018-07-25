package co.example.hp.jdchen.mvp.find.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.BaseActivity;
import co.example.hp.jdchen.base.BasePresenter;
import co.example.hp.jdchen.mvp.find.model.FindBean;
import co.example.hp.jdchen.mvp.find.presenter.FindPresenter;
import co.example.hp.jdchen.mvp.find.view.adapter.MyAdapter;
import co.example.hp.jdchen.mvp.find.view.iview.FindIView;

/**
 * Created by hp on 2018/7/11.
 */

public class FindFragment extends BaseActivity<FindPresenter>implements FindIView{


    private XRecyclerView findRecycler;
    private MyAdapter myAdapter;
    private List<FindBean.ResultBean.DataBean> newslist;

    @Override
    protected FindPresenter getPresenter() {
        return new FindPresenter(this);
    }

    @Override
    protected int getfragmentId() {
        return R.layout.jd_find;
    }

    @Override
    protected void initLisenter() {

    }

    @Override
    protected void initData() {
        prenter.finds();
    }

    @Override
    protected void initView(View view) {
        findRecycler = view.findViewById(R.id.find_recycler);
    }
    @Override
    public void onfindSuccess(FindBean findBean) {
        newslist = findBean.getResult().getData();
        final MyAdapter myAdapter = new MyAdapter(newslist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
          findRecycler.setLayoutManager(linearLayoutManager);
        findRecycler.setAdapter(myAdapter);
        findRecycler.setRefreshProgressStyle(ProgressStyle.BallZigZag); //设定下拉刷新样式
        findRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag);//设定上拉加载样式


        findRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                myAdapter.notifyDataSetChanged();
               findRecycler.loadMoreComplete();
            }

            @Override
            public void onLoadMore() {

                myAdapter.notifyDataSetChanged();
                findRecycler.refreshComplete();
            }
        });







    }


    @Override
    public void onfindError(String error) {

    }

    @Override
    public Context context() {
        return null;
    }


}
