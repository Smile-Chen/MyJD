package co.example.hp.jdchen.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * Created by hp on 2018/7/11.
 */

public abstract class Activit<P extends BasePresenter> extends AppCompatActivity{
      protected P prenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getfragmentId());
        ButterKnife.bind(this);
        prenter=getPresenter();
        initView();
        initLisenter();
        initData();

    }

    protected abstract void initData();
    protected abstract void initLisenter();
    protected abstract void initView();
    protected abstract P getPresenter();
    protected abstract int getfragmentId();



}
