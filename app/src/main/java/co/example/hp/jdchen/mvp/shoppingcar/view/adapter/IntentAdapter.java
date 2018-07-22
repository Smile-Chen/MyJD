package co.example.hp.jdchen.mvp.shoppingcar.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.IntentBean;

/**
 * Created by hp on 2018/7/20.
 */

public class IntentAdapter extends RecyclerView.Adapter<IntentAdapter.IntentViewHolder> {
    public List<IntentBean.DataBean> list;
    public Context context;

    public IntentAdapter(List<IntentBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public IntentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shop_intent_content, null);
        return new IntentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final IntentViewHolder holder, final int position) {

        holder.shopIntentName.setText(list.get(position).getTitle());
         holder.shopIntentPrice.setText("￥ "+list.get(position).getPrice());
        holder.shopIntentTime.setText(list.get(position).getCreatetime());
        int status = list.get(position).getStatus();
                if (status==0){
                    holder.shopIntentPayment.setText("待付款");
                    holder.shopIntentPayment.setBackgroundColor(Color.YELLOW);
                }else if(status==1){
                    holder.shopIntentPayment.setText("已付款");
                    holder.shopIntentPayment.setBackgroundColor(Color.GREEN);
                }else if(status==2){
                    holder.shopIntentPayment.setText("已取消");
                    holder.shopIntentPayment.setBackgroundColor(Color.GRAY);
                }


        holder.shopIntentPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onIntentButtonClick!=null){
                    onIntentButtonClick.onIntentButton(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class IntentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_intent_name)
        TextView shopIntentName;
        @BindView(R.id.shop_intent_price)
        TextView shopIntentPrice;
        @BindView(R.id.shop_intent_time)
        TextView shopIntentTime;
        @BindView(R.id.shop_intent_payment)
        Button shopIntentPayment;

        IntentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
     OnIntentButtonClick onIntentButtonClick;

    public void setOnIntentButtonClick(OnIntentButtonClick onIntentButtonClick) {
        this.onIntentButtonClick = onIntentButtonClick;
    }

    public interface OnIntentButtonClick {
        void onIntentButton(int position);
    }
}
