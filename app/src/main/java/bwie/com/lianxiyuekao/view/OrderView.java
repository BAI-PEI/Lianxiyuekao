package bwie.com.lianxiyuekao.view;


import bwie.com.lianxiyuekao.bean.OrderBean;

/**
 * date:2018/1/16 19:28
 * introduction:
 */

public interface OrderView {
    void success(OrderBean bean);
    void failure(int code);
}
