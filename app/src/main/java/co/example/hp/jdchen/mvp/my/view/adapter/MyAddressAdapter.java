package co.example.hp.jdchen.mvp.my.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.my.model.bean.MyAddressBean;
import co.example.hp.jdchen.mvp.my.view.activity.NewAddressActivity;

/**
 * Created by hp on 2018/7/20.
 */

public class MyAddressAdapter extends RecyclerView.Adapter<MyAddressAdapter.MyAddressViewHolder> {
    public List<MyAddressBean.DataBean> list;
    public Context context;


    public MyAddressAdapter(List<MyAddressBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyAddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_address_content, null);
        return new MyAddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyAddressViewHolder holder, final int position) {
        int status = list.get(position).getStatus();
        if (status==1){
            holder.myAddressContentRadiobutton.setChecked(true);
        }else {
            holder.myAddressContentRadiobutton.setChecked(false);
        }
        holder.myAddressContentName.setText(list.get(position).getName());
        holder.myAddressContentMobile.setText(list.get(position).getMobile() + "");
        holder.myAddressContentAddr.setText(list.get(position).getAddr());
        holder.myAddressContentUpdateadress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (onUpdateClickLister!=null){
                        onUpdateClickLister.onUpdateClick(position);
                    }

            }
        });
        holder.myAddressContentRadiobutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ondefutClickLister!=null){
                    ondefutClickLister.ondefutClickLister(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyAddressViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.my_address_content_name)
        TextView myAddressContentName;
        @BindView(R.id.my_address_content_mobile)
        TextView myAddressContentMobile;
        @BindView(R.id.my_address_content_addr)
        TextView myAddressContentAddr;
        @BindView(R.id.my_address_content_radiobutton)
        RadioButton myAddressContentRadiobutton;
        @BindView(R.id.my_address_content_updateadress)
        TextView myAddressContentUpdateadress;

        MyAddressViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    OnUpdateClickLister onUpdateClickLister;

    public void setOnUpdateClickLister(OnUpdateClickLister onUpdateClickLister) {
        this.onUpdateClickLister = onUpdateClickLister;
    }

    public interface OnUpdateClickLister {
        void onUpdateClick(int position);
    }
    OnDefutClickLister ondefutClickLister;

    public void setOndefutClickLister(OnDefutClickLister ondefutClickLister) {
        ondefutClickLister = ondefutClickLister;
    }

    public interface OnDefutClickLister {
        void ondefutClickLister(int position);
    }
}
