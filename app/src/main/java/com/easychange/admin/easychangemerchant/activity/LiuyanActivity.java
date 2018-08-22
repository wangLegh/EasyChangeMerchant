package com.easychange.admin.easychangemerchant.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.p.LiuyanPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LiuyanActivity extends BaseActivity implements LiuyanPresenter.LiuyanCallBack {

    @BindView(R.id.view_header_back)
    FrameLayout viewHeaderBack;
    @BindView(R.id.view_header_title)
    TextView viewHeaderTitle;
    @BindView(R.id.view_header_rightBtn)
    TextView viewHeaderRightBtn;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private LiuyanPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liuyan);
        ButterKnife.bind(this);
        presenter = new LiuyanPresenter(this,this);
        viewHeaderTitle.setText("在线留言");
    }

    @OnClick({R.id.view_header_back, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_header_back:
                finish();
                break;
            case R.id.tv_commit:
                if (!TextUtils.isEmpty(etContent.getText().toString())){
                    presenter.getLiuyanList(etContent.getText().toString());
                }else {
                    Toast.makeText(mContext, "请输入要留言的内容", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void getLiuyanList() {
    finish();
    }

    @Override
    public void getLiuyanListFail(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
