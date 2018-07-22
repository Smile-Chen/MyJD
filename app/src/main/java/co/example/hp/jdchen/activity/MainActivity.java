package co.example.hp.jdchen.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.example.hp.jdchen.R;

public class MainActivity extends AppCompatActivity {
    int i=3;
   public Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
            if (msg.what==0){
               Intent intent= new Intent(MainActivity.this,MyJD.class);
               startActivity(intent);
            }
       }
   };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         new Thread(){
             @Override
             public void run() {
                 super.run();
                 while (i>0){
                     try {
                         i--;
                         sleep(1000);
                         handler.sendEmptyMessage(i);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }
         }.start();


    }


}
