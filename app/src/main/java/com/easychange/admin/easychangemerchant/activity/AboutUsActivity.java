package com.easychange.admin.easychangemerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.base.BaseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.view_header_back)
    FrameLayout viewHeaderBack;
    @BindView(R.id.view_header_title)
    TextView viewHeaderTitle;
    @BindView(R.id.view_header_rightBtn)
    TextView viewHeaderRightBtn;
    @BindView(R.id.layout_about_liuyan)
    LinearLayout layoutAboutLiuyan;
    @BindView(R.id.layout_about_phone)
    LinearLayout layoutAboutPhone;
    @BindView(R.id.layout_about_us)
    LinearLayout layoutAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        viewHeaderTitle.setText("关于我们");
    }

    @OnClick({R.id.view_header_back, R.id.layout_about_liuyan, R.id.layout_about_phone, R.id.layout_about_us})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_header_back:
                finish();
                break;
            case R.id.layout_about_liuyan:
                startActivity(new Intent(mContext,LiuyanActivity.class));
                break;
            case R.id.layout_about_phone:
                showNoPassDialog();
                break;
            case R.id.layout_about_us:
                Toast.makeText(mContext, "缺少图标", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void showNoPassDialog() {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_kefu_phone)
                .setPaddingdp(0, 0, 0, 0)
                .setGravity(Gravity.BOTTOM)
                .setAnimation(R.style.bottom_tab_style)
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .isOnTouchCanceled(true)
                .builder();
        dialog.show();
    }
}
