package com.lin.store.activity;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.ui.RecyclerViewDivider;
import com.lin.store.R;
import com.lin.store.adapter.StoreAdapter;
import com.lin.store.entity.Store;
import com.lin.store.presenter.SearchResultPresenter;
import com.lin.store.view.SearchResultView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2019/5/9.
 */
@Route(path = ArouterPath.ACTIVITY_STORE_SEARCH_RESULT)
public class SearchResultActivity extends BaseActivity<SearchResultPresenter> implements SearchResultView {
    private RecyclerView rvStore;
    private List<Store> storeList = new ArrayList<>();
    StoreAdapter adapter;
    String key;
    @Override
    protected SearchResultPresenter createPresenter() {
        return new SearchResultPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.store_activity_search_result;
    }

    @Override
    public void initData() {
        key = getIntent().getStringExtra("key");
        presenter.search(key);
    }

    @Override
    public void initView() {
        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rvStore = findViewById(R.id.rv_store);
        adapter = new StoreAdapter(storeList);
        adapter.setOnItemClickListener(new StoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ARouter.getInstance().build(ArouterPath.ACTIVITY_STORE_DETAIL).withLong("hospitalId",storeList.get(position).getHospitalId()).navigation();
            }
        });
        rvStore.setLayoutManager(new LinearLayoutManager(this));
        rvStore.addItemDecoration(new RecyclerViewDivider(getBaseContext(), LinearLayoutManager.VERTICAL, 1, ContextCompat.getColor(getBaseContext(), R.color.gray)));
        rvStore.setAdapter(adapter);

    }

    @Override
    public void updateList(List<Store> stores) {
        storeList.clear();
        storeList.addAll(stores);
        adapter.notifyDataSetChanged();
    }
}
