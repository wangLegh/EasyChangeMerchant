package com.easychange.admin.easychangemerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.views.CountDownUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePhoneActivitty extends BaseActivity {

    @BindView(R.id.view_header_back)
    FrameLayout viewHeaderBack;
    @BindView(R.id.view_header_title)
    TextView viewHeaderTitle;
    @BindView(R.id.view_header_rightBtn)
    TextView viewHeaderRightBtn;
    @BindView(R.id.tv_phone_number)
    EditText tvPhoneNumber;
    @BindView(R.id.tv_code)
    EditText tvCode;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.activity_change_phone_activitty)
    LinearLayout activityChangePhoneActivitty;
    private CountDownUtils countDownUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_activitty);
        ButterKnife.bind(this);
        viewHeaderTitle.setText("更换手机");
        countDownUtils = new CountDownUtils(1000*60,1000,tvGetcode);
    }

    @OnClick({R.id.view_header_back, R.id.tv_getcode, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_header_back:
                finish();
                break;
            case R.id.tv_getcode:
                countDownUtils.start();
                break;
            case R.id.tv_next:
                startActivity(new Intent(ChangePhoneActivitty.this,SureChangePhoneActivity.class));
                break;
        }
    }
}
