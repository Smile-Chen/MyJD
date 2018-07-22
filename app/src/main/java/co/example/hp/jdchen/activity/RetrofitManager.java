package co.example.hp.jdchen.activity;

import java.util.concurrent.TimeUnit;

import co.example.hp.jdchen.api.ConsantAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hp on 2018/7/11.
 */

public class RetrofitManager {
    public static final String BASE_URL= ConsantAPI.BASE_URL;
    private Retrofit mretrofit;

    private static class SingleHolder {
        private static final RetrofitManager _INSTANT = new RetrofitManager(BASE_URL);
    }
    //懒加载
    public static RetrofitManager getRetrofit() {
        return SingleHolder._INSTANT;
    }
    private RetrofitManager(String baseUrl) {
        mretrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkhttpClinet())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    private OkHttpClient getOkhttpClinet() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(5000,TimeUnit.SECONDS)
                .writeTimeout(5000,TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public <T> T create(Class<T> Clazz) {
        return mretrofit.create(Clazz);
    }


}
