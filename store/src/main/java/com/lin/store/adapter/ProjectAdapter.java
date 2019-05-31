package com.lin.store.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.lin.baselib.arouter.ArouterHelper;
import com.lin.baselib.db.SPManager;
import com.lin.baselib.entity.User;
import com.lin.baselib.util.string.DateFormat;
import com.lin.store.R;
import com.lin.store.entity.PreorderDTO;
import com.lin.store.entity.ProjectDTO;
import com.lin.store.presenter.StoreDetailPresenter;

import java.util.Date;
import java.util.List;

/**
 * Created by lin on 2019/5/14.
 */

public class ProjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<ProjectDTO> projectList;
    Activity context;
    StoreDetailPresenter storeDetailPresenter;
    public ProjectAdapter(List<ProjectDTO> projectList, Activity context,StoreDetailPresenter storeDetailPresenter){
        this.projectList = projectList;
        this.context = context;
        this.storeDetailPresenter = storeDetailPresenter;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root;
        root = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_project_item, parent, false);
        return new ProjectHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProjectHolder projectHolder = (ProjectHolder)holder;
        final ProjectDTO projectDTO = projectList.get(position);
        projectHolder.btnAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SPManager.getInstance().getBoolean("isLoginValid",false)){
                    ArouterHelper.gotoLoginActivity();
                    return;
                }
                final PopupMenu popup = new PopupMenu(context, view);
                context.getMenuInflater().inflate(R.menu.store_popup_menu_selector, popup.getMenu());
                popup.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                PreorderDTO preorderDTO = new PreorderDTO();
                                preorderDTO.setHospitalId(projectDTO.getHospitalId());
                                preorderDTO.setProjectId(projectDTO.getProjectId());
                                preorderDTO.setUserId(User.getUser().getUserId());
                                if(item.getItemId()==R.id.popup_menu_time_selector_1){
                                     preorderDTO.setArriveTime(DateFormat.format("yyyy-MM-dd",new Date(System.currentTimeMillis()))+" 09:00");
                                }else if(item.getItemId()==R.id.popup_menu_time_selector_2){
                                    preorderDTO.setArriveTime(DateFormat.format("yyyy-MM-dd",new Date(System.currentTimeMillis()))+" 11:00");
                                }else if(item.getItemId()==R.id.popup_menu_time_selector_3){
                                    preorderDTO.setArriveTime(DateFormat.format("yyyy-MM-dd",new Date(System.currentTimeMillis()))+" 14:00");
                                }else{
                                    preorderDTO.setArriveTime(DateFormat.format("yyyy-MM-dd",new Date(System.currentTimeMillis()))+" 16:00");
                                }
                                storeDetailPresenter.generatePreorder(preorderDTO);
                                return true;
                            }
                        });
                popup.show();
            }
        });
        projectHolder.tvName.setText(projectDTO.getTitle());
        projectHolder.tvPrice.setText("价格："+projectDTO.getValue());

    }

    @Override
    public int getItemCount() {
        if(projectList!=null){
            return projectList.size();
        }
        return 0;
    }
    private static class ProjectHolder extends RecyclerView.ViewHolder{
        public Button btnAppointment;
        public TextView tvName;
        public TextView tvPrice;
        public ProjectHolder(View itemView) {
            super(itemView);
            btnAppointment = itemView.findViewById(R.id.btn_appointment);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}
