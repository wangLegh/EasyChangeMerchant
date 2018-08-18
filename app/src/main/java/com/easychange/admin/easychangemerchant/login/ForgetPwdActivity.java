package com.easychange.admin.easychangemerchant.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.utils.MyUtils;
import com.easychange.admin.easychangemerchant.utils.SendSmsTimerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author power
 * @date 2018-06-06 17:44:36
 * @description: 忘记密码页面
 */
public class ForgetPwdActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_verify)
    EditText etVerify;
    @BindView(R.id.tv_verify)
    TextView tvVerify;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_again_pwd)
    EditText etAgainPwd;
    @BindView(R.id.tv_reset)
    TextView tvReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.iv_back, R.id.tv_verify, R.id.tv_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_verify:
                if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
                    Toast.makeText(ForgetPwdActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (MyUtils.isMobileNO(etPhone.getText().toString().trim())) {
                    Toast.makeText(ForgetPwdActivity.this, "请输入正确格式的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                SendSmsTimerUtils.sendSms(tvVerify, R.color.colorPrimary, R.color.colorPrimary);
                break;
            case R.id.tv_reset:
                String phoneNum = etPhone.getText().toString();
                String verifyNum = etVerify.getText().toString();
                String passsword = etPassword.getText().toString();
                String againPwd = etAgainPwd.getText().toString();
                if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(verifyNum)) {
                    Toast.makeText(this, "手机号或验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passsword) || TextUtils.isEmpty(passsword)) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.equals(passsword, againPwd)) {
                    Toast.makeText(this, "密码输入不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }
    }

}
