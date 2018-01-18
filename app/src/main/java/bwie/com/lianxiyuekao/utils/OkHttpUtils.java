package bwie.com.lianxiyuekao.utils;

import android.os.Handler;
import android.os.Looper;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public class OkHttpUtils {

    private Handler handler = new Handler(Looper.getMainLooper());
    public Handler getHandler(){
        return handler;
    }
    //单例
    private static OkHttpUtils okHttpUtils = new OkHttpUtils();
    private OkHttpUtils(){};
    public static OkHttpUtils getInstance(){
        return okHttpUtils;
    }

    private OkHttpClient client;
    private void initOkHttpClient(){
        if(client == null){
            //添加拦截器就在Builder()后加上.addInterceptor(new MyInterceptor())
            client = new OkHttpClient.Builder().build();
        }
    }

    //公用的get请求方法  完成的功能不确定
    public void doGet(String url, Callback callback){
        initOkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

}
