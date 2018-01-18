package bwie.com.lianxiyuekao.retrofit;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Date:2017/12/16
 * time:14:09
 * author:HuangZhangpeng
 */
public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.method().equals("GET")) {
            String url = request.url().url().toString();
            url += "&source=android";
            Request newRequset = request.newBuilder().url(url).build();
            return chain.proceed(newRequset);
        } else {
            FormBody formBody = (FormBody) request.body();
            FormBody.Builder builder = new FormBody.Builder();
            for (int i = 0; i < formBody.size(); i++) {
                builder.add(formBody.name(i), formBody.value(i));
            }
            builder.add("source", "android");
            FormBody newFormBody = builder.build();
            Request newRequest = request.newBuilder().url(request.url().url().toString()).post(newFormBody).build();
            return chain.proceed(newRequest);
        }

    }
}
