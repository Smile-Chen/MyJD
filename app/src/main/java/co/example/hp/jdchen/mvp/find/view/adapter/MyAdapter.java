package co.example.hp.jdchen.mvp.find.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.find.model.FindBean;

/**
 * Created by hp on 2018/7/19.
 */

public class MyAdapter extends RecyclerView.Adapter{
             public List<FindBean.ResultBean.DataBean>findlist=new ArrayList<>();
             public static final int a=0;
               public static final int  b=1;
             private View itemView;
    private static final String TAG = "MyAdapter";

    public MyAdapter(List<FindBean.ResultBean.DataBean> findlist) {
        this.findlist = findlist;
        this.itemView = itemView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             if (viewType==a){
                 itemView = View.inflate(parent.getContext(), R.layout.find_one, null);
                 return new OneViewHolder(itemView);
             }else {
                 itemView = View.inflate(parent.getContext(), R.layout.find_two, null);
             }
        return new TwoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType==0){
            String pics = findlist.get(position).getThumbnail_pic_s();
            ((OneViewHolder)holder).oneImage.setImageURI(Uri.parse(pics));
              Log.d(TAG, "onBindViewHolder:图片是=== "+pics);
            ((OneViewHolder)holder).oneName.setText(findlist.get(position).getTitle());
        }else {
            String pics = findlist.get(position).getThumbnail_pic_s03();
            ((OneViewHolder)holder).oneImage.setImageURI(Uri.parse(pics));
            Log.d(TAG, "onBindViewHolder:图片是=== "+pics);
            ((OneViewHolder)holder).oneName.setText(findlist.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return findlist.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (findlist.get(position).getUrl()!=null){
            return 0;
        }else {
            return 1;
        }
    }

    //创建viewholder
    class OneViewHolder extends RecyclerView.ViewHolder{
        private final SimpleDraweeView oneImage;
        private final TextView oneName;
        public OneViewHolder(View itemView) {
            super(itemView);
            oneImage = itemView.findViewById(R.id.find_one_image);
            oneName = itemView.findViewById(R.id.find_one_name);
        }
    }
    class TwoViewHolder extends RecyclerView.ViewHolder{
        private final SimpleDraweeView twoImage;
        private final TextView twoName;
        public TwoViewHolder(View itemView) {
            super(itemView);
            twoImage = itemView.findViewById(R.id.find_two_image);
            twoName = itemView.findViewById(R.id.find_two_name);
        }
    }

}
