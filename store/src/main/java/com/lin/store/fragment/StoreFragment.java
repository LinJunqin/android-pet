package com.lin.store.fragment;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lin.baselib.arouter.ArouterHelper;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseFragment;
import com.lin.baselib.ui.RecyclerViewDivider;
import com.lin.store.R;
import com.lin.store.adapter.ListDropDownAdapter;
import com.lin.store.adapter.StoreAdapter;
import com.lin.store.entity.Store;
import com.lin.store.presenter.StorePresenter;
import com.lin.store.view.StoreView;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lin on 2018/12/14.
 */
@Route(path = ArouterPath.FRAGMENT_STORE)
public class StoreFragment extends BaseFragment<StorePresenter> implements StoreView {
    private TextView tvSearch;
    private ImageView ivSearch;
    StoreAdapter adapter;
    //筛选控件
    private String headers[] = {"类型", "距离", "评分"};
    private List<View> popupViews = new ArrayList<>();
    private ListDropDownAdapter typeAdapter;
    private ListDropDownAdapter distanceAdapter;
    private ListDropDownAdapter scoreAdapter;
    private List<String> type = new ArrayList<>();
    private List<String> distance = new ArrayList<>();
    private List<String> score = new ArrayList<>();
    private int constellationPosition = 0;
    private List<Store> storeList = new ArrayList<>();
    private List<Store> storeListCopy = new ArrayList<>();
    private DropDownMenu dropDownMenu;

    @Override
    protected StorePresenter createPresenter() {
        return new StorePresenter(this);
    }

    @Override
    public void initData() {
        presenter.initData(storeList);

    }

    @Override
    public void initView(View root) {
        tvSearch = root.findViewById(R.id.tv_search);

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArouterHelper.gotoStoreSearchActivity();
            }
        });
        ivSearch = root.findViewById(R.id.iv_search);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArouterHelper.gotoStoreSearchActivity();
            }
        });
        dropDownMenu = root.findViewById(R.id.dropDownMenu);
        // 筛选控件
        type.add("美容院");
        type.add("宠物医院");
        final ListView typeView = new ListView(getContext());
        typeView.setDividerHeight(0);
        typeAdapter = new ListDropDownAdapter(getContext(), type);
        typeView.setAdapter(typeAdapter);
        typeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeAdapter.setCheckItem(position);
                dropDownMenu.setTabText(type.get(position));
                dropDownMenu.closeMenu();
                storeList.clear();
                for(Store store:storeListCopy){
                    if(store.getType()==(byte)position){
                        storeList.add(store);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

       distance.add("1-2km");
       distance.add("2-3km");
        distance.add("3-9km");
        final ListView distanceView = new ListView(getContext());
        distanceAdapter = new ListDropDownAdapter(getContext(), distance);
        distanceView.setDividerHeight(0);
        distanceView.setAdapter(distanceAdapter);
        distanceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                distanceAdapter.setCheckItem(position);
                dropDownMenu.setTabText(distance.get(position));
                dropDownMenu.closeMenu();
                storeList.clear();
                for(Store store:storeListCopy){
                    if(position==0){
                        if(store.getDistance()<2&&store.getDistance()>=1){
                            storeList.add(store);
                        }
                    }else if(position==1){
                        if(store.getDistance()<3&&store.getDistance()>2){
                            storeList.add(store);
                        }
                    }else if(position==2){
                        if(store.getDistance()<9&&store.getDistance()>3){
                            storeList.add(store);
                        }
                    }

                }
                adapter.notifyDataSetChanged();


            }
        });

        score.add("0.0-2.5");
        score.add("2.5-5");
        final ListView scoreView = new ListView(getContext());
        scoreView.setDividerHeight(0);
        scoreAdapter = new ListDropDownAdapter(getContext(), score);
        scoreView.setAdapter(scoreAdapter);
        scoreView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                scoreAdapter.setCheckItem(position);
                dropDownMenu.setTabText(score.get(position));
                dropDownMenu.closeMenu();
                storeList.clear();
                for(Store store:storeListCopy){
                    if(position==0){
                        if(store.getScore()<2.5&&store.getScore()>=0.0){
                            storeList.add(store);
                        }
                    }else{
                        if(store.getScore()<5.0&&store.getScore()>2.5){
                            storeList.add(store);
                        }
                    }

                }
                adapter.notifyDataSetChanged();

            }
        });

        popupViews.add(typeView);
        popupViews.add(distanceView);
        popupViews.add(scoreView);


        RecyclerView contentView = (RecyclerView) getLayoutInflater().inflate(R.layout.store_recycle_store, null);
        adapter = new StoreAdapter(storeList);
        adapter.setOnItemClickListener(new StoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               // ArouterHelper.gotoStoreDetailActivity(storeList.get(position).getHospitalId());
                ARouter.getInstance().build(ArouterPath.ACTIVITY_STORE_DETAIL).withLong("hospitalId",storeList.get(position).getHospitalId()).navigation();
            }
        });
        contentView.setAdapter(adapter);
        contentView.setLayoutManager(new LinearLayoutManager(getContext()));
        contentView.setNestedScrollingEnabled(false);
        contentView.addItemDecoration(new RecyclerViewDivider(getContext(), LinearLayoutManager.VERTICAL, 1, ContextCompat.getColor(getContext(), R.color.gray_dark)));
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.store_fragment_store;
    }


    @Override
    public void updateList() {
        storeListCopy.addAll(storeList);
        adapter.notifyDataSetChanged();
    }
}
