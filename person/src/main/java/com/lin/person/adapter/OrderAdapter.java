package com.lin.person.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.baselib.base.BaseApplication;
import com.lin.baselib.util.ImageLoaderUtil;
import com.lin.person.R;
import com.lin.person.entity.Order;
import com.lin.person.presenter.OrderPresenter;

import java.util.List;

/**
 * Created by lin on 2019/5/13.
 */

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    List<Order> orderList;
    OrderPresenter orderPresenter;
    public OrderAdapter(List<Order> orderList, OrderPresenter orderPresenter){
        this.orderList = orderList;
        this.orderPresenter = orderPresenter;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root;
        root = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_order, parent, false);
        return new OrderHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mOnItemLongClickListener != null) {
                    //注意这里使用getTag方法获取position
                    mOnItemLongClickListener.onItemLongClick(view,(int)view.getTag());
                }
                return false;
            }
        });
        final OrderHolder orderHolder = (OrderHolder)holder;
        final Order order = orderList.get(position);
        orderHolder.btnGoodsGot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderPresenter.sureOfGot(order.getOrderId());
                orderHolder.tvOrderStatus.setText("订单已结束");
                orderHolder.tvOrderStatus.setTextColor(Color.rgb(193, 193, 193));
                orderHolder.btnGoodsGot.setVisibility(View.INVISIBLE);
            }
        });
        ImageLoaderUtil.getInstance(BaseApplication.getInstance()).displayImage(orderHolder.ivGoodsAvatar,order.getAvatar());

        orderHolder.tvGoodsName.setText(order.getName());
        orderHolder.tvGoodsAmount.setText("购买数量："+order.getSum());
        orderHolder.tvOrderAccount.setText("总价："+order.getAccount().intValue());
        if(order.getStatus().byteValue()==0){
            orderHolder.tvOrderStatus.setText("进行中");
        }else if(order.getStatus().byteValue()==2){
            orderHolder.tvOrderStatus.setText("订单已结束");
            orderHolder.tvOrderStatus.setTextColor(Color.rgb(193, 193, 193));
            orderHolder.btnGoodsGot.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if(orderList!=null){
            return orderList.size();
        }
        return 0;
    }
    private static class OrderHolder extends RecyclerView.ViewHolder{
        public Button btnGoodsGot;
        public TextView tvOrderStatus;
        public ImageView ivGoodsAvatar;
        public TextView  tvGoodsName;
        public TextView  tvGoodsAmount;
        public TextView  tvOrderAccount;
        public OrderHolder(View itemView) {
            super(itemView);
            btnGoodsGot =   itemView.findViewById(R.id.btn_goods_got);
            tvOrderStatus = itemView.findViewById(R.id.tv_order_status);
            ivGoodsAvatar = itemView.findViewById(R.id.iv_goods_avatar);
            tvGoodsName =   itemView.findViewById(R.id.tv_goods_name);
            tvGoodsAmount = itemView.findViewById(R.id.tv_goods_amount);
            tvOrderAccount =itemView.findViewById(R.id.tv_order_account);
        }
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    public  interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    private OrderAdapter.OnItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OrderAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public  interface OnItemLongClickListener {
        void onItemLongClick(View view , int position);
    }
    private OrderAdapter.OnItemLongClickListener mOnItemLongClickListener = null;
    public void setOnItemLongClickListener(OrderAdapter.OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }
}
