package co.example.hp.jdchen.mvp.classify.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.hompage.model.bean.HomeTypeBean;

/**
 * Created by hp on 2018/7/11.
 */

public class ClssifyLeftTypeAdapter extends BaseAdapter{
     public List<HomeTypeBean.DataBean>lefttypelist;
     public Context context;
    private static final String TAG = "RecyclerTypeAdapter";

    public ClssifyLeftTypeAdapter(List<HomeTypeBean.DataBean> lefttypelist, Context context) {
        this.lefttypelist = lefttypelist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lefttypelist.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder viewHolder;
        convertView = View.inflate(context, R.layout.classify_listview, null);
        viewHolder = new ViewHolder();
        viewHolder.lefttypename = convertView.findViewById(R.id.classify_list_tv_name);
        viewHolder.lefttypename.setText(lefttypelist.get(position).getName());
        Log.d(TAG, "getView: 分类左边=="+ viewHolder.lefttypename);
        return convertView;
    }
    class ViewHolder{
        TextView lefttypename;
    }

}
