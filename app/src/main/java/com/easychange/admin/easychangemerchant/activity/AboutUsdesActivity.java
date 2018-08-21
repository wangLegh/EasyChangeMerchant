package com.easychange.admin.easychangemerchant.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsdesActivity extends BaseActivity {

    @BindView(R.id.view_header_back)
    FrameLayout viewHeaderBack;
    @BindView(R.id.view_header_title)
    TextView viewHeaderTitle;
    @BindView(R.id.view_header_rightBtn)
    TextView viewHeaderRightBtn;
    @BindView(R.id.img_mine_header)
    ImageView imgMineHeader;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_usdes);
        ButterKnife.bind(this);
        viewHeaderTitle.setText("关于我们");
    }

    @OnClick(R.id.view_header_back)
    public void onClick() {
        finish();
    }
}
