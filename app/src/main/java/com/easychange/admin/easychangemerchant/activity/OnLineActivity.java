package com.easychange.admin.easychangemerchant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.adapter.OnLineAdapter;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.bean.ActionBean;
import com.easychange.admin.easychangemerchant.event.EventUtils;
import com.easychange.admin.easychangemerchant.p.ActionPresenter;
import com.easychange.admin.easychangemerchant.views.WanRecyclerView;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * admin  2018/8/16 wan
 */
public class OnLineActivity extends BaseActivity implements WanRecyclerView.PullRecyclerViewCallBack, OnLineAdapter.OnItemClickListener, ActionPresenter.ActionCallBack {

    private WanRecyclerView mRecyclerView;

    private List<ActionBean> lists;

    private OnLineAdapter adapter;

    private ActionPresenter presenter;

    private int page = 1;

    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_line);

        presenter = new ActionPresenter(this, this);

        presenter.getActionList(page, "1", "");

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

        EventBus.getDefault().register(this);
    }

    @Override
    public void onRefresh() {
        page = 1;
        lists.clear();
        presenter.getActionList(page, "1", "");
    }

    @Override
    public void onLoadMore() {
        page++;
        presenter.getActionList(page, "1", "");
    }

    @Override
    public void onItemClickListener(ActionBean actionBean, int position) {
        this.position = position;
        OnLineDetailsActivity.invoke(this, actionBean);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRecevierEvent(EventUtils event) {
        switch (event.getType()) {
            case EventUtils.TYPE_REFRESH_ACTION_LIST:
                lists.remove(position);
                adapter.notifyDataSetChanged();
                break;
            case EventUtils.TYPE_ON_LINE_REFRESH:
                page = 1;
                lists.clear();
                presenter.getActionList(page, "1", "");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
