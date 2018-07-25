package co.example.hp.jdchen.mvp.hompage.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.Activit;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeSearchBean;
import co.example.hp.jdchen.mvp.hompage.presenter.SearchPresenter;
import co.example.hp.jdchen.mvp.hompage.view.iview.SearchIView;

/**
 * Created by hp on 2018/7/16.
 */

public class HomeSearchActivity extends Activit<SearchPresenter> implements SearchIView {


    @BindView(R.id.title_search_return)
    Button titleSearchReturn;
    @BindView(R.id.title_search_name)
    EditText titleSearchName;
    @BindView(R.id.title_search_start)
    Button titleSearchStart;
    @BindView(R.id.title_search_flow)
    FlowLayout titleSearchFlow;
    @BindView(R.id.title_search_cler)
    Button titleSearchCler;
    private String keywords;
    private int sort;
    private static final String TAG = "HomeSearchActivit";

    @Override
    protected SearchPresenter getPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected int getfragmentId() {
        return R.layout.title_search;
    }

    @Override
    protected void initData() {

        titleSearchStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleSearchName.getText().toString().equals("")) {
                    Toast.makeText(HomeSearchActivity.this, "亲，赶紧输上你喜欢的内容吧", Toast.LENGTH_SHORT).show();
                } else {

                    keywords = titleSearchName.getText().toString();
                    prenter.searchs(keywords, sort);
                    Log.d(TAG, "onClick: " + keywords);
                }
            }
        });

    }

    @Override
    protected void initLisenter() {
        titleSearchReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleSearchCler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleSearchFlow.removeAllViews();
            }
        });


    }

    @Override
    protected void initView() {

    }


    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onSearchSuccess(HomeSearchBean homeSearchBean) {
        Button button = new Button(HomeSearchActivity.this);
         button.setPadding(10,10,10,10);
        button.setText(keywords);
        button.setBackgroundColor(Color.TRANSPARENT);
        titleSearchFlow.addView(button);
        Intent intent = new Intent(HomeSearchActivity.this, SearchContentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("keywords", keywords);
        bundle.putInt("sort", sort);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onSearchError(String error) {

    }

}
