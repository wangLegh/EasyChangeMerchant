package com.easychange.admin.easychangemerchant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.adapter.OnLineAdapter;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.views.WanRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * admin  2018/8/16 wan
 */
public class OnLineActivity extends BaseActivity implements WanRecyclerView.PullRecyclerViewCallBack, OnLineAdapter.OnItemClickListener {

    private WanRecyclerView mRecyclerView;

    private List<String> lists;

    private OnLineAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_line);

        TextView title = findViewById(R.id.view_header_title);
        title.setText("上线活动");
        findViewById(R.id.view_header_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView = findViewById(R.id.act_onLine_recyclerView);
        mRecyclerView.setLinearLayout();

        lists = new ArrayList<>();
        adapter = new OnLineAdapter(lists, this);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setPullRecyclerViewListener(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClickListener() {
        OnLineDetailsActivity.invoke(this);
    }
}
