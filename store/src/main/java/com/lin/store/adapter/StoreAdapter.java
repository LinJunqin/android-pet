package com.lin.store.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lin.baselib.base.BaseApplication;
import com.lin.baselib.util.ImageLoaderUtil;
import com.lin.store.R;
import com.lin.store.entity.Store;
import com.xp.stardemo.StarLinearLayout;

import java.util.List;

/**
 * Created by lin on 2019/5/8.
 */

public class StoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private List<Store> storeList;

    public StoreAdapter(List<Store> storeList){
        this.storeList = storeList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root;
        root = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item_store, parent, false);
        return new StoreHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);

        StoreHolder storeHolder = (StoreHolder)holder;
        Store store = storeList.get(position);
        ImageLoaderUtil.getInstance(BaseApplication.getInstance()).displayImage(storeHolder.storeFace,store.getAvatar());
        storeHolder.storeName.setText(store.getHospitalName());
        storeHolder.storeScoreTxt.setScore(store.getScore().floatValue());
        storeHolder.storeScoreNum.setText(store.getScore()+"分");
        for(int i= 0;i<6;i++){
            if(i<store.getProjectNames().size()&&i==0){
                storeHolder.storeTag1.setText(store.getProjectNames().get(i));
            }
            if(i<store.getProjectNames().size()&&i==1){
                storeHolder.storeTag2.setText(store.getProjectNames().get(i));
            }
            if(i<store.getProjectNames().size()&&i==2){
                storeHolder.storeTag3.setText(store.getProjectNames().get(i));
            }
            if(i<store.getProjectNames().size()&&i==3){
                storeHolder.storeTag4.setText(store.getProjectNames().get(i));
            }
            if(i<store.getProjectNames().size()&&i==4){
                storeHolder.storeTag5.setText(store.getProjectNames().get(i));
            }
            if(i<store.getProjectNames().size()&&i==5){
                storeHolder.storeTag6.setText(store.getProjectNames().get(i));
            }

            if(i>=store.getProjectNames().size()&&i==5){
                storeHolder.storeTag6.setVisibility(View.INVISIBLE);
            }
            if(i>=store.getProjectNames().size()&&i==4){
                storeHolder.storeTag5.setVisibility(View.INVISIBLE);
            }
            if(i>=store.getProjectNames().size()&&i==3){
                storeHolder.storeTag4.setVisibility(View.INVISIBLE);
            }
            if(i>=store.getProjectNames().size()&&i==2){
                storeHolder.storeTag3.setVisibility(View.INVISIBLE);
            }
            if(i>=store.getProjectNames().size()&&i==1){
                storeHolder.storeTag2.setVisibility(View.INVISIBLE);
            }
            if(i>=store.getProjectNames().size()&&i==0){
                storeHolder.storeTag1.setVisibility(View.INVISIBLE);
            }

        }
    }

    @Override
    public int getItemCount() {
        if(storeList!=null){
            return storeList.size();
        }
        return 0;
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
    private StoreAdapter.OnItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(StoreAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private static class StoreHolder extends RecyclerView.ViewHolder{
        public ImageView storeFace;
        public TextView storeName;
        public StarLinearLayout storeScoreTxt;
        public TextView storeScoreNum;
        public TextView storeTag1;
        public TextView storeTag2;
        public TextView storeTag3;
        public TextView storeTag4;
        public TextView storeTag5;
        public TextView storeTag6;

        public StoreHolder(View itemView) {
            super(itemView);
            storeFace = itemView.findViewById(R.id.store_face);
            storeName = itemView.findViewById(R.id.store_name);
            storeScoreTxt = itemView.findViewById(R.id.store_score_txt);
            storeScoreNum = itemView.findViewById(R.id.store_score_num);
            storeTag1 = itemView.findViewById(R.id.store_tag_1);
            storeTag2 = itemView.findViewById(R.id.store_tag_2);
            storeTag3 = itemView.findViewById(R.id.store_tag_3);
            storeTag4 = itemView.findViewById(R.id.store_tag_4);
            storeTag5 = itemView.findViewById(R.id.store_tag_5);
            storeTag6 = itemView.findViewById(R.id.store_tag_6);



        }
    }
}
