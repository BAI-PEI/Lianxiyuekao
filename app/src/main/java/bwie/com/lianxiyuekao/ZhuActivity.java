package bwie.com.lianxiyuekao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import bwie.com.lianxiyuekao.presenter.LoginPresenter;

public class ZhuActivity extends AppCompatActivity implements LoginPresenter.MainPresenter{
    private EditText rphone,rpassword;
    private Button lreg;
    private ImageView iv_fan;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        rphone = (EditText) findViewById(R.id.phone);
        rpassword = (EditText) findViewById(R.id.password);
        lreg = (Button) findViewById(R.id.btn_zhu);
        iv_fan=findViewById(R.id.iv_fan);
        loginPresenter = new LoginPresenter(this);
        iv_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ZhuActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        lreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.loginClick("reg", rphone.getText().toString(), rpassword.getText().toString());
            }
        });
    }
    @Override
    public void lClick(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void rClick() {

    }

}