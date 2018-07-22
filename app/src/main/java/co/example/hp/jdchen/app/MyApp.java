package co.example.hp.jdchen.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by hp on 2018/7/11.
 */

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
