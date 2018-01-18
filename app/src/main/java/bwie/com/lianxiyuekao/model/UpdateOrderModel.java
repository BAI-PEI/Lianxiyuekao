package bwie.com.lianxiyuekao.model;

import java.util.HashMap;
import java.util.Map;

import bwie.com.lianxiyuekao.bean.LoginBean;
import bwie.com.lianxiyuekao.bean.UpdateOrderBean;
import bwie.com.lianxiyuekao.retrofit.BaseObserver;
import bwie.com.lianxiyuekao.retrofit.RetrofitManager;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public class UpdateOrderModel {
    //https://www.zhaoapi.cn/product/updateOrder?uid=100&status=2&orderId=5037
    public void updateOrder(String status, String orderId,final UpdateOrderCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("uid","100");
        map.put("status",status);
        map.put("orderId",orderId);
        RetrofitManager.get("product/updateOrder", map, new BaseObserver<UpdateOrderBean>() {
            @Override
            public void success(UpdateOrderBean bean) {
                callback.success(bean);
            }

            @Override
            public void failure(int code) {

            }

            @Override
            public void onNext(LoginBean bean) {

            }

        });
    }


}
