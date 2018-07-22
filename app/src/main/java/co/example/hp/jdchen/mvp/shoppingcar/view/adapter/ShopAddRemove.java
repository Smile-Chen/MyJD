package co.example.hp.jdchen.mvp.shoppingcar.view.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import co.example.hp.jdchen.R;

/**
 * Created by hp on 2018/7/13.
 */

public class ShopAddRemove extends LinearLayout {

    private Button shopChildNumJian;
    private Button shopChildNum;
    private Button shopChildNumJia;
    public int number=1;

    public ShopAddRemove(Context context) {
        this(context,null);
    }

    public ShopAddRemove(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.shop_add_remove, this);
        shopChildNum = view.findViewById(R.id.shop_child_num);
        shopChildNumJian = view.findViewById(R.id.shop_child_numjian);
        shopChildNumJia = view.findViewById(R.id.shop_child_numjia);

        //商品数量减减减
        shopChildNumJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number>1){
                    --number;
                    shopChildNum.setText(number+"");
                    if (onNumberCHangListenter!=null){
                        onNumberCHangListenter.onNumberChang(number);
                    }else{
                        Toast.makeText(getContext(), "不能再少了", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //商品数量加加加
        shopChildNumJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++number;
                shopChildNum.setText(number+"");
                if (onNumberCHangListenter!=null){
                    onNumberCHangListenter.onNumberChang(number);
                }

            }
        });

    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
        shopChildNum.setText(number+"");
    }
    //数字监听
    OnNumberCHangListenter onNumberCHangListenter;

    public void setonNumberCHangListenter(OnNumberCHangListenter onNumberCHangListenter) {
        this.onNumberCHangListenter = onNumberCHangListenter;
    }

    public interface OnNumberCHangListenter {
        void onNumberChang(int num);
    }
}
