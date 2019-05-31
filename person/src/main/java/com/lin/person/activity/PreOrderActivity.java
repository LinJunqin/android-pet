package com.lin.person.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.entity.User;
import com.lin.baselib.ui.RecyclerViewDivider;
import com.lin.person.R;
import com.lin.person.adapter.PreOrderAdapter;
import com.lin.person.entity.PreOrder;
import com.lin.person.presenter.PreOrderPresenter;
import com.lin.person.view.PreOrderView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2019/5/10.
 */
@Route(path = ArouterPath.ACTIVITY_PRE_ORDER)
public class PreOrderActivity extends BaseActivity<PreOrderPresenter> implements PreOrderView{
    private ImageButton back;
    private RecyclerView rvPreOrder;
    private List<PreOrder> preOrderList = new ArrayList<>();
    private PreOrderAdapter preOrderAdapter;
    @Override
    protected PreOrderPresenter createPresenter() {
        return new PreOrderPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.person_activity_pre_order;
    }

    @Override
    public void initData() {
        presenter.initData(User.getUser().getUserId(),preOrderList);
    }

    @Override
    public void initView() {
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rvPreOrder = findViewById(R.id.rv_pre_order);
        rvPreOrder.setLayoutManager(new LinearLayoutManager(this));
        rvPreOrder.addItemDecoration(new RecyclerViewDivider(getBaseContext(), LinearLayoutManager.VERTICAL, 1, ContextCompat.getColor(getBaseContext(), R.color.gray)));
        preOrderAdapter = new PreOrderAdapter(preOrderList,presenter);
        preOrderAdapter.setOnItemClickListener(new PreOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PreOrder preOrder = preOrderList.get(position);
                ARouter.getInstance().build(ArouterPath.ACTIVITY_PRE_ORDER_DETAIL).withSerializable("preOrder",preOrder).navigation();

            }
        });
        preOrderAdapter.setOnItemLongClickListener(new PreOrderAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, final int position) {
                final PopupMenu popup = new PopupMenu(PreOrderActivity.this, view);
                getMenuInflater().inflate(R.menu.person_popup_menu_del, popup.getMenu());
                popup.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                if(item.getItemId()==R.id.popup_menu_del){
                                    presenter.delete(preOrderList.get(position).getPreorderId(),position);
                                }
                                return true;
                            }
                        });
                popup.show();
            }
        });
        rvPreOrder.setAdapter(preOrderAdapter);
    }

    @Override
    public void updateList() {
        preOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateList(int position) {
        preOrderList.remove(position);
        preOrderAdapter.notifyItemRemoved(position);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }
}
