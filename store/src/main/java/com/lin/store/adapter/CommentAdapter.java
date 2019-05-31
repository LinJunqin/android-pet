package com.lin.store.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lin.baselib.base.BaseApplication;
import com.lin.baselib.util.ImageLoaderUtil;
import com.lin.baselib.util.string.DateFormat;
import com.lin.store.R;
import com.lin.store.entity.CommentDTO;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lin on 2019/5/14.
 */

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<CommentDTO> commentList;
    public CommentAdapter(List<CommentDTO> commentList){
        this.commentList = commentList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root;
        root = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_comment_item, parent, false);
        return new CommentHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommentHolder commentHolder = (CommentHolder)holder;
        CommentDTO commentDTO = commentList.get(position);
        ImageLoaderUtil.getInstance(BaseApplication.getInstance()).displayImage(commentHolder.civUser,commentDTO.getAvatar());
        commentHolder.tvUsername.setText(commentDTO.getUsername());
        commentHolder.tvTime.setText(DateFormat.format(commentDTO.getCreateTime()));
        commentHolder.tvContent.setText(commentDTO.getContent());

    }

    @Override
    public int getItemCount() {
        if(commentList!=null){
            return commentList.size();
        }
        return 0;
    }
    private static class CommentHolder extends RecyclerView.ViewHolder{
        public CircleImageView civUser;
        public TextView        tvUsername;
        public TextView        tvTime;
        public TextView        tvContent;
        public CommentHolder(View itemView) {
            super(itemView);
            civUser = itemView.findViewById(R.id.civ_user);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
