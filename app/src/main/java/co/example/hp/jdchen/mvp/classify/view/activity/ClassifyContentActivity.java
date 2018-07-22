package co.example.hp.jdchen.mvp.classify.view.activity;

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
import butterknife.OnClick;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.Activit;
import co.example.hp.jdchen.mvp.classify.model.bean.ChildContentBean;
import co.example.hp.jdchen.mvp.classify.presenter.ClassifyContentPresenter;
import co.example.hp.jdchen.mvp.classify.view.adapter.ChildContentAdapter;
import co.example.hp.jdchen.mvp.classify.view.iview.ClassifyContentIView;
import co.example.hp.jdchen.mvp.hompage.view.activity.HomeRecommendActivity;

/**
 * Created by hp on 2018/7/17.
 */

public class ClassifyContentActivity extends Activit<ClassifyContentPresenter> implements ClassifyContentIView {

    @BindView(R.id.classify_child_button_sales)
    Button classifyChildButtonSales;
    @BindView(R.id.classify_child_button_price)
    Button classifyChildButtonPrice;
    @BindView(R.id.classify_child_button_on)
    Button classifyChildButtonOn;
    @BindView(R.id.classify_child_recycler)
    RecyclerView classifyChildRecycler;
    private int pscid;

    private static final String TAG = "ClassifyContentActivity";
    private int sort;

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onContentSuccess(ChildContentBean childContentBean) {

        final List<ChildContentBean.DataBean> data = childContentBean.getData();
        ChildContentAdapter childContentAdapter = new ChildContentAdapter(data, ClassifyContentActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ClassifyContentActivity.this, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        classifyChildRecycler.setLayoutManager(gridLayoutManager);
        classifyChildRecycler.setAdapter(childContentAdapter);
        childContentAdapter.setOnChildContentClick(new ChildContentAdapter.OnChildContentClick() {
            @Override
            public void onchildshopSuccess(int position) {
                Intent intent = new Intent(ClassifyContentActivity.this, HomeRecommendActivity.class);
                Bundle bundle = new Bundle();
                int pid = data.get(position).getPid();
                bundle.putInt("pid",pid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onContentError(String error) {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        pscid = extras.getInt("pscid");
        Log.d(TAG, "initData:拿到的PScid===="+ this.pscid);
        prenter.classifycontents(pscid, sort);
    }

    @Override
    protected void initLisenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected ClassifyContentPresenter getPresenter() {
        return new ClassifyContentPresenter(this);
    }

    @Override
    protected int getfragmentId() {
        return R.layout.classify_child;
    }

    @OnClick({R.id.classify_child_button_sales, R.id.classify_child_button_price, R.id.classify_child_button_on})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.classify_child_button_sales:
                sort=1;
                initData();
                break;
            case R.id.classify_child_button_price:
                sort=2;
                initData();
                break;
            case R.id.classify_child_button_on:
                sort=0;
                initData();
                break;
        }
    }


}
