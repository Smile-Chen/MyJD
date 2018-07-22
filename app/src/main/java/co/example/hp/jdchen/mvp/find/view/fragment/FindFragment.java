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


    private RecyclerView findRecycler;
    private MyAdapter myAdapter;

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
        List<FindBean.ResultBean.DataBean> newslist = findBean.getResult().getData();
        MyAdapter myAdapter = new MyAdapter(newslist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
          findRecycler.setLayoutManager(linearLayoutManager);
        findRecycler.setAdapter(myAdapter);


    }

    @Override
    public void onfindError(String error) {

    }

    @Override
    public Context context() {
        return null;
    }


}
