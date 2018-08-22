package com.easychange.admin.easychangemerchant.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.bean.AboutUsBean;
import com.easychange.admin.easychangemerchant.http.JsonCallback;
import com.easychange.admin.easychangemerchant.utils.MyUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

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
    @BindView(R.id.tv_weixin)
    TextView tv_weixin;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_guanwang)
    TextView tv_guanwang;
    @BindView(R.id.tv_phone)
    TextView tv_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_usdes);
        ButterKnife.bind(this);
        viewHeaderTitle.setText("关于我们");
        OkGo.<AboutUsBean>get("http://39.106.220.142/aboutus/aboutusList")
                .tag(this)
                .execute(new JsonCallback<AboutUsBean>() {
                    @Override
                    public void onSuccess(Response<AboutUsBean> response) {
                        List<AboutUsBean.DataEntity> data = response.body().getData();
                        if (data != null && data.size() > 0) {
                            tv_weixin.setText(data.get(1).getContent());
                            tv_phone.setText(data.get(0).getContent());
                            tv_email.setText(data.get(2).getContent());
                            tv_guanwang.setText(data.get(3).getContent());
                        }
                    }
                });
        tvMineName.setText("V "+MyUtils.getAppVersion(this));
    }

    @OnClick(R.id.view_header_back)
    public void onClick() {
        finish();
    }
}
