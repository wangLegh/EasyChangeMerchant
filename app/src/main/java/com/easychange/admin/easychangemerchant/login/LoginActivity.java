package com.easychange.admin.easychangemerchant.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.MainActivity;
import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_login, R.id.tv_ruzhu,R.id.tv_forget})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.tv_ruzhu:
                startActivity(new Intent(this,RegistActivity.class));
                break;
            case R.id.tv_forget:
                startActivity(new Intent(this,ForgetPwdActivity.class));
                break;
        }
    }
}
