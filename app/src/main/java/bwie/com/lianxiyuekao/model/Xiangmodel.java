package bwie.com.lianxiyuekao.model;

import java.io.IOException;

import bwie.com.lianxiyuekao.presenter.XiangQiangpresenter;
import bwie.com.lianxiyuekao.utils.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public class Xiangmodel {

    public void getData(String pid, final XiangQiangpresenter.XiangQCallBack xiangQCallBack) {
        OkHttpUtils.getInstance().doGet("http://120.27.23.105/product/getProductDetail?pid=" + pid+"&source=android", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                xiangQCallBack.error(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                xiangQCallBack.success(string);
            }
        });

    }
}
