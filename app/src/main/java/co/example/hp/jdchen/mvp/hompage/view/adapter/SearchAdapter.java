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

import butterknife.BindView;
import butterknife.ButterKnife;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeSearchBean;

/**
 * Created by hp on 2018/7/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    public List<HomeSearchBean.DataBean> listBeans;
    public Context context;
    private View view;

    public SearchAdapter(List<HomeSearchBean.DataBean> listBeans, Context context) {
        this.listBeans = listBeans;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_rsearch_content, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String[] split = listBeans.get(position).getImages().split("\\|");
        holder.searchContentImage.setImageURI(Uri.parse(split[0]));
        holder.searchContentName.setText(listBeans.get(position).getTitle());
        holder.searchContentPrice.setText("￥ "+listBeans.get(position).getPrice());
        holder.searchContentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSearchChilkin!=null){
                    onSearchChilkin.onSearchSuccess(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_content_image)
        SimpleDraweeView searchContentImage;
        @BindView(R.id.search_content_name)
        TextView searchContentName;
        @BindView(R.id.search_content_price)
        TextView searchContentPrice;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    OnSearchChilkin onSearchChilkin;

    public void setOnSearchChilkin(OnSearchChilkin onSearchChilkin) {
        this.onSearchChilkin = onSearchChilkin;
    }

    public interface OnSearchChilkin {
        void onSearchSuccess(int position);
    }
}
