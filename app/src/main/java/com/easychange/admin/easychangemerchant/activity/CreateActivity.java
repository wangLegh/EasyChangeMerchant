package com.easychange.admin.easychangemerchant.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.easychange.admin.easychangemerchant.EasyApplication;
import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.base.BaseDialog;
import com.easychange.admin.easychangemerchant.p.CreatePresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * admin  2018/8/16 wan
 */
public class CreateActivity extends BaseActivity implements View.OnClickListener, CreatePresenter.CreateCallBack {

    private long startTime;
    private long startSpecificTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        TextView title = findViewById(R.id.view_header_title);
        title.setText("创建活动");
        findViewById(R.id.view_header_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvActionTime = findViewById(R.id.act_create_actionTime);
        tvStopTime = findViewById(R.id.act_create_action_stopTime);
        tvStartTime = findViewById(R.id.act_create_action_startTime);
        tvSpecificStartTime = findViewById(R.id.act_create_action_specificStartTime);
        tvSpecificStopTime = findViewById(R.id.act_create_action_specificStopTime);
        etCompanyName = findViewById(R.id.act_create_action_companyName);
        etCouponNum = findViewById(R.id.act_create_action_couponNum);
        etCoinNum = findViewById(R.id.act_create_action_coinNum);
        etMaxAction = findViewById(R.id.act_create_action_action);
        submit = findViewById(R.id.act_create_action_submit);
        etMaxNum = findViewById(R.id.act_create_action_input_max);
        etMinNum = findViewById(R.id.act_create_action_input_min);

        tvStopTime.setOnClickListener(this);
        tvStartTime.setOnClickListener(this);
        tvActionTime.setOnClickListener(this);
        tvSpecificStartTime.setOnClickListener(this);
        tvSpecificStopTime.setOnClickListener(this);
        submit.setOnClickListener(this);

        createPresenter = new CreatePresenter(this, this);
    }

    private TextView tvActionTime;
    private TextView tvStopTime;
    private TextView tvStartTime;
    private TextView tvSpecificStopTime;
    private TextView tvSpecificStartTime;
    private EditText etCompanyName;
    private EditText etCouponNum;
    private EditText etCoinNum;
    private EditText etMaxNum;
    private EditText etMinNum;
    private TextView etMaxAction;
    private TextView submit;
    private CreatePresenter createPresenter;

    private void showNoPassDialog() {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_create_time)
                .setPaddingdp(0, 0, 0, 0)
                .setGravity(Gravity.CENTER)
                .setAnimation(R.style.bottom_tab_style)
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .isOnTouchCanceled(true)
                .builder();

        RelativeLayout layout = dialog.getView(R.id.dialog_create_time_layout);
        final RadioButton rbAll = dialog.getView(R.id.dialog_create_time_rbAll);
        final RadioButton rbWork = dialog.getView(R.id.dialog_create_time_rbWork);
        final RadioButton rbRest = dialog.getView(R.id.dialog_create_time_rbRest);
        Button submit = dialog.getView(R.id.dialog_create_time_submit);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbAll.isChecked()) {
                    tvActionTime.setText("全部");
                } else if (rbWork.isChecked()) {
                    tvActionTime.setText("工作日");
                } else if (rbRest.isChecked()) {
                    tvActionTime.setText("休息日");
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showStartTimeSelect() {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date2, View v) {//选中事件回调
                startTime = date2.getTime();
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

    private void showStopTimeSelect() {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date2, View v) {//选中事件回调
                if (date2.getTime()>startTime){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                    tvStopTime.setText(format.format(date2));
                }else {
                    Toast.makeText(mContext, "结束时间应该在起始时间之后", Toast.LENGTH_SHORT).show();
                }
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

    private void showSpecificStartTimeSelect() {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date2, View v) {//选中事件回调
                startSpecificTime = date2.getTime();
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                tvSpecificStartTime.setText(format.format(date2));
            }
        })
                .setType(TimePickerView.Type.HOURS_MINS)//默认全部显示
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

    private void showSpecificStopTimeSelect() {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date2, View v) {//选中事件回调
                if (date2.getTime()>startSpecificTime){
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    tvSpecificStopTime.setText(format.format(date2));
                }else {
                    Toast.makeText(mContext, "结束时间应该在起始时间之后", Toast.LENGTH_SHORT).show();
                }

//                tvSpecificStopTime.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
            }
        })
                .setType(TimePickerView.Type.HOURS_MINS)//默认全部显示
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_create_actionTime:
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                showNoPassDialog();
                break;
            case R.id.act_create_action_startTime:
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                showStartTimeSelect();
                break;
            case R.id.act_create_action_stopTime:
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                if (tvStartTime.getText().toString().equals("请选择")){
                    Toast.makeText(mContext, "请选择起始时间", Toast.LENGTH_SHORT).show();
                }else {
                    showStopTimeSelect();
                }
                break;
            case R.id.act_create_action_specificStartTime:
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                showSpecificStartTimeSelect();
                break;
            case R.id.act_create_action_specificStopTime:
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                if (tvSpecificStartTime.getText().toString().equals("请输入具体开始时间")){
                    Toast.makeText(mContext, "请输入具体开始时间", Toast.LENGTH_SHORT).show();
                }else {
                    showSpecificStopTimeSelect();
                }

                break;
            case R.id.act_create_action_submit:
                if (TextUtils.isEmpty(etCompanyName.getText().toString().trim())) {
                    Toast.makeText(this, "请输入活动名称", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.equals(tvStartTime.getText().toString(), "请选择")) {
                    Toast.makeText(this, "请选择起始时间", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.equals(tvStopTime.getText().toString(), "请选择")) {
                    Toast.makeText(this, "请选择结束时间", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.equals(tvSpecificStartTime.getText().toString(), "请输入具体开始时间")) {
                    Toast.makeText(this, "请输入具体开始时间", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.equals(tvSpecificStopTime.getText().toString(), "请输入具体结束时间")) {
                    Toast.makeText(this, "请输入具体结束时间", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.equals(tvActionTime.getText().toString(), "请选择")) {
                    Toast.makeText(this, "请选择活动时间", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(etCouponNum.getText().toString())) {
                    Toast.makeText(this, "请输入优惠券发布数量", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(etCoinNum.getText().toString())) {
                    Toast.makeText(this, "请输入易换币数量", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(etMaxNum.getText().toString())) {
                    Toast.makeText(this, "请设置满减", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(etMinNum.getText().toString())) {
                    Toast.makeText(this, "请设置满减", Toast.LENGTH_SHORT).show();

                } else {
                    //0工作日 1休息日 2不限
                    String actionTime = null;
                    if (TextUtils.equals(tvActionTime.getText().toString(), "工作日")) {
                        actionTime = "0";
                    } else if (TextUtils.equals(tvActionTime.getText().toString(), "休息日")) {
                        actionTime = "1";
                    } else if (TextUtils.equals(tvActionTime.getText().toString(), "全部")) {
                        actionTime = "2";
                    }

                    createPresenter.setCreateAction(etCompanyName.getText().toString().trim(), etCoinNum.getText().toString(),
                            EasyApplication.getUserId(), etMaxNum.getText().toString(), etMinNum.getText().toString(),
                            etCouponNum.getText().toString(), tvStartTime.getText().toString().replace(".", "-"), tvStopTime.getText().toString().replace(".", "-")
                            , tvSpecificStartTime.getText().toString() + "-" + tvSpecificStopTime.getText().toString(),
                            actionTime);
                }
                break;
        }
    }

    @Override
    public void requestCreateActionFail(int code) {

    }

    @Override
    public void requestCreateActionSuccess() {
        finish();
    }
}
