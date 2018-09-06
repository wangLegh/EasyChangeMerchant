package com.easychange.admin.easychangemerchant.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.MainActivity;
import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.bean.LoginBean;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.easychange.admin.easychangemerchant.http.ResponseBean2;
import com.easychange.admin.easychangemerchant.utils.CacheUtils;
import com.easychange.admin.easychangemerchant.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginPresenter.TokenCallBack {

    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_ruzhu)
    TextView tvRuzhu;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this, this);
        initData();
    }

    private void initData() {
        String token = CacheUtils.get("token");
        if (!TextUtils.isEmpty(token)){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @OnClick({R.id.tv_login, R.id.tv_ruzhu, R.id.tv_forget})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                doLogin();
                break;
            case R.id.tv_ruzhu:
                startActivity(new Intent(this, RegistActivity.class));
                break;
            case R.id.tv_forget:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
        }
    }

    private void doLogin() {
        if (TextUtils.isEmpty(etUser.getText().toString().trim())) {
            Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!MyUtils.isMobileNO(etUser.getText().toString().trim())) {
            Toast.makeText(LoginActivity.this, "请输入正确格式的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        String passsword = etPassword.getText().toString();
        if (TextUtils.isEmpty(passsword) || TextUtils.isEmpty(passsword)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.getPassLoginInfo(etUser.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void getPassLoginInfo(LoginBean loginBean) {
        CacheUtils.put("id", loginBean.getId());
        CacheUtils.put("token", loginBean.getToken());
        CacheUtils.put("phone", loginBean.getShopPhone());
        CacheUtils.put("head", loginBean.getShopLogo());
        CacheUtils.put("name", loginBean.getBusinessName());
        CacheUtils.put("address", loginBean.getShopAddr());
        CacheUtils.put("tanjifen", loginBean.getCarboniNtegral());
        CacheUtils.put("qrCode", loginBean.getQrCode());
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void getRegistCode(ResponseBean responseBean) {

    }

    @Override
    public void registShop(ResponseBean2 responseBean) {

    }

    @Override
    public void getForgetPswCode(ResponseBean responseBean) {

    }

    @Override
    public void getForgetPassInfo(ResponseBean responseBean) {

    }
}
