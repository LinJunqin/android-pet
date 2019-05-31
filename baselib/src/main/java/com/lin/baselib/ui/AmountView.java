package com.lin.baselib.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lin.baselib.R;


/**
 * Created by lin on 2018/5/24.
 */


public class AmountView extends LinearLayout implements View.OnClickListener, TextWatcher {

    private static final String TAG = "AmountView";
    private int position=-1;

    private int amount = 0; //购买数量
    private int goods_storage = 100; //商品库存
    private EditText etAmount;
    private Button btnDecrease;
    private Button btnIncrease;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //组件布局
        LayoutInflater.from(context).inflate(R.layout.baselib_view_amount, this);
        etAmount = (EditText) findViewById(R.id.etAmount);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        etAmount.setVisibility(GONE);
        btnDecrease.setVisibility(GONE);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);
        etAmount.setFocusable(false);
    }


    /**
     * 位置
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * 设置库存方法
     * @param goods_storage
     */
    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
    }

    /**
     * 获取数量
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     * 设置数量
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
        etAmount.setText(this.amount + "");
        if (this.amount>=0){
            etAmount.setVisibility(VISIBLE);
            btnDecrease.setVisibility(VISIBLE);
        }
    }

    /**
     * 增加，减少事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > 0) {
                amount--;
                etAmount.setText(amount + "");
                if (amount==0){
                    etAmount.setVisibility(GONE);
                    btnDecrease.setVisibility(GONE);
                }
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                etAmount.setText(amount + "");
                if (amount>0){
                    etAmount.setVisibility(VISIBLE);
                    btnDecrease.setVisibility(VISIBLE);
                }
            }
        }

        etAmount.clearFocus();

        if (mListener != null) {
            mListener.onAmountChange(this, amount,position);
        }
    }

    /**
     * 数量变化监听
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty())
            return;
        amount = Integer.valueOf(s.toString());
        if (amount > goods_storage) {
            etAmount.setText(goods_storage + "");
            return;
        }

    }

    /**
     * 自定义接口 监听数量变化，
     */
    private OnAmountChangeListener mListener;

    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount, int position);
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }
}
