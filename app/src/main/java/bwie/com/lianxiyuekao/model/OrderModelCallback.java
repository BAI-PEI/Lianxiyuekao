package bwie.com.lianxiyuekao.model;


import bwie.com.lianxiyuekao.bean.OrderBean;

/**
 * date:2018/1/16 19:22
 * introduction:
 */

public interface OrderModelCallback {
    void success(OrderBean bean);
    void failure(int code);
}
