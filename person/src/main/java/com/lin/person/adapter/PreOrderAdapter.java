package com.lin.person.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseApplication;
import com.lin.baselib.entity.User;
import com.lin.baselib.util.ImageLoaderUtil;
import com.lin.person.R;
import com.lin.person.entity.CommentDTO;
import com.lin.person.entity.PreOrder;
import com.lin.person.presenter.PreOrderPresenter;

import java.util.List;

/**
 * Created by lin on 2019/5/14.
 */

public class PreOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    List<PreOrder> preOrderList;
    PreOrderPresenter preOrderPresenter;
    public PreOrderAdapter(List<PreOrder> preOrderList, PreOrderPresenter preOrderPresenter){
        this.preOrderList = preOrderList;
        this.preOrderPresenter = preOrderPresenter;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root;
        root = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_pre_order, parent, false);
        return new PreOrderHolder(root);
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
        final PreOrder preOrder = preOrderList.get(position);
        final PreOrderHolder preOrderHolder = (PreOrderHolder)holder;
        ImageLoaderUtil.getInstance(BaseApplication.getInstance()).displayImage(preOrderHolder.ivStoreAvatar,preOrder.getAvatar());
        preOrderHolder.tvStoreName.setText(preOrder.getHospitalName());
        preOrderHolder.tvStoreAddress.setText(preOrder.getAddress());
        preOrderHolder.tvPreOrderTime.setText(preOrder.getArriveTime());
        if(preOrder.getStatus().byteValue()==0){
            preOrderHolder.tvOrderStatus.setText("预约成功");
        }else if(preOrder.getStatus().byteValue()==1){
            preOrderHolder.btnComment.setVisibility(View.INVISIBLE);
            preOrderHolder.btnCancel.setVisibility(View.INVISIBLE);
            preOrderHolder.tvOrderStatus.setText("已取消");
            preOrderHolder.tvOrderStatus.setTextColor(Color.rgb(193, 193, 193));
        }else if(preOrder.getStatus().byteValue()==2){
            preOrderHolder.tvOrderStatus.setText("已评价");
            preOrderHolder.btnComment.setVisibility(View.INVISIBLE);
            preOrderHolder.btnCancel.setVisibility(View.INVISIBLE);
            preOrderHolder.tvOrderStatus.setTextColor(Color.rgb(193, 193, 193));
        }

        preOrderHolder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setPreorderId(preOrder.getPreorderId());
                commentDTO.setHospitalId(preOrder.getHospitalId());
                commentDTO.setUserId(User.getUser().getUserId());
                ARouter.getInstance().build(ArouterPath.ACTIVITY_COMMENT).withSerializable("commentDTO",commentDTO).navigation();
            }
        });
        preOrderHolder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               preOrderPresenter.cancelPreOrder(preOrder.getPreorderId());
            }
        });


    }

    @Override
    public int getItemCount() {
        if(preOrderList!=null){
            return preOrderList.size();
        }
        return 0;
    }
    private static class PreOrderHolder extends RecyclerView.ViewHolder{
        public Button btnComment;
        public Button btnCancel;
        public TextView tvOrderStatus;
        public ImageView ivStoreAvatar;
        public TextView tvStoreName;
        public TextView tvStoreAddress;
        public TextView tvPreOrderTime;


        public PreOrderHolder(View itemView) {
            super(itemView);
            btnComment = itemView.findViewById(R.id.btn_comment);
            btnCancel = itemView.findViewById(R.id.btn_cancel);
            tvOrderStatus = itemView.findViewById(R.id.tv_order_status);
            ivStoreAvatar = itemView.findViewById(R.id.iv_store_avatar);
            tvStoreName = itemView.findViewById(R.id.tv_store_name);
            tvStoreAddress = itemView.findViewById(R.id.tv_store_address);
            tvPreOrderTime = itemView.findViewById(R.id.tv_pre_order_time);
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
    private PreOrderAdapter.OnItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(PreOrderAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public  interface OnItemLongClickListener {
        void onItemLongClick(View view , int position);
    }
    private PreOrderAdapter.OnItemLongClickListener mOnItemLongClickListener = null;
    public void setOnItemLongClickListener(PreOrderAdapter.OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }
}
