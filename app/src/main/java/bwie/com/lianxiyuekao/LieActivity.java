package bwie.com.lianxiyuekao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import bwie.com.lianxiyuekao.adapter.MyRecyAdapter;
import bwie.com.lianxiyuekao.bean.InfoDatas;
import bwie.com.lianxiyuekao.presenter.RecyPresenter;
import bwie.com.lianxiyuekao.view.IRecyView;

public class LieActivity extends AppCompatActivity implements IRecyView {
    private RecyclerView xrv;
    private RecyPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lie);
        initView();
        presenter = new RecyPresenter(this);
        presenter.showRecy();
    }
    private void initView() {
        xrv =findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xrv.setLayoutManager(manager);
    }
    @Override
    public void showRecy(final InfoDatas infoDatas) {
        MyRecyAdapter myRecyAdapter = new MyRecyAdapter(this, infoDatas);
        xrv.setAdapter(myRecyAdapter);

        myRecyAdapter.setClick(new MyRecyAdapter.Click() {
            @Override
            public void itemclick(int position) {
                Toast.makeText(LieActivity.this, position+"", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LieActivity.this,XiangActivity.class);
                intent.putExtra("pid",position+"");
                startActivity(intent);
            }
        });
    }
}
