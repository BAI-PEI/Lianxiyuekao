package bwie.com.lianxiyuekao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import bwie.com.lianxiyuekao.R;
import bwie.com.lianxiyuekao.bean.InfoDatas;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public class MyRecyAdapter extends RecyclerView.Adapter<MyRecyAdapter.MyViewHolder> {
    Context context;
    InfoDatas infoDatas;
    private View view;

    public MyRecyAdapter(Context context, InfoDatas infoDatas) {
        this.context = context;
        this.infoDatas = infoDatas;
    }

    @Override
    public MyRecyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.list_item,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecyAdapter.MyViewHolder holder, final int position) {
        holder.tv1.setText(infoDatas.getData().get(position).getTitle());
        holder.tv2.setText(infoDatas.getData().get(position).getPrice()+"");
        String[] split = infoDatas.getData().get(position).getImages().split("\\|");
        Glide.with(context).load(split[1]).into(holder.iv);
        //每个条目的点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.itemclick(infoDatas.getData().get(position).getPid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return infoDatas.getData().size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv1,tv2;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
        }
    }
    //点击事件---------------------------------
    private Click click;
    public void setClick(Click click){
        this.click=click;
    }
    public interface Click{
        void itemclick(int position);
    }
    //-----------------------------------------
}
