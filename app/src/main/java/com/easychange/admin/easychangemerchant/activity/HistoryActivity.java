package com.easychange.admin.easychangemerchant.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.adapter.OnLineAdapter;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.bean.ActionBean;
import com.easychange.admin.easychangemerchant.p.ActionPresenter;
import com.easychange.admin.easychangemerchant.views.WanRecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * admin  2018/8/16 wan
 */
public class HistoryActivity extends BaseActivity implements OnLineAdapter.OnItemClickListener, WanRecyclerView.PullRecyclerViewCallBack, View.OnClickListener, ActionPresenter.ActionCallBack {
    private WanRecyclerView mRecyclerView;

    private List<ActionBean> lists;

    private OnLineAdapter adapter;

    private TextView tvTime;

    private ActionPresenter presenter;

    private int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        presenter = new ActionPresenter(this, this);

        presenter.getActionList(page, "2", format.format(System.currentTimeMillis()));

        TextView title = findViewById(R.id.view_header_title);
        title.setText("历史活动");
        findViewById(R.id.view_header_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTime = findViewById(R.id.act_history_time);

        tvTime.setText(format.format(System.currentTimeMillis()).replace("-", "."));

        mRecyclerView = findViewById(R.id.act_submit_recyclerView);

        mRecyclerView.setLinearLayout();

        lists = new ArrayList<>();
        adapter = new OnLineAdapter(lists, this);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setPullRecyclerViewListener(this);
        tvTime.setOnClickListener(this);
    }

    @Override
    public void onItemClickListener(ActionBean actionBean, int position) {
        HistoryDetailsActivity.invoke(this, actionBean);
    }

    @Override
    public void onRefresh() {
        String time = tvTime.getText().toString();
        page = 1;
        lists.clear();
        presenter.getActionList(page, "2", time.replace(".", "-"));
    }

    @Override
    public void onLoadMore() {
        String time = tvTime.getText().toString();
        page++;
        presenter.getActionList(page, "2", time.replace(".", "-"));
    }

    private void showTimeSelect() throws ParseException {
        Calendar dayc1 = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        Date daystart = df.parse(tvTime.getText().toString());
        dayc1.setTime(daystart);

        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date2, View v) {//选中事件回调
                SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                tvTime.setText(format.format(date2));
                page = 1;
                lists.clear();
                presenter.getActionList(page, "2", format.format(date2).replace(".", "-"));
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
        pvTime.setDate(dayc1);//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_history_time:
                try {
                    showTimeSelect();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void getActionList(List<ActionBean> data) {
        if (data != null) {
            lists.addAll(data);
            mRecyclerView.setHasMore(data.size(), 10);
        } else {
            mRecyclerView.setHasMore(0, 10);
        }
        adapter.notifyDataSetChanged();
        mRecyclerView.setStateView(lists.size());
    }

    @Override
    public void getActionListFail() {
        mRecyclerView.setHasMore(0, 10);
    }

}
