package co.example.hp.jdchen.mvp.my.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.example.hp.jdchen.R;
import co.example.hp.jdchen.base.Activit;
import co.example.hp.jdchen.mvp.my.model.bean.HearFileBean;
import co.example.hp.jdchen.mvp.my.model.bean.MBean;
import co.example.hp.jdchen.mvp.my.presenter.MyCenterPresenter;
import co.example.hp.jdchen.mvp.my.view.iview.MyCenterIView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by hp on 2018/7/18.
 */

public class MyCenter extends Activit<MyCenterPresenter> implements MyCenterIView {

    @BindView(R.id.my_center_name)
    TextView myCenterName;

    private static final String TAG = "MyCenter";
    @BindView(R.id.my_center_head)
    SimpleDraweeView myCenterHead;
    @BindView(R.id.my_center_phone)
    TextView myCenterPhone;
    @BindView(R.id.my_center_address)
    TextView myCenterAddress;
    @BindView(R.id.my_center_loginout)
    Button myCenterLoginout;
    //定义图片的路径
    public String path = Environment.getExternalStorageDirectory() + "/photo.png";
    @BindView(R.id.my_center_return)
    Button myCenterReturn;
    @BindView(R.id.my_center_qz)
    ImageView myCenterQz;
    @BindView(R.id.my_center_startqz)
    TextView myCenterStartqz;
    private int uid;
    private String icon;


    @Override
    protected int getfragmentId() {
        return R.layout.my_center;
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initLisenter() {
        //返回
        myCenterReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(1, intent);
                finish();
            }
        });

        //注销
        myCenterLoginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                SharedPreferences preferences = MyCenter.this.getSharedPreferences("mobile", Context.MODE_PRIVATE);
                boolean flag = preferences.getBoolean("flag", false);
                SharedPreferences.Editor edit = preferences.edit();
                edit.clear();
                edit.commit();
                setResult(100, intent);
                finish();
            }
        });

        //打开相机
        myCenterHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyCenter.this);
                builder.setPositiveButton("打开相机", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //将图片放到SD card
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                        startActivityForResult(intent, 100);
                    }
                });
                builder.setNegativeButton("打开相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, 100);
                    }
                });
                builder.show();
            }
        });

        //收货地址
        myCenterAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCenter.this, MyAddressActivity.class);
                startActivity(intent);
            }
        });

        //生成二维码
        myCenterStartqz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = myCenterName.getText().toString();

                Bitmap qrCode = EncodingUtils.createQRCode(s, 200, 200, BitmapFactory.decodeResource(getResources(), R.drawable.jd_buy_icon));
                myCenterQz.setImageBitmap(qrCode);
            }
        });


    }

    @Override
    protected void initView() {
        SharedPreferences preferences = MyCenter.this.getSharedPreferences("mobile", Context.MODE_PRIVATE);
        boolean flag = preferences.getBoolean("flag", false);
        if (flag) {
            SharedPreferences.Editor edit = preferences.edit();
            String mobile = preferences.getString("mobile", "");
            icon = preferences.getString("icon", "");
            String nickname = preferences.getString("nickname", "");
            uid = preferences.getInt("uid", 0);
            myCenterName.setText(nickname);
            myCenterPhone.setText(mobile);
            myCenterHead.setImageURI(Uri.parse(icon));
        }
    }

    @Override
    protected MyCenterPresenter getPresenter() {
        return new MyCenterPresenter(this);
    }

    @Override
    public void onHeardSuccess(HearFileBean hearFileBean) {
        if (hearFileBean.getCode().equals("0")) {
            Toast.makeText(MyCenter.this, "上传成功", Toast.LENGTH_SHORT).show();
            myCenterHead.setImageURI(Uri.parse(icon));
        }
    }

    @Override
    public void onHeardError(String error) {

    }

    @Override
    public void onMSuccess(MBean mBean) {
        prenter.mys(uid);


    }

    @Override
    public void onMError(String error) {

    }

    @Override
    public Context context() {
        return this;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 相机调用裁剪功能
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到拍完的照片进行裁剪
            it.setDataAndType(Uri.fromFile(new File(path)), "image/*");
            //设置是否支持裁剪
            it.putExtra("crop", true);
            //设置框的宽高比
            it.putExtra("aspactX", 1);
            it.putExtra("aspactY", 1);
            //设置输出的照片
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //将图片返回
            it.putExtra("return-data", true);

            startActivityForResult(it, 300);

        }

        //设置裁剪
        if (requestCode == 200 && resultCode == RESULT_OK) {
            //得到图片路径
            Uri uri = data.getData();
            //调用系统裁剪功能
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到照片进行裁剪
            it.setDataAndType(uri, "image/*");
            //设置是否支持裁剪
            it.putExtra("crop", true);
            //设置框的宽高比
            it.putExtra("aspactX", 1);
            it.putExtra("aspactY", 1);
            //设置输出图片大小
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //将图片返回
            it.putExtra("return-data", true);

            startActivityForResult(it, 300);
        }
        //裁剪完后回到设置图片
        if (requestCode == 300 && resultCode == RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            //获取文件路径
            File file1 = new File(getFilesDir().getAbsolutePath());
            if (!file1.exists()) {
                //如果路径不存在就创建
                file1.mkdirs();
            }
            //创建文件
            File file2 = new File(file1, "photo.png");
            FileOutputStream fileOutputStream;
            try {
                //文件输出流
                fileOutputStream = new FileOutputStream(file2);
                //将bitmap写入文件流
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                //刷新此输出流并强制将所有缓冲的输出字节被写出
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //RequestBody封装了文件和文件的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file2);
            // MultipartBody.Part封装了接受的key和文件名字和RequestBody
            MultipartBody.Part file = MultipartBody.Part.createFormData("file", file2.getName(), requestBody);

            //调用上传头像
            prenter.hearfilebeans(uid, file);
        }
    }
}
