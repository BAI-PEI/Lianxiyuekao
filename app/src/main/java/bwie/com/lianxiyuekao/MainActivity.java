package bwie.com.lianxiyuekao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bwie.com.lianxiyuekao.md5.MD5Helper;
import bwie.com.lianxiyuekao.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity implements LoginPresenter.MainPresenter{
    private EditText phone,password;
    private TextView reg,wang;
    Button login;
    private LoginPresenter loginPresenter;
    MD5Helper md5Helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = findViewById(R.id.phone);
        password =findViewById(R.id.password);
        login =findViewById(R.id.login);
        reg =findViewById(R.id.reg);
        wang=findViewById(R.id.wang);
        wang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "你咋这么笨呢，密码都忘了", Toast.LENGTH_SHORT).show();
            }
        });
        loginPresenter = new LoginPresenter(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String md5 = MD5Helper.getMD5(password.getText().toString());
                loginPresenter.loginClick("login", phone.getText().toString(),md5);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.regClick();
            }
        });
    }
    //登录
    @Override
    public void lClick(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,LieActivity.class);
        startActivity(intent);
    }

    @Override
    public void rClick() {
        startActivity(new Intent(MainActivity.this, ZhuActivity.class));
    }
}