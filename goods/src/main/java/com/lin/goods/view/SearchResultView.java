package com.lin.goods.view;

import com.lin.baselib.base.BaseView;
import com.lin.goods.entity.Goods;

import java.util.List;

/**
 * Created by lin on 2019/5/9.
 */

public interface SearchResultView extends BaseView {
    void updateList(List<Goods> goods);
}
