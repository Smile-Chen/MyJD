package co.example.hp.jdchen.mvp.classify.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.classify.model.bean.ChildContentBean;

/**
 * Created by hp on 2018/7/17.
 */

public class ChildContentAdapter extends RecyclerView.Adapter<ChildContentAdapter.ContentViewHolder> {
           public List<ChildContentBean.DataBean>contentlist;
           public Context context;
            private View view;

    public ChildContentAdapter(List<ChildContentBean.DataBean> contentlist, Context context) {
        this.contentlist = contentlist;
        this.context = context;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_child_content, null);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, final int position) {
       String[] split = contentlist.get(position).getImages().split("\\|");
       holder.classifyChildContentImage.setImageURI(Uri.parse(split[0]));
        holder.classifyChildContentName.setText(contentlist.get(position).getTitle());
        holder.classifyChildContentPrice.setText("￥ "+contentlist.get(position).getPrice());
         holder.classifyChildContentImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (onChildContentClick!=null){
                     onChildContentClick.onchildshopSuccess(position);
                 }
             }
         });

    }

    @Override
    public int getItemCount() {
        return contentlist.size();
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.classify_child_content_image)
        SimpleDraweeView classifyChildContentImage;
        @BindView(R.id.classify_child_content_name)
        TextView classifyChildContentName;
        @BindView(R.id.classify_child_content_price)
        TextView classifyChildContentPrice;

        ContentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    OnChildContentClick onChildContentClick;

    public void setOnChildContentClick(OnChildContentClick onChildContentClick) {
        this.onChildContentClick = onChildContentClick;
    }

    public interface OnChildContentClick {
        void onchildshopSuccess(int position);
    }

}
