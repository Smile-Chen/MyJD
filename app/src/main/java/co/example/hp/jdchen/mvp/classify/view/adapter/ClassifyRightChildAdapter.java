package co.example.hp.jdchen.mvp.classify.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.classify.model.bean.ClassifyBean;

/**
 * Created by hp on 2018/7/12.
 */

public class ClassifyRightChildAdapter extends RecyclerView.Adapter<ClassifyRightChildAdapter.RightChildViewHolder>{
            public List<ClassifyBean.DataBean.ListBean>rightchildlist;
            public Context context;
    private View itemView;

    public ClassifyRightChildAdapter(List<ClassifyBean.DataBean.ListBean> rightchildlist, Context context) {
        this.rightchildlist = rightchildlist;
        this.context = context;
    }

    @Override
    public RightChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_recycler_child, null);
        return new RightChildViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RightChildViewHolder holder, final int position) {
        String icon = rightchildlist.get(position).getIcon();
        holder.classifyRecyclerChildImage.setImageURI(Uri.parse(icon));
        holder.classifyRecyclerChildName.setText(rightchildlist.get(position).getName());
        holder.classifyRecyclerChildImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChildClick!=null){
                    onChildClick.onchildSuccess(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return rightchildlist.size();
    }

    public class RightChildViewHolder extends RecyclerView.ViewHolder {

        private final TextView classifyRecyclerChildName;
        private final ImageView classifyRecyclerChildImage;

        public RightChildViewHolder(View itemView) {
            super(itemView);
            classifyRecyclerChildImage = itemView.findViewById(R.id.classify_recycler_child_image);
            classifyRecyclerChildName = itemView.findViewById(R.id.classify_recycler_child_name);
        }
    }
    OnChildClick onChildClick;

    public void setOnChildClick(OnChildClick onChildClick) {
        this.onChildClick = onChildClick;
    }

    public interface OnChildClick {
        void onchildSuccess(int position);
    }

}
