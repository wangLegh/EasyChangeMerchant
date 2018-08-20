package com.easychange.admin.easychangemerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YihuanbiActivity extends BaseActivity {

    @BindView(R.id.view_header_back)
    FrameLayout viewHeaderBack;
    @BindView(R.id.view_header_title)
    TextView viewHeaderTitle;
    @BindView(R.id.view_header_rightBtn)
    TextView viewHeaderRightBtn;
    @BindView(R.id.tv_faxing_num)
    TextView tvFaxingNum;
    @BindView(R.id.tv_shengyu_num)
    TextView tvShengyuNum;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_shenqing)
    TextView tvShenqing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yihuanbi);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        viewHeaderTitle.setText("易换币");
    }

    @OnClick({R.id.view_header_back, R.id.tv_shenqing})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_header_back:
                finish();
                break;
            case R.id.tv_shenqing:
                startActivity(new Intent(mContext,ShenqingActivity.class));
                break;
        }
    }
}
