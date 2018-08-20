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
public class HistoryDetailsActivity extends BaseActivity {

    private ActionBean actionBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detaiils);

        actionBean = getIntent().getParcelableExtra("action");

        TextView title = findViewById(R.id.view_header_title);
        title.setText("历史活动详情");
        findViewById(R.id.view_header_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvActionName = findViewById(R.id.act_history_actionName);
        tvActionState = findViewById(R.id.act_history_actionState);
        tvActionLimit = findViewById(R.id.act_history_actionLimit);
        tvSuccessNum = findViewById(R.id.act_history_successNum);
        tvActionTime = findViewById(R.id.act_history_actionTime);
        tvCoinNum = findViewById(R.id.act_history_coinNum);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        tvActionName.setText("活动名称：" + actionBean.getActivityTitle());

        tvActionTime.setText(format.format(actionBean.getBeginTime()) + "-" + format.format(actionBean.getEndTime()));

        tvActionLimit.setText("满" + actionBean.getFull() + "减" + actionBean.getSub());

        tvSuccessNum.setText(actionBean.getOrderQuantity() + "单");

        tvCoinNum.setText(actionBean.getGainCurrency() + "易换币");

        switch (actionBean.getOnsale()) {
            case "1":
                tvActionState.setText("上线");
                break;
            case "0":
                tvActionState.setText("下线");
                break;
        }
    }

    private TextView tvActionName;
    private TextView tvActionState;
    private TextView tvActionLimit;
    private TextView tvSuccessNum;
    private TextView tvActionTime;
    private TextView tvCoinNum;

    public static void invoke(Context context, ActionBean actionBean){
        Intent intent = new Intent(context, HistoryDetailsActivity.class);
        intent.putExtra("action", actionBean);
        context.startActivity(intent);
    }
}
