package co.example.hp.jdchen.mvp.shoppingcar.view.adapter;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import co.example.hp.jdchen.R;
import co.example.hp.jdchen.mvp.shoppingcar.model.bean.ShopBean;

/**
 * Created by hp on 2018/7/13.
 */

public class ShopAdapter extends BaseExpandableListAdapter {
    public List<ShopBean.DataBean> shoplist;


    public ShopAdapter(List<ShopBean.DataBean> shoplist) {
        this.shoplist = shoplist;
    }

    @Override
    public int getGroupCount() {
        return shoplist==null?0:shoplist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return shoplist.get(groupPosition).getList()==null?0:shoplist.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        PrentViewHolder prentViewHolder = null;

                     convertView = View.inflate(parent.getContext(), R.layout.shop_prent, null);
                     prentViewHolder = new PrentViewHolder(convertView);
                     convertView.setTag(prentViewHolder);
                      prentViewHolder.shopPrentShopname.setText(shoplist.get(groupPosition).getSellerName());


        //根据商品确定商家是否被选中
        boolean currentSellerAllselectes = isCurrentSellerAllselectes(groupPosition);
        prentViewHolder.shopPrentCheck.setChecked(currentSellerAllselectes);
          prentViewHolder.shopPrentCheck.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  //点击商家checkbox
                  if (onCarcheckChangListener!=null){
                      onCarcheckChangListener.onSellerCheckedChang(groupPosition);
                  }
              }
          });
        return convertView;
    }

    //判断当前商家所有商品是否被选中
    public boolean isCurrentSellerAllselectes(int groupPosition) {
        List<ShopBean.DataBean.ListBean> listBeans = shoplist.get(groupPosition).getList();
        for (ShopBean.DataBean.ListBean listBean:listBeans){
            if (listBean.getSelected()==0){
                return false;
            }
        }
        return true;
    }
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        final ChildViewHolder childViewHolder;

            convertView = View.inflate(parent.getContext(), R.layout.shop_child, null);
           childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);

        String[] split = shoplist.get(groupPosition).getList().get(childPosition).getImages().split("\\|");
        childViewHolder.shopChildImage.setImageURI(Uri.parse(split[0]));
        childViewHolder.shopChildName.setText(shoplist.get(groupPosition).getList().get(childPosition).getTitle());
        childViewHolder.shopChildPrice.setText("￥ "+shoplist.get(groupPosition).getList().get(childPosition).getPrice());

             childViewHolder.shopChildName.setOnLongClickListener(new View.OnLongClickListener() {
                 @Override
                 public boolean onLongClick(View v) {
                     if (onCarcheckChangListener!=null){
                         onCarcheckChangListener.onDelectCartsChange(groupPosition,childPosition);
                     }

                     return false;
                 }
             });


            //商品的选中状态
         childViewHolder.shopChildCheck.setChecked(shoplist.get(groupPosition).getList().get(childPosition).getSelected()==1);
         childViewHolder.shopChildCheck.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //点击商品
                 if (onCarcheckChangListener!=null){
                     onCarcheckChangListener.onProductCheckedChang(groupPosition,childPosition);
                 }
             }
         });

         childViewHolder.shopChildhShopaddremove.setNumber(shoplist.get(groupPosition).getList().get(childPosition).getNum());
         childViewHolder.shopChildhShopaddremove.setonNumberCHangListenter(new ShopAddRemove.OnNumberCHangListenter() {
             @Override
             public void onNumberChang(int num) {
                             if (onCarcheckChangListener!=null){
                                 onCarcheckChangListener.onProductNumberChange(groupPosition,childPosition,num);
                 }

             }
         });


        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    //判断所有商品是否被选中
     public boolean isAllCheckedSelected(){
         for (int i = 0; i < shoplist.size(); i++) {
             List<ShopBean.DataBean.ListBean> list = shoplist.get(i).getList();
             for (int j = 0; j < list.size(); j++) {
                 if (list.get(j).getSelected()==0){
                     return false;
                 }
             }
         }
        return true;
     }
     //计算总价
    public float caculateTotaPrice(){
         float totalprice=0;
        for (int i = 0; i < shoplist.size(); i++) {
            List<ShopBean.DataBean.ListBean> list = shoplist.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                //只要是选中状态
                if (list.get(j).getSelected()==1){
                    double price = list.get(j).getPrice();
                    int num = list.get(j).getNum();
                    totalprice+=price*num;
                }
            }
        }
         return totalprice;
    }
    //计算总数量
    public float caculateTotaNumber(){
        float totalnumber=0;
        for (int i = 0; i < shoplist.size(); i++) {
            List<ShopBean.DataBean.ListBean> list = shoplist.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                //只要是选中状态
                if (list.get(j).getSelected()==1){
                    int num = list.get(j).getNum();
                    totalnumber+=num;
                }
            }
        }
        return totalnumber;
    }
    //当商家checkbox被选中时调用
     public void changeSellShopName(int groupPosition, boolean isSelected){
         List<ShopBean.DataBean.ListBean> beans = shoplist.get(groupPosition).getList();
         for (int i = 0; i < beans.size(); i++) {
             ShopBean.DataBean.ListBean listBean = beans.get(i);
             listBean.setSelected(isSelected?1:0);
         }
     }
//当商品checkbox被选中时调用
public void changeSellShopinng(int groupPosition, int childPosition){
    List<ShopBean.DataBean.ListBean> list = shoplist.get(groupPosition).getList();
    ShopBean.DataBean.ListBean listBean = list.get(childPosition);
    listBean.setSelected(listBean.getSelected()==0?1:0);
}
    //当加减器被点击得时候调用
    public void changeSellNumber(int groupPosition, int childPosition,int number) {
        List<ShopBean.DataBean.ListBean> list = shoplist.get(groupPosition).getList();
        ShopBean.DataBean.ListBean listBean = list.get(childPosition);
        listBean.setNum(number);
}
//设置所有商品状态
    public void changedShopAllStatus(boolean selected){
        for (int i = 0; i < shoplist.size(); i++) {
            List<ShopBean.DataBean.ListBean> list = shoplist.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                list.get(j).setSelected(selected?1:0);
            }
        }
    }

    //创建ViewHolder
    public static class PrentViewHolder {
        public View rootView;
        public CheckBox shopPrentCheck;
        public TextView shopPrentShopname;

        public PrentViewHolder(View rootView) {
            this.rootView = rootView;
            this.shopPrentCheck = (CheckBox) rootView.findViewById(R.id.shop_prent_check);
            this.shopPrentShopname = (TextView) rootView.findViewById(R.id.shop_prent_shopname);
        }

    }

    public static class ChildViewHolder {
        public View rootView;
        public CheckBox shopChildCheck;
        public SimpleDraweeView shopChildImage;
        public TextView shopChildName;
        public TextView shopChildPrice;
        public ShopAddRemove shopChildhShopaddremove;

        public ChildViewHolder(View rootView) {
            this.rootView = rootView;
            this.shopChildCheck = (CheckBox) rootView.findViewById(R.id.shop_child_check);
            this.shopChildImage = (SimpleDraweeView) rootView.findViewById(R.id.shop_child_image);
            this.shopChildName = (TextView) rootView.findViewById(R.id.shop_child_name);
            this.shopChildPrice = (TextView) rootView.findViewById(R.id.shop_child_price);
            this.shopChildhShopaddremove = (ShopAddRemove) rootView.findViewById(R.id.shop_child_shopaddremove);

        }

    }

    //事件监听
    OnCarcheckChangListener onCarcheckChangListener;

    public void setShopAdapter(ShopAdapter.OnCarcheckChangListener onCarcheckChangListener) {
        this.onCarcheckChangListener = onCarcheckChangListener;
    }
    public interface OnCarcheckChangListener {
              void onSellerCheckedChang(int groupPosition);
              void onProductCheckedChang(int groupPosition, int childPosition);
              void onProductNumberChange(int groupPosition, int childPosition,int numbe);
              void onDelectCartsChange(int groupPosition, int childPosition);
    }

}
