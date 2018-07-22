package co.example.hp.jdchen.mvp.classify.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.classify.model.bean.ClassifyBean;
import co.example.hp.jdchen.mvp.classify.view.activity.ClassifyContentActivity;

/**
 * Created by hp on 2018/7/11.
 */

public class ClassifyRightAdapter extends RecyclerView.Adapter<ClassifyRightAdapter.RightViewHolder>{
     public List<ClassifyBean.DataBean>typelist;
     public Context context;
    private View itemView;
    private static final String TAG = "RecyclerTypeAdapter";

    public ClassifyRightAdapter(List<ClassifyBean.DataBean> typelist, Context context) {
        this.typelist = typelist;
        this.context = context;
    }

    @Override
    public RightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_recycler_title, null);
        return new RightViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RightViewHolder holder, int position) {
        holder.classifyRightRecyclerName.setText(typelist.get(position).getName());

        final List<ClassifyBean.DataBean.ListBean> listBeans = this.typelist.get(position).getList();
        final ClassifyRightChildAdapter classifyRightChildAdapter = new ClassifyRightChildAdapter(listBeans, context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.classifyRightRecyclerChild.setLayoutManager(gridLayoutManager);
        holder.classifyRightRecyclerChild.setAdapter(classifyRightChildAdapter);
        classifyRightChildAdapter.setOnChildClick(new ClassifyRightChildAdapter.OnChildClick() {
            @Override
            public void onchildSuccess(int position) {
                Intent intent = new Intent(context, ClassifyContentActivity.class);
                Bundle bundle = new Bundle();
                int pscid = listBeans.get(position).getPscid();
                 bundle.putInt("pscid",pscid);
                   intent.putExtras(bundle);
                Log.d(TAG, "onchildSuccess: 详情id==="+pscid);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return typelist.size();
    }

    public class RightViewHolder extends RecyclerView.ViewHolder{

        private final TextView classifyRightRecyclerName;
        private final RecyclerView classifyRightRecyclerChild;

        public RightViewHolder(View itemView) {
            super(itemView);
            classifyRightRecyclerName = itemView.findViewById(R.id.classify_right_recycler_name);
            classifyRightRecyclerChild = itemView.findViewById(R.id.classify_right_recycler_child);
        }
    }
}
