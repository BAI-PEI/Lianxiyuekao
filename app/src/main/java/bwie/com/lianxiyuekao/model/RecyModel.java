package bwie.com.lianxiyuekao.model;

import bwie.com.lianxiyuekao.utils.OkHttpUtils;
import okhttp3.Callback;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public class RecyModel implements IRecyModel {
    @Override
    public void recy(Callback callback) {
        OkHttpUtils.getInstance().doGet("http://120.27.23.105/product/getProducts?pscid=39&page=1", callback);
    }
}
