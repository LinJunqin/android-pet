package com.lin.store.view;

import com.lin.baselib.base.BaseView;
import com.lin.store.entity.Store;

import java.util.List;

/**
 * Created by lin on 2019/5/9.
 */

public interface SearchResultView extends BaseView {
    void updateList(List<Store> stores);
}
