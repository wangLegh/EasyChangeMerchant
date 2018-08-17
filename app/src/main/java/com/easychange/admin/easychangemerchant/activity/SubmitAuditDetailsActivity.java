package com.easychange.admin.easychangemerchant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.base.BaseDialog;

/**
 * admin  2018/8/17 wan
 */
public class SubmitAuditDetailsActivity extends BaseActivity {
    private TextView btnPass;
    private TextView btnNoPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_audit_details);

        TextView title = findViewById(R.id.view_header_title);
        title.setText("已提交活动详情");
        findViewById(R.id.view_header_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnPass = findViewById(R.id.act_submit_audit_pass_btn);
        btnNoPass = findViewById(R.id.act_submit_audit_no_pass_btn);
        tvActionName = findViewById(R.id.act_submit_audit_actionName);
        tvActionTime = findViewById(R.id.act_submit_audit_actionTime);
        tvActionLimit = findViewById(R.id.act_submit_audit_actionLimit);
        tvCouponTime = findViewById(R.id.act_submit_audit_couponTime);
        tvCouponPrice = findViewById(R.id.act_submit_audit_couponPrice);
        tvCouponNum = findViewById(R.id.act_submit_audit_couponNum);

        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoPassDialog();
            }
        });

        btnNoPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private TextView tvActionName;
    private TextView tvActionTime;
    private TextView tvActionLimit;
    private TextView tvCouponTime;
    private TextView tvCouponPrice;
    private TextView tvCouponNum;

    private void showNoPassDialog() {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_pass_action)
                .setPaddingdp(20, 0, 20, 0)
                .setGravity(Gravity.CENTER)
                .setAnimation(R.style.bottom_tab_style)
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .isOnTouchCanceled(true)
                .builder();

        TextView cancel = dialog.getView(R.id.dialog_no_pass_cancel);
        TextView submit = dialog.getView(R.id.dialog_no_pass_submit);
        EditText editText = dialog.getView(R.id.dialog_no_pass_et);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dialog.show();
    }

    public static void invoke(Context context){
        Intent intent = new Intent(context, SubmitAuditDetailsActivity.class);
        context.startActivity(intent);
    }
}
