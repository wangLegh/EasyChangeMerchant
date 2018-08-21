package com.easychange.admin.easychangemerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.bean.ChangePhoneBean;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.easychange.admin.easychangemerchant.p.ChangePhonePresenter;
import com.easychange.admin.easychangemerchant.utils.MyUtils;
import com.easychange.admin.easychangemerchant.views.CountDownUtils;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SureChangePhoneActivity extends BaseActivity implements ChangePhonePresenter.ChangePhoneCallBack {

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
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.activity_change_phone_activitty)
    LinearLayout activityChangePhoneActivitty;
    private CountDownUtils countDownUtils;
    private ChangePhonePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_change_phone);
        ButterKnife.bind(this);
        viewHeaderTitle.setText("更换手机");
        presenter = new ChangePhonePresenter(this, this);
        countDownUtils = new CountDownUtils(1000 * 60, 1000, tvGetcode);
    }

    @OnClick({R.id.view_header_back, R.id.tv_getcode, R.id.tv_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_header_back:
                finish();
                break;
            case R.id.tv_getcode:
                if (TextUtils.isEmpty(tvPhoneNumber.getText().toString()) || !MyUtils.isMobileNO(tvPhoneNumber.getText().toString())) {
                    Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.GetCodeRequest(tvPhoneNumber.getText().toString(), "2");
                break;
            case R.id.tv_complete:
                if (TextUtils.isEmpty(tvPhoneNumber.getText().toString()) || !MyUtils.isMobileNO(tvPhoneNumber.getText().toString())) {
                    Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(tvCode.getText().toString())) {
                    Toast.makeText(mContext, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.ChangePhoneRequest(tvPhoneNumber.getText().toString(), tvCode.getText().toString());
                break;
        }
    }

    @Override
    public void requestChangePhoneSuccess(Response<ResponseBean<ChangePhoneBean>> response) {
        if (response.body().code == 200) {
            Toast.makeText(mContext, response.body().msg, Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
            setResult(1,intent);
            finish();
        } else {
            Toast.makeText(mContext, response.body().msg, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void requestFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestGetCodeSuccess() {
        Toast.makeText(mContext, "获取验证码成功", Toast.LENGTH_SHORT).show();
        countDownUtils.start();
    }

    @Override
    public void requestNextSuccess(Response<ResponseBean> message) {

    }
}
