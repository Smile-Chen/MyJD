package co.example.hp.jdchen.mvp.my.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.BaseActivity;
import co.example.hp.jdchen.mvp.my.model.bean.MBean;
import co.example.hp.jdchen.mvp.my.presenter.MPresenter;
import co.example.hp.jdchen.mvp.my.view.activity.LoginActivity;
import co.example.hp.jdchen.mvp.my.view.activity.MyCenter;
import co.example.hp.jdchen.mvp.my.view.iview.MIView;
import co.example.hp.jdchen.mvp.shoppingcar.view.activity.IntentActivity;

/**
 * Created by hp on 2018/7/11.
 */

public class MyFragment extends BaseActivity<MPresenter>implements MIView{

    private TextView myLogin;
    public SimpleDraweeView  myChatHead;
    private static final String TAG = "MyFragment";
    private String icon;
    private ImageView myIntent;
    private int uid;
    private String nickname;
    private String mobile;


    @Override
    protected int getfragmentId() {
        return R.layout.jd_my;
    }
    @Override
    protected MPresenter getPresenter() {
        return new MPresenter(this);
    }
    @Override
    protected void initData() {
        SharedPreferences preferences = getActivity().getSharedPreferences("mobile", Context.MODE_PRIVATE);
        boolean flag = preferences.getBoolean("flag", false);
        if (flag){
            SharedPreferences.Editor edit = preferences.edit();
            mobile = preferences.getString("mobile", "");
            icon = preferences.getString("icon", "");
            nickname = preferences.getString("nickname", "");
            uid = preferences.getInt("uid",uid);
            Log.d(TAG, "initData:用户id==="+uid);


            prenter.mys(uid);
            myChatHead.setImageURI(Uri.parse(icon));

        }

    }

    @Override
    protected void initLisenter() {
        //头像
        myChatHead.setOnClickListener(new View.OnClickListener() {
            public String name;
            @Override
            public void onClick(View v) {
                if (myLogin.getText().toString().equals("登录")){
                    Toast.makeText(getActivity(),"【请您先进行登录！！！】",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(), MyCenter.class);
                    startActivityForResult(intent,100);
                }
            }
        });
        //登录
        myLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myLogin.getText().toString().equals("登录")){
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent,200);
                }else {
                    Toast.makeText(getActivity(),"【您已登陆！！！】",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //订单
        myIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntentActivity.class);
                startActivity(intent);
            }
        });




    }

    @Override
    protected void initView(View view) {
        myLogin = view.findViewById(R.id.my_login);
        myChatHead = view.findViewById(R.id.my_ChatHead);
        myIntent = view.findViewById(R.id.my_intent);

    }

    @Override
    public void onMSuccess(MBean mBean) {
        if (mBean.getCode().equals("0")){
            String icon = mBean.getData().getIcon();
            myChatHead.setImageURI(Uri.parse(icon));
            if (nickname.equals("")){
                myLogin.setText(mobile);
            }else {
                myLogin.setText(nickname);
            }
        }


    }

    @Override
    public void onMError(String error) {

    }
    @Override
    public Context context() {
        return context();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //头像
        if (requestCode==100 && resultCode==1){
            SharedPreferences preferences = getActivity().getSharedPreferences("mobile", Context.MODE_PRIVATE);
            int uid = preferences.getInt("uid", 0);
            prenter.mys(uid);
        }
        //登录
        if (requestCode==200 && resultCode==2){
            SharedPreferences preferences = getActivity().getSharedPreferences("mobile", Context.MODE_PRIVATE);
            int uid = preferences.getInt("uid", 0);
            prenter.mys(uid);
        }
     //注销
        if (requestCode==100 && resultCode==3){
            myLogin.setText("登录");
            myChatHead.setImageURI("");
           }


    }


}
