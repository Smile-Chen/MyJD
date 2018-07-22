package co.example.hp.jdchen.mvp.classify.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import java.util.List;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.BaseActivity;
import co.example.hp.jdchen.mvp.classify.model.bean.ClassifyBean;
import co.example.hp.jdchen.mvp.classify.presenter.ClassifyPresenter;
import co.example.hp.jdchen.mvp.classify.view.adapter.ClassifyRightAdapter;
import co.example.hp.jdchen.mvp.classify.view.iview.ClassifyIView;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;

/**
 * Created by hp on 2018/7/12.
 */

public class ClassifyRightFragment extends BaseActivity<ClassifyPresenter>implements ClassifyIView{


    private RecyclerView classifyRightRecycler;
    private static final String TAG = "ClassifyRightFragment";
    private int cid;

    @Override
    protected ClassifyPresenter getPresenter() {
        return new ClassifyPresenter(this);
    }

    @Override
    protected int getfragmentId() {
        return R.layout.classify_recycler;
    }
    @Override
    protected void initData() {
        Log.d(TAG, "initData: "+"jghhhhhhhhhhhhhhhhhhhhhhhhhhhj");
        prenter.classifyrightchild(cid);
    }

    @Override
    protected void initLisenter() {
          Bundle arguments = getArguments();
        cid = arguments.getInt("cid");
        Log.d(TAG, "initView:++++++++++ 右边=="+cid);
    }

    @Override
    protected void initView(View view) {
        classifyRightRecycler = view.findViewById(R.id.classify_right_recycler);

    }

   @Override
    public Context context() {
        return null;
    }

    @Override
    public void onLeftTypeSuccess(HomeTypeBean homeTypeBean) {



    }

    @Override
    public void onLeftTypeError(String error) {

    }

    @Override
    public void onRightTypeSuccess(ClassifyBean classifyBean) {
        List<ClassifyBean.DataBean> datas = classifyBean.getData();
        ClassifyRightAdapter classifyRightAdapter = new ClassifyRightAdapter(datas, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        classifyRightRecycler.setLayoutManager(linearLayoutManager);
        classifyRightRecycler.setAdapter(classifyRightAdapter);
    


    }

    @Override
    public void onRightTypeError(String error) {

    }

}
