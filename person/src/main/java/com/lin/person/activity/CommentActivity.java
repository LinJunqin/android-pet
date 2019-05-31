package com.lin.person.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lin.baselib.arouter.ArouterPath;
import com.lin.baselib.base.BaseActivity;
import com.lin.person.R;
import com.lin.person.entity.CommentDTO;
import com.lin.person.presenter.CommentPresenter;
import com.lin.person.view.CommentView;

import java.math.BigDecimal;

/**
 * Created by lin on 2019/5/14.
 */
@Route(path = ArouterPath.ACTIVITY_COMMENT)
public class CommentActivity extends BaseActivity<CommentPresenter> implements CommentView{

    CommentDTO commentDTO;
    EditText etScore;
    EditText etComment;
    Button btnSummit;
    @Override
    protected CommentPresenter createPresenter() {
        return new CommentPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.person_activity_comment;
    }

    @Override
    public void initData() {

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
       commentDTO = (CommentDTO) getIntent().getSerializableExtra("commentDTO");
        etScore = findViewById(R.id.et_score);
        etComment = findViewById(R.id.et_comment);
        btnSummit = findViewById(R.id.btn_summit);
        btnSummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if("".equals(etScore.getText().toString().trim())||"".equals(etComment.getText().toString().trim())){
                        showToast("所填项有空");
                    }else{
                        commentDTO.setScore(new BigDecimal(Double.parseDouble(etScore.getText().toString().trim())));
                        commentDTO.setContent(etComment.getText().toString().trim());
                        presenter.comment(commentDTO);
                    }
                }catch (Exception e){
                    showToast("评分所填为非数字");
                }

            }
        });
    }

    @Override
    public void finishActivity() {
        setResult(RESULT_OK);
        finish();
    }
}
