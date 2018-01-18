package bwie.com.lianxiyuekao.presenter;

import bwie.com.lianxiyuekao.model.LoginModel;

/**
 * Created by BAIPEI on 2018/1/16.
 */

public class LoginPresenter {

    MainPresenter mainPresenter;
    private LoginModel loginModel;

    public LoginPresenter(MainPresenter mainPresenter){
        this.mainPresenter = mainPresenter;
        loginModel = new LoginModel();
    }

    public void loginClick(String wz,String phone,String password){
        loginModel.Login(wz,phone,password, new LoginModel.ModelCallPresenter() {
            @Override
            public void success(String msg) {
                mainPresenter.lClick(msg);
            }
        });
    }

    public void regClick(){
        mainPresenter.rClick();
    }

    public interface MainPresenter{
        void lClick(String msg);
        void rClick();
    }
}

