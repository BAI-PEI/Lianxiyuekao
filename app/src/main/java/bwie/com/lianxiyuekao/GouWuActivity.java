package bwie.com.lianxiyuekao;

import android.content.Intent;
import android.location.GnssNavigationMessage;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bwie.com.lianxiyuekao.adapter.ExAdapter;
import bwie.com.lianxiyuekao.bean.CartsBean;
import bwie.com.lianxiyuekao.bean.DuiXinag;
import bwie.com.lianxiyuekao.utils.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GouWuActivity extends AppCompatActivity {
    private ExAdapter adapter;
    private ExpandableListView elv;
    private CheckBox checkBox;
    private TextView tvTotal;
    private TextView tvCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_wu);
        tvTotal = findViewById(R.id.tvTotal);
        elv = findViewById(R.id.elv);
        checkBox = findViewById(R.id.cb);
        tvCount=findViewById(R.id.tvCount);
        tvCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GouWuActivity.this,DingDanActivity.class);
                startActivity(intent);
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setCheAll(checkBox.isChecked());
            }
        });
        //去除小箭头
        elv.setGroupIndicator(null);
        OkHttpUtils.getInstance().doGet("http://120.27.23.105/product/getCarts?uid=71&source=android", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CartsBean gson = new Gson().fromJson(string, CartsBean.class);
                        List<CartsBean.DataBean> data = gson.getData();
                        data.remove(0);
                        List<List<CartsBean.DataBean.ListBean>> list=new ArrayList<>();
                        for(int i=0;i<data.size();i++){
                            list.add(data.get(i).getList());
                        }
                        adapter = new ExAdapter(GouWuActivity.this, data,list);
                        elv.setAdapter(adapter);
                        int elvCount = elv.getCount();
                        for (int i=0; i<elvCount; i++)
                        {
                            elv.expandGroup(i);
                        };
                    }
                });
            }
        });
    }
    public void setCheckAll(boolean checkAll) {
        checkBox.setChecked(checkAll);


    }

    public void PriceAndCount(DuiXinag duiXinag) {
        tvTotal.setText("合计:"+duiXinag.getPrice());
        tvCount.setText("去结算(" + duiXinag.getCount() + ")");
    }
}