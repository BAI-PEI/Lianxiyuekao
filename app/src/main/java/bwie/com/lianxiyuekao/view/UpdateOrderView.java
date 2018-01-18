package bwie.com.lianxiyuekao.view;


import bwie.com.lianxiyuekao.bean.UpdateOrderBean;

/**
 * date:2018/1/17 16:12
 * introduction:
 */

public interface UpdateOrderView {
    void success(UpdateOrderBean bean);
    void failure(int code);
}
