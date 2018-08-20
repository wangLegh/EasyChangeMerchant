package com.easychange.admin.easychangemerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.adapter.MessageAdapter;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.views.WanRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivitty extends BaseActivity implements MessageAdapter.OnItemClickListener, WanRecyclerView.PullRecyclerViewCallBack {

    @BindView(R.id.view_header_back)
    FrameLayout viewHeaderBack;
    @BindView(R.id.view_header_title)
    TextView viewHeaderTitle;
    @BindView(R.id.view_header_rightBtn)
    TextView viewHeaderRightBtn;
    @BindView(R.id.message_recyclerView)
    WanRecyclerView messageRecyclerView;
    private List<String> lists;

    private MessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_message_activitty);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        viewHeaderTitle.setText("我的消息");
        messageRecyclerView.setLinearLayout();

        lists = new ArrayList<>();
        adapter = new MessageAdapter(lists, this);
        messageRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        messageRecyclerView.setPullRecyclerViewListener(this);
    }

    @OnClick({R.id.view_header_back, R.id.view_header_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_header_back:
                finish();
                break;
            case R.id.view_header_title:
                break;
        }
    }

    @Override
    public void onItemClickListener(int pos) {
        Intent intent = new Intent(mContext,MessageDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
