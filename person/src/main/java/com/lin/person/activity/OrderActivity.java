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
import com.lin.baselib.ui.RecyclerViewDivider;
import com.lin.person.R;
import com.lin.person.adapter.OrderAdapter;
import com.lin.person.entity.Order;
import com.lin.person.presenter.OrderPresenter;
import com.lin.person.view.OrderView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2019/5/10.
 */
@Route(path = ArouterPath.ACTIVITY_ORDER)
public class OrderActivity extends BaseActivity<OrderPresenter> implements OrderView{
    private ImageButton back;
    private RecyclerView rvOrder;
    private List<Order> orderList = new ArrayList<>();
    private OrderAdapter orderAdapter;
    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.person_activity_order;
    }

    @Override
    public void initData() {
        presenter.initData();

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
        rvOrder = findViewById(R.id.rv_order);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        rvOrder.addItemDecoration(new RecyclerViewDivider(getBaseContext(), LinearLayoutManager.VERTICAL, 1, ContextCompat.getColor(getBaseContext(), R.color.gray)));

        orderAdapter = new OrderAdapter(orderList,presenter);
        orderAdapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ARouter.getInstance().build(ArouterPath.ACTIVITY_ORDER_DETAIL).withSerializable("order",orderList.get(position)).navigation();
            }
        });
        orderAdapter.setOnItemLongClickListener(new OrderAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, final int position) {
                final PopupMenu popup = new PopupMenu(OrderActivity.this, view);
                getMenuInflater().inflate(R.menu.person_popup_menu_del, popup.getMenu());
                popup.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                if(item.getItemId()==R.id.popup_menu_del){
                                    presenter.delete(orderList.get(position).getOrderId(),position);
                                }
                                return true;
                            }
                        });
                popup.show();
            }
        });
        rvOrder.setAdapter(orderAdapter);

    }

    @Override
    public void updateList(List<Order> orderList) {
        this.orderList.clear();
        this.orderList.addAll(orderList);
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateList() {
        initData();
    }

    @Override
    public void updateList(int position) {
        orderList.remove(position);
        orderAdapter.notifyItemRemoved(position);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }
}
