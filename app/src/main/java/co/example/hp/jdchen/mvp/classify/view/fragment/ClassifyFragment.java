package co.example.hp.jdchen.mvp.classify.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.BaseActivity;
import co.example.hp.jdchen.mvp.classify.model.bean.ClassifyBean;
import co.example.hp.jdchen.mvp.classify.presenter.ClassifyPresenter;
import co.example.hp.jdchen.mvp.classify.view.adapter.ClssifyLeftTypeAdapter;
import co.example.hp.jdchen.mvp.classify.view.iview.ClassifyIView;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;
import co.example.hp.jdchen.mvp.hompage.view.activity.HomeSearchActivity;

/**
 * Created by hp on 2018/7/11.
 */

public class ClassifyFragment extends BaseActivity<ClassifyPresenter> implements ClassifyIView {

    @BindView(R.id.title_search)
    EditText titleSearch;
    private ListView classifyListView;
    private FrameLayout classifyFrame;
    private ClassifyRightFragment classifyRightFragment1;
    private static final String TAG = "ClassifyFragment";
    public int cid;

    @Override
    protected int getfragmentId() {
        return R.layout.jd_classify;
    }

    @Override
    protected ClassifyPresenter getPresenter() {
        return new ClassifyPresenter(this);
    }

    @Override
    protected void initData() {
        prenter.classifys();
    }

    @Override
    protected void initView(View view) {
        classifyListView = view.findViewById(R.id.classify_listview);
        classifyFrame = view.findViewById(R.id.classify_frame);
        titleSearch = view.findViewById(R.id.title_search);
        classifyRightFragment1 = new ClassifyRightFragment();

        //fragment管理者
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.classify_frame, classifyRightFragment1);
        Bundle bundle = new Bundle();
        bundle.putInt("cid", 1);
        classifyRightFragment1.setArguments(bundle);
        transaction.commit();
    }

    @Override
    protected void initLisenter() {
        titleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HomeSearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onLeftTypeSuccess(HomeTypeBean homeTypeBean) {


        final List<HomeTypeBean.DataBean> Leftdata = homeTypeBean.getData();
        ClssifyLeftTypeAdapter clssifyLeftTypeAdapter = new ClssifyLeftTypeAdapter(Leftdata, getActivity());
        classifyListView.setAdapter(clssifyLeftTypeAdapter);

        classifyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cid = Leftdata.get(position).getCid();
                Log.d(TAG, "onItemClick:左边==== " + cid);
                //fragment管理者

                ClassifyRightFragment classifyRightFragment = new ClassifyRightFragment();
                FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = supportFragmentManager.beginTransaction();
                transaction.replace(R.id.classify_frame, classifyRightFragment);

                Bundle bundle = new Bundle();
                bundle.putInt("cid", cid);
                classifyRightFragment.setArguments(bundle);
                classifyListView.setSelector(R.color.colortu);
                transaction.commit();
            }
        });


    }

    @Override
    public void onLeftTypeError(String error) {

    }

    @Override
    public void onRightTypeSuccess(ClassifyBean classifyBean) {


    }

    @Override
    public void onRightTypeError(String error) {

    }

}
