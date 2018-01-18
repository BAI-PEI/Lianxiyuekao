package bwie.com.lianxiyuekao;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;

import bwie.com.lianxiyuekao.bean.XiangBean;
import bwie.com.lianxiyuekao.presenter.XiangQiangpresenter;
import bwie.com.lianxiyuekao.utils.OkHttpUtils;
import bwie.com.lianxiyuekao.view.XiangCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XiangActivity extends AppCompatActivity implements XiangCallBack {
    private XiangQiangpresenter xiangQiangpresenter;
    private TextView m2_tv1,m2_tv2,m2_tv3;
    private ImageView m2_iv;
    private Button m2_btn1,m2_btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        m2_tv1 = findViewById(R.id.m2_tv1);
        m2_iv = findViewById(R.id.m2_iv);
        m2_tv2=findViewById(R.id.m2_tv2);
        m2_tv3= findViewById(R.id.m2_tv3);
        //购物车按钮
        m2_btn1=findViewById(R.id.m2_btn1);
        //加入购物车按钮
        m2_btn2=findViewById(R.id.m2_btn2);
        //添加删除线
        m2_tv2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        Intent intent = getIntent();
        final String pid = intent.getStringExtra("pid");
        xiangQiangpresenter = new XiangQiangpresenter(this);
        xiangQiangpresenter.getData(pid);
        m2_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(XiangActivity.this,GouWuActivity.class);
                startActivity(intent1);
            }
        });
        m2_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XiangActivity.this, pid, Toast.LENGTH_SHORT).show();
                addData(pid);
            }
        });
    }

    private void addData(String pid) {
        OkHttpUtils.getInstance().doGet("http://120.27.23.105/product/addCart?uid=" + "71" + "&pid=" + pid + ""+"&source=android", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(XiangActivity.this,string , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void success(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                XiangBean bean = new Gson().fromJson(s, XiangBean.class);
                XiangBean.DataBean data = bean.getData();
                String[] split = data.getImages().split("\\|");
                Glide.with(XiangActivity.this).load(split[1]).into(m2_iv);
                m2_tv1.setText(data.getTitle());
                m2_tv2.setText("原价:"+data.getSalenum()+"");
                m2_tv3.setText("现价:"+data.getPrice()+"");
            }
        });
    }

    @Override
    public void error(Exception e) {

    }
}