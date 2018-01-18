package bwie.com.lianxiyuekao.model;

import java.util.HashMap;
import java.util.Map;

import bwie.com.lianxiyuekao.bean.LoginBean;
import bwie.com.lianxiyuekao.bean.OrderBean;
import bwie.com.lianxiyuekao.retrofit.BaseObserver;
import bwie.com.lianxiyuekao.retrofit.RetrofitManager;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public class OrderModel {
    //http://120.27.23.105/product/getOrders?uid=71
    public void getOrderData(String status,final OrderModelCallback callback){
        Map<String,String> map=new HashMap<>();
        map.put("uid","100");
        map.put("status",status);
        RetrofitManager.get("product/getOrders", map, new BaseObserver<OrderBean>() {
            @Override
            public void success(OrderBean bean) {
                callback.success(bean);
            }

            @Override
            public void failure(int code) {
                callback.failure(code);
            }

            @Override
            public void onNext(LoginBean bean) {

            }

        });
    }
}
