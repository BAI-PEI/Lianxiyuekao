package bwie.com.lianxiyuekao.presenter;


import bwie.com.lianxiyuekao.bean.UpdateOrderBean;
import bwie.com.lianxiyuekao.model.UpdateOrderCallback;
import bwie.com.lianxiyuekao.model.UpdateOrderModel;
import bwie.com.lianxiyuekao.view.UpdateOrderView;

/**
 * date:2018/1/17 16:13
 * introduction:
 */

public class UpdateOrderPresenter {

    private UpdateOrderView view;
    private UpdateOrderModel model;

    public UpdateOrderPresenter(UpdateOrderView view) {
        this.view = view;
        model=new UpdateOrderModel();
    }

    public void updateOrder(String status,String orderId){
        model.updateOrder(status, orderId, new UpdateOrderCallback() {
            @Override
            public void success(UpdateOrderBean bean) {
                view.success(bean);
            }

            @Override
            public void failure(int code) {
               view.failure(code);
            }
        });
    }


}
