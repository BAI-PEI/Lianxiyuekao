package bwie.com.lianxiyuekao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bwie.com.lianxiyuekao.R;
import bwie.com.lianxiyuekao.adapter.OrderAdapter;
import bwie.com.lianxiyuekao.bean.OrderBean;
import bwie.com.lianxiyuekao.presenter.OrderPresenter;
import bwie.com.lianxiyuekao.view.OrderView;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public class WaitPayFragment extends Fragment implements OrderView {
    private OrderPresenter presenter;
    private List<OrderBean.DataBean> list = new ArrayList<>();
    private OrderAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_wait_pay, container, false);
        RecyclerView recy_cancel=view.findViewById(R.id.recyclerView);
        presenter = new OrderPresenter(this);
        presenter.getOrder("0");
        adapter = new OrderAdapter(getContext(),list);
        recy_cancel.setAdapter(adapter);
        recy_cancel.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        return view;
    }

    @Override
    public void success(OrderBean bean) {
        list.clear();
        list.addAll(bean.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void failure(int code) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //presenter.dettach();
    }
}
