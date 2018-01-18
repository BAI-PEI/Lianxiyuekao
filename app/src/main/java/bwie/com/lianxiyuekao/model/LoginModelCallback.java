package bwie.com.lianxiyuekao.model;

import bwie.com.lianxiyuekao.bean.LoginBean;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public interface LoginModelCallback {
    void success(LoginBean bean);
    void failure(int code);
}
