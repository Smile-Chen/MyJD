package co.example.hp.jdchen.activity;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.classify.view.fragment.ClassifyFragment;
import co.example.hp.jdchen.mvp.find.view.fragment.FindFragment;
import co.example.hp.jdchen.mvp.hompage.view.fragment.HomeFragment;
import co.example.hp.jdchen.mvp.my.view.fragment.MyFragment;
import co.example.hp.jdchen.mvp.shoppingcar.view.fragment.ShoppingCarFragment;


/**
 * Created by hp on 2018/7/11.
 */

public class MyJD extends AppCompatActivity{

    private RadioGroup mainRadio;
    private FrameLayout fragment;
    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private FindFragment findFragment;
    private ShoppingCarFragment shoppingCarFragment;
    private MyFragment myFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.acticity_jd);
        fragment = findViewById(R.id.main_frame);
        mainRadio = findViewById(R.id.main_radio);
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        findFragment = new FindFragment();
        shoppingCarFragment = new ShoppingCarFragment();
        myFragment = new MyFragment();

        mainRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_button01:
                        getfragment(homeFragment);
                    break;
                    case R.id.main_button02:
                        getfragment(classifyFragment);
                        break;
                    case R.id.main_button03:
                        getfragment(findFragment);
                        break;
                    case R.id.main_button04:
                        getfragment(shoppingCarFragment);
                        break;
                    case R.id.main_button05:
                        getfragment(myFragment);
                        break;
                }
            }
        });
        mainRadio.check(R.id.main_button01);

    }
    public void getfragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
              transaction.replace(R.id.main_frame,fragment);
              transaction.commit();
    }


}
