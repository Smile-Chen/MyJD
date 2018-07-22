package co.example.hp.jdchen.mvp.my.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.my.view.activity.LoginActivity;
import co.example.hp.jdchen.mvp.my.view.activity.MyCenter;
import co.example.hp.jdchen.mvp.shoppingcar.view.activity.IntentActivity;

/**
 * Created by hp on 2018/7/11.
 */

public class MyFragment extends Fragment{

    private TextView myLogin;
    public SimpleDraweeView  myChatHead;
    private static final String TAG = "MyFragment";
    private String icon;
    private ImageView myIntent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jd_my, null);
        myLogin = view.findViewById(R.id.my_login);
        myChatHead = view.findViewById(R.id.my_ChatHead);
        myIntent = view.findViewById(R.id.my_intent);
        myLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myLogin.getText().toString().equals("登录")){
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent,1);
                }else {
                    Toast.makeText(getActivity(),"【您已登陆！！！】",Toast.LENGTH_SHORT).show();
                    myChatHead.setOnClickListener(new View.OnClickListener() {
                        public String name;
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), MyCenter.class);
                            startActivityForResult(intent,1);
                        }
                    });
                }
            }
        });

        SharedPreferences preferences = getActivity().getSharedPreferences("mobile", Context.MODE_PRIVATE);
        boolean flag = preferences.getBoolean("flag", false);
        if (flag){
            SharedPreferences.Editor edit = preferences.edit();
            String mobile = preferences.getString("mobile", "");
            icon = preferences.getString("icon", "");
            String nickname = preferences.getString("nickname", "");
            if (nickname.equals("")){
                myLogin.setText(mobile);
            }else {
                myLogin.setText(nickname);
            }
            myChatHead.setImageURI(Uri.parse(icon));
        }

        myIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntentActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==1){
            myLogin.setText("登录");
            myChatHead.setImageURI("");
        }
    }


}
