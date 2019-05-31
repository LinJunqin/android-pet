package com.lin.person.view;

import com.lin.baselib.base.BaseView;
import com.lin.person.entity.OrderDetailDTO;

/**
 * Created by lin on 2019/5/10.
 */

public interface OrderDetailView extends BaseView {
    void update();

    void updateData(OrderDetailDTO orderDetailDTO);
}
