package com.lin.goods.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lin.baselib.arouter.ArouterHelper;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseFragment;
import com.lin.goods.R;
import com.lin.goods.adapter.GoodsAdapter;
import com.lin.goods.entity.Goods;
import com.lin.goods.presenter.HomePresenter;
import com.lin.goods.view.HomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2018/12/14.
 */
@Route(path = ArouterPath.FRAGMENT_HOME)
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {
    private TextView tvSearch;
    private ImageView ivSearch;
    private RecyclerView rvGoods;
    private List<Goods> goodsList = new ArrayList<>();
    private SwipeRefreshLayout srlGoods;
    GoodsAdapter adapter;
    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void initData() {
       presenter.initData();
    }

    @Override
    public void initView(View root) {
     tvSearch = root.findViewById(R.id.tv_search);
     tvSearch.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             ArouterHelper.gotoSearchActivity();
         }
     });
     ivSearch = root.findViewById(R.id.iv_search);
     ivSearch.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             ArouterHelper.gotoSearchActivity();
         }
     });

     rvGoods = root.findViewById(R.id.rv_goods);
      adapter = new GoodsAdapter(goodsList);
        adapter.setOnItemClickListener(new GoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ARouter.getInstance().build(ArouterPath.ACTIVITY_GOODS_DETAIL).withLong("goodsId",goodsList.get(position).getGoodsId()).navigation();
            }
        });
     rvGoods.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
     rvGoods.setAdapter(adapter);
     srlGoods = root.findViewById(R.id.srl_goods);
        srlGoods.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                       initData();
                    }
                }
        );
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment_home;
    }


    @Override
    public void update(List<Goods> goodsList) {
        this.goodsList.addAll(goodsList);
        adapter.notifyDataSetChanged();
        if(srlGoods.isRefreshing()){
            srlGoods.setRefreshing(false);
        }
    }
}
