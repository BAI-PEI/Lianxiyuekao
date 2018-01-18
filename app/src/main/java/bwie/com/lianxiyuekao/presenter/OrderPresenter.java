package bwie.com.lianxiyuekao.presenter;


import bwie.com.lianxiyuekao.bean.OrderBean;
import bwie.com.lianxiyuekao.model.OrderModel;
import bwie.com.lianxiyuekao.model.OrderModelCallback;
import bwie.com.lianxiyuekao.view.OrderView;

/**
 * date:2018/1/16 19:29
 * introduction:
 */

public class OrderPresenter {
    private OrderView view;
    private OrderModel model;

    public OrderPresenter(OrderView view) {
        this.view = view;
        model=new OrderModel();
    }


    public void getOrder(String status){
        model.getOrderData(status, new OrderModelCallback() {
            @Override
            public void success(OrderBean bean) {
                view.success(bean);
            }

            @Override
            public void failure(int code) {
               view.failure(code);
            }
        });
    }
    /**
     * 解除绑定
     */
    public void dettach(){
        this.view=null;
    }
}
