package com.easychange.admin.easychangemerchant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;

/**
 * admin  2018/8/16 wan
 */
public class OnLineDetailsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_line_details);

        TextView title = findViewById(R.id.view_header_title);
        TextView editTv = findViewById(R.id.view_header_rightBtn);
        editTv.setVisibility(View.VISIBLE);
        editTv.setText("编辑");
        title.setText("活动详情");
        findViewById(R.id.view_header_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
 
        editTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionEditActivity.invoke(OnLineDetailsActivity.this);
            }
        });

        tvActionName = findViewById(R.id.act_onLine_actionName);
        tvActionTime = findViewById(R.id.act_onLine_actionTime);
        tvSuccessNum = findViewById(R.id.act_onLine_successNum);
        tvCoinNum = findViewById(R.id.act_onLine_coinNum);
        tvActionLimit = findViewById(R.id.act_onLine_actionLimit);
        stopAction = findViewById(R.id.act_onLine_stopAction);
        stopAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private TextView tvActionName;
    private TextView tvActionTime;
    private TextView tvSuccessNum;
    private TextView tvCoinNum;
    private TextView tvActionLimit;
    private TextView stopAction;

    public static void invoke(Context context){
        Intent intent = new Intent(context, OnLineDetailsActivity.class);
        context.startActivity(intent);
    }
}
