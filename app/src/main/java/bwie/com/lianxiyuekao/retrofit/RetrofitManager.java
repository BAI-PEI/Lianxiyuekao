package bwie.com.lianxiyuekao.retrofit;

import java.util.Map;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * date:2018/1/15 14:36
 * introduction:
 */

public class RetrofitManager {

    public static OkHttpClient client=new OkHttpClient.Builder()
            .addInterceptor(new MyInterceptor())
            .build();


    public static ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://www.zhaoapi.cn/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService.class);

    public static void get(String url, Map<String,String> map, Observer observer){
        apiService.get(url,map)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(observer);

    }

    public static void post(String url,Map<String,String> map,Observer observer){
        apiService.post(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
