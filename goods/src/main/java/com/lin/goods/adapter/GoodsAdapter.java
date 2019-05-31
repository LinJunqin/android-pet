package com.lin.goods.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.baselib.base.BaseApplication;
import com.lin.baselib.util.ImageLoaderUtil;
import com.lin.goods.R;
import com.lin.goods.entity.Goods;

import java.util.List;

/**
 * Created by lin on 2019/5/8.
 */

public class GoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private List<Goods> goodsList;

    public GoodsAdapter(List<Goods> goodsList){
        this.goodsList = goodsList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root;
        root = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_goods, parent, false);
        return new GoodsHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        GoodsHolder goodsHolder = (GoodsHolder)holder;
        Goods goods = goodsList.get(position);
        goodsHolder.tvName.setText(goods.getName());
        goodsHolder.tvPrice.setText("价格：￥"+goods.getPrice()+"");
        ImageLoaderUtil.getInstance(BaseApplication.getInstance()).displayImage(goodsHolder.ivGoodsAvatar,goods.getAvatar());

    }

    @Override
    public int getItemCount() {
        if(goodsList!=null){
            return goodsList.size();
        }
        return 0;
    }
    private static class GoodsHolder extends RecyclerView.ViewHolder{
        public ImageView ivGoodsAvatar;
        public TextView  tvName;
        public TextView  tvPrice;

        public GoodsHolder(View itemView) {
            super(itemView);
            ivGoodsAvatar = itemView.findViewById(R.id.iv_goods_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
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
    private GoodsAdapter.OnItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(GoodsAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
