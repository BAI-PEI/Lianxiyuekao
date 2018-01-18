package bwie.com.lianxiyuekao.model;

import bwie.com.lianxiyuekao.bean.CreatOrderBean;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public interface CreateOrderCallback {
    void success(CreatOrderBean bean);
    void failure(int code);

}
