package com.easychange.admin.easychangemerchant.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * admin  2018/8/16 wan
 */
public class ActionEditActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_action);

        TextView title = findViewById(R.id.view_header_title);
        title.setText("编辑活动详情");
        findViewById(R.id.view_header_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvStopTime = findViewById(R.id.act_edit_action_stopTime);
        tvStartTime = findViewById(R.id.act_edit_action_startTime);
        etCompanyName = findViewById(R.id.act_edit_action_companyName);
        etCoinNum = findViewById(R.id.act_edit_action_coinNum);
        etCouponNum = findViewById(R.id.act_edit_action_couponNum);
        submit = findViewById(R.id.act_edit_action_submit);

        tvStopTime.setOnClickListener(this);
        tvStartTime.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    private TextView tvStopTime;
    private TextView tvStartTime;
    private EditText etCompanyName;
    private EditText etCoinNum;
    private EditText etCouponNum;
    private TextView submit;

    private void showStartTimeSelect(){
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date2, View v) {//选中事件回调
                SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                tvStartTime.setText(format.format(date2));
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                .setCancelText(" ")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setContentSize(20)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("日历")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.colorPrimary))//确定按钮文字颜色
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    private void showStopTimeSelect(){
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date2, View v) {//选中事件回调
                SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                tvStopTime.setText(format.format(date2));
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                .setCancelText(" ")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setContentSize(20)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("日历")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.colorPrimary))//确定按钮文字颜色
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    public static void invoke(Context context){
        Intent intent = new Intent(context, ActionEditActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_edit_action_startTime:
                showStartTimeSelect();
                break;
            case R.id.act_edit_action_stopTime:
                showStopTimeSelect();
                break;
            case R.id.act_edit_action_submit:

                break;
        }
    }
}
