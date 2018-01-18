package bwie.com.lianxiyuekao.model;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import bwie.com.lianxiyuekao.bean.MyData;

/**
 * Created by BAIPEI on 2018/1/16.
 */

public class LoginModel {
    ModelCallPresenter modelCallPresenter;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            modelCallPresenter.success((String) msg.obj);
        }
    };

    public void Login(final String wz, final String phone, final String password,ModelCallPresenter modelCallPresenter) {
        this.modelCallPresenter = modelCallPresenter;
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader br;
                try {
                    URL url = new URL("http://120.27.23.105/user/" + wz + "?mobile=" + phone + "&&password=" + password);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    int code = con.getResponseCode();
                    if (code == 200) {
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        Gson gson = new Gson();
                        String data = gson.fromJson(br.readLine(), MyData.class).getMsg();
                        Message m = new Message();
                        m.obj = data;
                        handler.sendMessage(m);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public interface ModelCallPresenter {
        void success(String msg);
    }
}
