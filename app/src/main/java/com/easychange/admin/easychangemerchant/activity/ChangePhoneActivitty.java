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
import com.easychange.admin.easychangemerchant.http.ResponseBean2;
import com.easychange.admin.easychangemerchant.p.ChangePhonePresenter;
import com.easychange.admin.easychangemerchant.utils.MyUtils;
import com.easychange.admin.easychangemerchant.views.CountDownUtils;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePhoneActivitty extends BaseActivity implements ChangePhonePresenter.ChangePhoneCallBack {

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
    private ChangePhonePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_activitty);
        ButterKnife.bind(this);
        viewHeaderTitle.setText("更换手机");
        presenter = new ChangePhonePresenter(this,this);
        countDownUtils = new CountDownUtils(1000*60,1000,tvGetcode);
    }

    @OnClick({R.id.view_header_back, R.id.tv_getcode, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_header_back:
                finish();
                break;
            case R.id.tv_getcode:
                if (TextUtils.isEmpty(tvPhoneNumber.getText().toString())|| !MyUtils.isMobileNO(tvPhoneNumber.getText().toString())){
                    Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.GetCodeRequest(tvPhoneNumber.getText().toString(),"1");
                break;
            case R.id.tv_next:
                if (TextUtils.isEmpty(tvPhoneNumber.getText().toString())|| !MyUtils.isMobileNO(tvPhoneNumber.getText().toString())){
                    Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(tvCode.getText().toString())){
                    Toast.makeText(mContext, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
               presenter.NextRequest(tvCode.getText().toString());
                break;
        }
    }

    @Override
    public void requestChangePhoneSuccess(Response<ResponseBean2<ChangePhoneBean>> response) {

    }

    @Override
    public void requestFail(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestGetCodeSuccess() {
        countDownUtils.start();
    }

    @Override
    public void requestNextSuccess(Response<ResponseBean> beanResponse) {

        if (beanResponse.body().code==200){
            Intent intent =new Intent(ChangePhoneActivitty.this,SureChangePhoneActivity.class);
            startActivityForResult(intent,0);
        }else {
            Toast.makeText(mContext, beanResponse.body().msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==1){
            finish();
        }
    }
}
