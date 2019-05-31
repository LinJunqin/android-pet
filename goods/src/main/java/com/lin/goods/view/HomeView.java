package com.lin.goods.view;


import com.lin.baselib.base.BaseView;
import com.lin.goods.entity.Goods;

import java.util.List;

/**
 * Created by lin on 2018/12/14.
 */

public interface HomeView extends BaseView {

    void update(List<Goods> goodsList);
}
