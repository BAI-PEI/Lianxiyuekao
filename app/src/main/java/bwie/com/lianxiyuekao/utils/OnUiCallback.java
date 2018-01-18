package bwie.com.lianxiyuekao.utils;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public abstract class OnUiCallback implements Callback {
    private Handler handler = OkHttpUtils.getInstance().getHandler();
    public abstract void onFailed(Call call, IOException e);
    public abstract void onSuccess(String result) throws IOException;

    @Override
    public void onFailure(final Call call, final IOException e) {
        //该方法就是把  线程post到handler所在的线程
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFailed(call, e);
            }
        });

    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        final String result = response.body().string();
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    onSuccess(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
