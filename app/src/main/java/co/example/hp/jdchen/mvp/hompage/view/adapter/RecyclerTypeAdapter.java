package co.example.hp.jdchen.mvp.hompage.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;

/**
 * Created by hp on 2018/7/11.
 */

public class  RecyclerTypeAdapter extends RecyclerView.Adapter<RecyclerTypeAdapter.TypeViewHolder>{
     public List<HomeTypeBean.DataBean>typelist;
     public Context context;
    private View itemView;
    private static final String TAG = "RecyclerTypeAdapter";

    public RecyclerTypeAdapter(List<HomeTypeBean.DataBean> typelist, Context context) {
        this.typelist = typelist;
        this.context = context;
    }

    @Override
    public TypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_type, null);
        return new TypeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TypeViewHolder holder, int position) {
       String icon = typelist.get(position).getIcon();
        holder.homeRecyclerTypeImage.setImageURI(Uri.parse(icon));
        holder.homeRecyclerTypeName.setText(typelist.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return typelist.size();
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder{

        private final TextView homeRecyclerTypeName;
        private final SimpleDraweeView homeRecyclerTypeImage;

        public TypeViewHolder(View itemView) {
            super(itemView);
            homeRecyclerTypeImage = itemView.findViewById(R.id.home_recycler_type_image);
            homeRecyclerTypeName = itemView.findViewById(R.id.home_recycler_type_name);
        }
    }
}
