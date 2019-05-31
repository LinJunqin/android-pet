package com.lin.goods.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.baselib.ui.FlowLayout;
import com.lin.baselib.ui.SearchListView;
import com.lin.goods.R;
import com.lin.goods.presenter.SearchPresenter;
import com.lin.goods.utils.RecordSQLiteOpenHelper;
import com.lin.goods.view.SearchView;

/**
 * Created by lin on 2019/5/9.
 */
@Route(path = ArouterPath.ACTIVITY_SEARCH)
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchView{

    /**
     * 初始化成员变量
     */

    private EditText etSearch; // 搜索按键
    private Button tvClear;  // 删除搜索记录按键
    private TextView searchBack; // 返回按键
    private FlowLayout hisSearch; //历史搜索
    private TextView noHisSearch; //提示暂无搜索记录字样
    // ListView列表 & 适配器
    private SearchListView listView;
    private BaseAdapter adapter;

    // 数据库变量
    // 用于存放历史搜索记录
    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;
    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_search;
    }

    @Override
    public void initData() {
        //实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(this);
        //第1次进入时查询所有的历史搜索记录
        queryData("");
    }

    @Override
    public void initView() {
        etSearch = (EditText) findViewById(R.id.et_search);
        etSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    String key = etSearch.getText().toString();
                    //Toast.makeText(SearchActivity.this, "需要搜索的是" + et_search.getText(), Toast.LENGTH_SHORT).show();

                    // 2. 点击搜索键后，对该搜索字段在数据库是否存在进行检查（查询）
                    boolean hasData = hasData(etSearch.getText().toString().trim());
                    // 3. 若存在，则不保存；若不存在，则将该搜索字段保存（插入）到数据库，并作为历史搜索记录
                    if (!hasData) {
                        if ( etSearch.getText().toString().trim().length() > 0){
                            insertData(etSearch.getText().toString().trim());
                            hisSearch.addTag(etSearch.getText().toString().trim());
                            tvClear.setVisibility(View.VISIBLE);
                            noHisSearch.setVisibility(View.GONE);
                        }
                    }
                    ARouter.getInstance().build(ArouterPath.ACTIVITY_SEARCH_RESULT).withString("key",key).navigation();


                }
                return false;
            }
        });
        listView = (SearchListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 获取用户点击列表里的文字,并自动填充到搜索框内
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                String name = textView.getText().toString();
                etSearch.setText(name);
                etSearch.setSelection(name.length());
            }
        });
        tvClear = (Button) findViewById(R.id.tv_clear);
        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                builder.setMessage("确定要删除全部历史记录？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteData();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.create().show();
            }
        });
        searchBack = (TextView) findViewById(R.id.search_back);
        /**
         * 返回按钮返回上一层
         */
        searchBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }

        });
        hisSearch = (FlowLayout) findViewById(R.id.his_search);
        noHisSearch = (TextView) findViewById(R.id.no_search_history);
        hisSearch.setOnTagClickListener(new FlowLayout.OnTagClickListener() {
            @Override
            public void TagClick(String text) {
                // 获取用户点击列表里的文字,并自动填充到搜索框内
                etSearch.setText(text);
                etSearch.setSelection(text.length());

            }
        });
    }

    /**
     * 清空数据库
     */
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
        hisSearch.cleanTag();
        tvClear.setVisibility(View.INVISIBLE);
        noHisSearch.setVisibility(View.VISIBLE);
    }
    /**
     * 模糊查询数据 & 显示到ListView列表上
     */
    private void queryData(String tempName) {

        // 1. 模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 2. 创建adapter适配器对象 & 装入模糊搜索的结果
        adapter = new SimpleCursorAdapter(SearchActivity.this, android.R.layout.simple_list_item_1, cursor, new String[] { "name" },
                new int[] { android.R.id.text1 }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 3. 设置适配器
        //listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // 当输入框为空 & 数据库中有搜索记录时，显示 "删除搜索记录"按钮
        if ( cursor.getCount() != 0){
            while ( cursor.moveToNext()){
                hisSearch.addTag(cursor.getString(1));
            }
            tvClear.setVisibility(View.VISIBLE);
            noHisSearch.setVisibility(View.GONE);
        }
        else {
            Log.i("没有搜索记录","yes");
            tvClear.setVisibility(View.INVISIBLE);
            noHisSearch.setVisibility(View.VISIBLE);
        }

    }
    /**
     * 检查数据库中是否已经有该搜索记录
     */
    private boolean hasData(String tempName) {
        // 从数据库中Record表里找到name=tempName的id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        return cursor.moveToNext();
    }
    /**
     * 插入数据到数据库，即写入搜索字段到历史搜索记录
     */
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }
}
