package bwie.com.lianxiyuekao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.lianxiyuekao.GouWuActivity;
import bwie.com.lianxiyuekao.R;
import bwie.com.lianxiyuekao.bean.CartsBean;
import bwie.com.lianxiyuekao.bean.DuiXinag;
import bwie.com.lianxiyuekao.bean.PriceAndCount;

/**
 * Created by BAIPEI on 2018/1/17.
 */

public class ExAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<CartsBean.DataBean> group;
    private List<List<CartsBean.DataBean.ListBean>> child;
    private final LayoutInflater inflater;
    public ExAdapter(Context context, List<CartsBean.DataBean> group, List<List<CartsBean.DataBean.ListBean>> child) {
        this.context=context;
        this.group=group;
        this.child= child;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        final GroupViewHolder holder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.elv_group, null);
            holder = new GroupViewHolder();
            holder.tv = view.findViewById(R.id.tvGroup);
            holder.cbGroup = view.findViewById(R.id.cbGroup);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (GroupViewHolder) view.getTag();
        }
        final CartsBean.DataBean dataBean = group.get(groupPosition);
        holder.tv.setText(dataBean.getSellerName());
        holder.cbGroup.setChecked(dataBean.isChecked());
        holder.cbGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要改变三个checkbox的状态值
                //1.一级列表的checkbox状态值
                dataBean.setChecked(holder.cbGroup.isChecked());
                //2.二级列表的checkbox状态值
                setChildrenCb(groupPosition, holder.cbGroup.isChecked());
                //3.全选的checkbox状态值
                ((GouWuActivity) context).setCheckAll(setCheckAll());
                //计算钱和数量并显示
                setPriceAndCountText();
                //刷新界面
                notifyDataSetChanged();
            }
        });


        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        final ChildViewHolder holder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.elv_child, null);
            holder = new ChildViewHolder();
            holder.iv = view.findViewById(R.id.iv);
            holder.tvTitle = view.findViewById(R.id.tvTitle);
            holder.tvSubhead = view.findViewById(R.id.tvSubhead);
            holder.tvPrice = view.findViewById(R.id.tvPrice);
            holder.cbChild = view.findViewById(R.id.cbChild);
            holder.btDel = view.findViewById(R.id.btDel);
            holder.tvNum = view.findViewById(R.id.tvNum);
            holder.ivDel = view.findViewById(R.id.ivDel);
            holder.ivAdd = view.findViewById(R.id.ivAdd);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ChildViewHolder) view.getTag();
        }
        holder.btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //其实就是删除集合
                List<CartsBean.DataBean.ListBean> listBeans = child.get(groupPosition);
                if (listBeans.size() > 0) {
                    listBeans.remove(childPosition);
                }
                if (listBeans.size() == 0) {
                    child.remove(groupPosition);
                    group.remove(groupPosition);
                }
                //计算钱和数量并显示
                setPriceAndCount();
                //改变全选状态
                ((GouWuActivity) context).setCheckAll(isAllGroupCbChecked());
                //刷新列表
                notifyDataSetChanged();
            }
        });

        holder.tvNum.setText(child.get(groupPosition).get(childPosition).getCount()+"");

        holder.cbChild.setChecked(child.get(groupPosition).get(childPosition).isChecked());
        CartsBean.DataBean.ListBean listBean = child.get(groupPosition).get(childPosition);
        holder.tvTitle.setText(listBean.getTitle());
        holder.tvPrice.setText(listBean.getPrice()+"");
        String[] split = listBean.getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.iv);
        holder.cbChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CartsBean.DataBean.ListBean> listBeans = child.get(groupPosition);
                //自己
                listBeans.get(childPosition).setChecked(holder.cbChild.isChecked());
                //上级
                group.get(groupPosition).setChecked(SetGroupCheck(groupPosition));
                //全选
                ((GouWuActivity)context).setCheckAll(setCheckAll());
                setPriceAndCountText();
                notifyDataSetChanged();
            }
        });
        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = child.get(groupPosition).get(childPosition).getCount();
                count++;
                child.get(groupPosition).get(childPosition).setCount(count);
                setPriceAndCountText();
                notifyDataSetChanged();
            }
        });
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = child.get(groupPosition).get(childPosition).getCount();
                if(count<=1){
                    count=1;
                }else{
                    count--;
                }
                child.get(groupPosition).get(childPosition).setCount(count);
                setPriceAndCountText();
                notifyDataSetChanged();
            }
        });
        return view;
    }
    public void setPriceAndCountText(){
        ((GouWuActivity)context).PriceAndCount(setPriceAndCount());
    }

    private DuiXinag setPriceAndCount() {
        int price=0;
        int count=0;
        for(int i=0;i<child.size();i++){
            List<CartsBean.DataBean.ListBean> listBeans = child.get(i);
            for(int y=0;y<listBeans.size();y++){
                if(listBeans.get(y).isChecked()){
                    price+=listBeans.get(y).getCount()*listBeans.get(y).getPrice();
                    count+=listBeans.get(y).getCount();
                }
            }
        }
        return new DuiXinag(price,count);
    }

    private boolean setCheckAll() {
        for(int i=0;i<group.size();i++){
            if(!group.get(i).isChecked()){
                return false;
            }
        }
        return true;

    }

    private boolean SetGroupCheck(int groupPosition) {
        List<CartsBean.DataBean.ListBean> listBeans = child.get(groupPosition);
        for(int i=0;i<listBeans.size();i++){
            if(!listBeans.get(i).isChecked()){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupViewHolder{
        TextView tv;
        CheckBox cbGroup;
    }
    class ChildViewHolder {
        ImageView iv;
        TextView tvTitle;
        TextView tvSubhead;
        TextView tvPrice;
        CheckBox cbChild;
        Button btDel;
        TextView tvNum;
        ImageView ivDel;
        ImageView ivAdd;
    }
    /**
     * 设置一级列表对应的二级列表checkbox状态
     *
     * @param groupPosition
     * @param bool
     */
    private void setChildrenCb(int groupPosition, boolean bool) {
        List<CartsBean.DataBean.ListBean> listBeans = child.get(groupPosition);
        for (int i = 0; i < listBeans.size(); i++) {
            listBeans.get(i).setChecked(bool);
        }
    }

    /**
     * 判断一级列表checkbox状态
     *
     * @return
     */
    private boolean isAllGroupCbChecked() {
        if (group.size() == 0) {
            return false;
        }
        for (int i = 0; i < group.size(); i++) {
            if (!group.get(i).isChecked()) {
                return false;
            }
        }
        return true;
    }

    public void setCheAll(boolean checked){
        for(int l=0;l<group.size();l++){
            group.get(l).setChecked(checked);
        }

        for(int i=0;i<child.size();i++){
            for(int y=0;y<child.get(i).size();y++){
                child.get(i).get(y).setChecked(checked);
            }
        }
        setPriceAndCountText();
        notifyDataSetChanged();
    }


}