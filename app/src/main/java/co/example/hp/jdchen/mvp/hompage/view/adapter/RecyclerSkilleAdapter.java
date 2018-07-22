package co.example.hp.jdchen.mvp.hompage.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeBean;

/**
 * Created by hp on 2018/7/11.
 */

public class RecyclerSkilleAdapter extends RecyclerView.Adapter<RecyclerSkilleAdapter.SkillViewHolder>{
     public List<HomeBean.MiaoshaBean.ListBeanX>skilllist;
     public Context context;
    private View itemView;
    private static final String TAG = "RecyclerTypeAdapter";

    public RecyclerSkilleAdapter(List<HomeBean.MiaoshaBean.ListBeanX> skilllist, Context context) {
        this.skilllist = skilllist;
        this.context = context;
    }

    @Override
    public SkillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_seckill, null);
        return new SkillViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(SkillViewHolder holder, final int position) {
        String[] split = skilllist.get(position).getImages().split("\\|");
        Log.d(TAG, "onBindViewHolder: 秒杀==="+split[0]);
        holder.homeRecyclerSkillImage.setImageURI(Uri.parse(split[0]));
        holder.homeRecyclerSkillIPrice.setText("￥ "+skilllist.get(position).getPrice());

        holder.homeRecyclerSkillImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSkillCilke!=null){
                    onSkillCilke.onSkillSuccess(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return skilllist.size();
    }

    public class SkillViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView homeRecyclerSkillImage;
        private final TextView homeRecyclerSkillIPrice;
        public SkillViewHolder(View itemView) {
            super(itemView);
            homeRecyclerSkillImage = itemView.findViewById(R.id.home_recycler_seckill_image);
            homeRecyclerSkillIPrice = itemView.findViewById(R.id.home_recycler_seckill_price);
        }
    }
    OnSkillCilke onSkillCilke;

    public void setOnSkillCilke(OnSkillCilke onSkillCilke) {
        this.onSkillCilke = onSkillCilke;
    }

    public interface OnSkillCilke {
        void onSkillSuccess(int position);
    }
}
