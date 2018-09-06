package com.easychange.admin.easychangemerchant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.bean.ActionBean;

import java.text.SimpleDateFormat;

/**
 * admin  2018/8/17 wan
 */
public class SubmitPassDetailsActivity extends BaseActivity {
    private ActionBean actionBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_pass_details);

        actionBean = getIntent().getParcelableExtra("action");

        TextView title = findViewById(R.id.view_header_title);
        title.setText("未通过活动详情");
        findViewById(R.id.view_header_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvActionName = findViewById(R.id.act_submit_pass_actionName);
        tvActionTime = findViewById(R.id.act_submit_pass_actionTime);
        tvCouponTime = findViewById(R.id.act_submit_pass_couponTime);
        tvCouponNum = findViewById(R.id.act_submit_pass_couponNum);
        tvCouponPrice = findViewById(R.id.act_submit_pass_couponPrice);
        tvLimitName = findViewById(R.id.act_submit_pass_limitName);
        tvActionLimit = findViewById(R.id.act_submit_pass_actionLimit);
        tvCause = findViewById(R.id.act_submit_pass_cause);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        tvActionName.setText("活动名称：" + actionBean.getActivityTitle());

        tvActionTime.setText(format.format(actionBean.getBeginTime()) + "-" + format.format(actionBean.getEndTime()));
        switch (actionBean.getActivityTime()) {
            case "0":
                tvCouponTime.setText("工作日 每天" + actionBean.getTimeLimit());
                break;
            case "1":
                tvCouponTime.setText("休息日 每天" + actionBean.getTimeLimit());
                break;
            case "2":
                tvCouponTime.setText("全部 每天" + actionBean.getTimeLimit());
                break;
        }

        tvCouponNum.setText(actionBean.getCount() + "");

        tvActionLimit.setText("满" + actionBean.getFull() + "减" + actionBean.getSub());

        tvCouponPrice.setText(actionBean.getPrice() + "易换券");

        tvCause.setText(actionBean.getContent());
    }

    private TextView tvActionName;
    private TextView tvActionTime;
    private TextView tvCouponTime;
    private TextView tvCouponNum;
    private TextView tvCouponPrice;
    private TextView tvLimitName;
    private TextView tvActionLimit;
    private TextView tvCause;

    public static void invoke(Context context, ActionBean actionBean){
        Intent intent = new Intent(context, SubmitPassDetailsActivity.class);
        intent.putExtra("action", actionBean);
        context.startActivity(intent);
    }
}
