package com.lin.person.view;

import com.lin.baselib.base.BaseView;
import com.lin.person.entity.Order;

import java.util.List;

/**
 * Created by lin on 2019/5/10.
 */

public interface OrderView extends BaseView {
    void updateList(List<Order> orderList);

    void updateList();

    void updateList(int position);
}
