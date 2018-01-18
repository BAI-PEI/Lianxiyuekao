package bwie.com.lianxiyuekao.model;

import bwie.com.lianxiyuekao.bean.UpdateOrderBean;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public interface UpdateOrderCallback {
    void success(UpdateOrderBean bean);
    void failure(int code);
}
