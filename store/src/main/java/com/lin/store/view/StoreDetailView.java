package com.lin.store.view;

import com.lin.baselib.base.BaseView;
import com.lin.store.entity.StoreDetailDTO;

/**
 * Created by lin on 2019/5/10.
 */

public interface StoreDetailView extends BaseView{
    void updateData(StoreDetailDTO data);
}
