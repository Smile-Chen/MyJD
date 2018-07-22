package co.example.hp.jdchen.mvp.hompage.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeBean;

/**
 * Created by hp on 2018/7/11.
 */

public class RecyclerRcommendeAdapter extends RecyclerView.Adapter<RecyclerRcommendeAdapter.RecommendViewHolder>{
     public List<HomeBean.TuijianBean.ListBean>recommendlist;
     public Context context;
    private View itemView;
    private static final String TAG = "RecyclerTypeAdapter";

    public RecyclerRcommendeAdapter(List<HomeBean.TuijianBean.ListBean> recommendlist, Context context) {
        this.recommendlist = recommendlist;
        this.context = context;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_recommend, null);
        return new RecommendViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecommendViewHolder holder, final int position) {
        String[] split = recommendlist.get(position).getImages().split("\\|");
        Log.d(TAG, "onBindViewHolder: 推荐=="+split[0]);
        holder.homeRecyclerRecommentImage.setImageURI(Uri.parse(split[0]));
        holder.homeRecyclerRecommentName.setText(recommendlist.get(position).getTitle());
         holder.homeRecyclerRecommentImage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Toast.makeText(context,""+recommendlist.get(position).getSubhead().toString(),Toast.LENGTH_SHORT).show();
                 if (onRecommendClick!=null){
                     onRecommendClick.onContentSuccess(position);
                 }
             }
         });
    }

    @Override
    public int getItemCount() {
        return recommendlist.size();
    }

    public static class RecommendViewHolder extends RecyclerView.ViewHolder{

        private final TextView homeRecyclerRecommentName;
        private final SimpleDraweeView homeRecyclerRecommentImage;

        public RecommendViewHolder(View itemView) {
            super(itemView);
            homeRecyclerRecommentImage = itemView.findViewById(R.id.home_recycler_recommend_image);
            homeRecyclerRecommentName = itemView.findViewById(R.id.home_recycler_recommend_name);
        }



    }
    OnRecommendClick onRecommendClick;

    public void setOnRecommendClick(OnRecommendClick onRecommendClick) {
        this.onRecommendClick = onRecommendClick;
    }

    public interface  OnRecommendClick{
        void onContentSuccess(int position);
    }

}
