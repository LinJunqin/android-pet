package com.lin.goods.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.goods.R;
import com.lin.goods.adapter.GoodsAdapter;
import com.lin.goods.entity.Goods;
import com.lin.goods.presenter.SearchResultPresenter;
import com.lin.goods.view.SearchResultView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2019/5/9.
 */
@Route(path = ArouterPath.ACTIVITY_SEARCH_RESULT)
public class SearchResultActivity extends BaseActivity<SearchResultPresenter> implements SearchResultView{
   private RecyclerView rvGoods;
   private List<Goods> goodsList = new ArrayList<>();
   private GoodsAdapter adapter;

    String key;
    @Override
    protected SearchResultPresenter createPresenter() {
        return new SearchResultPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_search_result;
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
        rvGoods = findViewById(R.id.rv_goods);
        adapter = new GoodsAdapter(goodsList);
        adapter.setOnItemClickListener(new GoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ARouter.getInstance().build(ArouterPath.ACTIVITY_GOODS_DETAIL).withLong("goodsId",goodsList.get(position).getGoodsId()).navigation();
            }
        });
        rvGoods.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvGoods.setAdapter(adapter);
    }

    @Override
    public void updateList(List<Goods> goods) {
        goodsList.clear();
        goodsList.addAll(goods);
        adapter.notifyDataSetChanged();
    }
}
