package com.lin.goods.view;

import com.lin.baselib.base.BaseView;
import com.lin.goods.entity.Address;

/**
 * Created by lin on 2019/5/9.
 */

public interface GoodsOrderView extends BaseView {
    void update(Address address);
}
