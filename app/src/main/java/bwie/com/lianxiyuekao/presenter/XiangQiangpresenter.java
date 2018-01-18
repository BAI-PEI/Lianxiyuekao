package bwie.com.lianxiyuekao.presenter;

import bwie.com.lianxiyuekao.model.Xiangmodel;
import bwie.com.lianxiyuekao.view.XiangCallBack;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public class XiangQiangpresenter {
    XiangCallBack callBack;
    Xiangmodel xiangmodel;
    public XiangQiangpresenter(XiangCallBack callBack) {
        this.callBack=callBack;
        this.xiangmodel=new Xiangmodel();
    }

    public void getData(String pid) {
        xiangmodel.getData(pid, new XiangQCallBack() {
            @Override
            public void success(String s) {
                callBack.success(s);
            }

            @Override
            public void error(Exception e) {
                callBack.error(e);
            }
        });
    }
    XiangQCallBack xiangQCallBack;
    public void setXiangQCallBack(XiangQCallBack xiangQCallBack){
        this.xiangQCallBack=xiangQCallBack;
    }

    public interface  XiangQCallBack{
        void  success(String s);
        void  error(Exception e);
    }

}
