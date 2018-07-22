package co.example.hp.jdchen.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * Created by hp on 2018/7/11.
 */

public abstract class BaseActivity<P extends BasePresenter> extends Fragment{
      protected P prenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getfragmentId(), container, false);
        ButterKnife.bind(getActivity());
        prenter=getPresenter();
             initView(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLisenter();
        initData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
             if (isVisibleToUser){
                 if (prenter==null){
                     return;
                 }else {
                     novisble();
                 }
                 initData();
             }
    }

    private void novisble() {
    }


    protected abstract void initData();
    protected abstract void initLisenter();
    protected abstract void initView(View view);
    protected abstract P getPresenter();
    protected abstract int getfragmentId();



}
