package com.liangyang.recycleviewtouch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liangyang.recycleviewtouch.R;
import com.liangyang.recycleviewtouch.bean.DataBean;

import java.util.List;

/**
 * 创建日期：2017/1/17 on 16:22
 * 作者:杨亮 liangyang
 * 描述:创建适配器
 * 1.先创建ViewHolder(继承RecyclerView.ViewHolder)
 * 2.创建适配器(继承RecyclerView.Adapter<RecyclerView.ViewHolder>)
 * 3.注意：RecyclerView没有提供setOnItemClickListener这个回调，自己在Adapter中添加这个回调接口
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    /**
     * 自定义构造RecyclerView的setOnItemClickListener这个回调
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    private List<DataBean> dataBeanList;
    private Context context;
    private LayoutInflater mInflater;

    public RecycleViewAdapter(Context context, List<DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycle_view_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
        //return new MyViewHolder(view);
    }

    /**
     * 将数据绑定到ViewHolder，设置值
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //数据映射
        DataBean dataBean = dataBeanList.get(position);
        holder.name.setText(dataBean.getName());
        holder.time.setText(dataBean.getTime());
        holder.message.setText(dataBean.getMessage());
        holder.imageView.setImageResource(dataBean.getImage_id());
//        holder.imageView.setBackgroundResource(dataBean.getImage_id());

        /**
         * 如果设置了回调，则设置点击事件
         */
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //mOnItemClickListener.onItemClick(holder.imageView,position);
                    mOnItemClickListener.onItemClick(holder.imageView,position);
                }
            });
        }
    }

    /**
     * 获取总的条目数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    /**
     * 创建适配器的缓存容器
     * 1、创建一个ViewHolder，并且需要继承RecyclerView.ViewHolder
     * 2、其实创建的是一个适配器
     * Created by Jerry
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name, time, message;
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_username);
            time = (TextView) itemView.findViewById(R.id.tv_time);
            message = (TextView) itemView.findViewById(R.id.tv_message);
            imageView = (ImageView) itemView.findViewById(R.id.iv_icon);
        }
    }
}
