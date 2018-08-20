package com.easychange.admin.easychangemerchant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.bean.ActionBean;
import com.easychange.admin.easychangemerchant.p.SoldOutPresenter;

import java.text.SimpleDateFormat;

/**
 * admin  2018/8/16 wan
 */
public class OnLineDetailsActivity extends BaseActivity implements SoldOutPresenter.SoldOutCallBack {

    private ActionBean actionBean;

    private SoldOutPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_line_details);

        actionBean = getIntent().getParcelableExtra("action");

        presenter = new SoldOutPresenter(this, this);

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

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        tvActionName.setText("活动名称：" + actionBean.getActivityTitle());

        tvActionTime.setText(format.format(actionBean.getBeginTime()) + "-" + format.format(actionBean.getEndTime()));

        tvActionLimit.setText("满" + actionBean.getFull() + "减" + actionBean.getSub());

        tvSuccessNum.setText(actionBean.getOrderQuantity() + "单");

        tvCoinNum.setText(actionBean.getGainCurrency() + "易换币");

        stopAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setSoldOutRequest(actionBean.getId() + "");
            }
        });
    }

    private TextView tvActionName;
    private TextView tvActionTime;
    private TextView tvSuccessNum;
    private TextView tvCoinNum;
    private TextView tvActionLimit;
    private TextView stopAction;

    public static void invoke(Context context, ActionBean actionBean){
        Intent intent = new Intent(context, OnLineDetailsActivity.class);
        intent.putExtra("action", actionBean);
        context.startActivity(intent);
    }

    @Override
    public void requestSoldOutSuccess() {
        Toast.makeText(this,"操作成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestSoldOutFail() {
        Toast.makeText(this,"操作失败", Toast.LENGTH_SHORT).show();
    }
}
