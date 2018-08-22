package com.easychange.admin.easychangemerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.EasyApplication;
import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.adapter.MessageAdapter;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.bean.MessageBean;
import com.easychange.admin.easychangemerchant.http.DialogCallback;
import com.easychange.admin.easychangemerchant.http.HttpManager;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.easychange.admin.easychangemerchant.views.WanRecyclerView;
import com.lzy.okgo.model.Response;

import java.io.Serializable;
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
    private MessageAdapter adapter;
    private int pageNum = 1;
    private List<MessageBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_message_activitty);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        new HttpManager<ResponseBean<List<MessageBean>>>("merchantApp/systemnoticeList", this)
                .addParams("id", EasyApplication.getUserId())
                .addParams("pageNum", pageNum)
                .getRequets(new DialogCallback<ResponseBean<List<MessageBean>>>(MessageActivitty.this) {
                    @Override
                    public void onSuccess(Response<ResponseBean<List<MessageBean>>> response) {
                        if (response.body().code == 200) {
                            List<MessageBean> data1 = response.body().data;
                            if (messageRecyclerView.isLoadMore()) {
                                if (data != null && data.size() > 0) {
                                    if (data1 != null && data1.size() > 0) {
                                        MessageActivitty.this.data.addAll(data1);
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        pageNum--;
                                        Toast.makeText(MessageActivitty.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                if (data1 != null && data1.size() > 0) {
                                    data = data1;
                                    adapter = new MessageAdapter(data, MessageActivitty.this);
                                    messageRecyclerView.setAdapter(adapter);
                                    adapter.setOnItemClickListener(MessageActivitty.this);
                                } else {
                                    Toast.makeText(MessageActivitty.this, "没有数据", Toast.LENGTH_SHORT).show();
                                }
                            }
                            messageRecyclerView.setPullLoadMoreCompleted();
                            messageRecyclerView.setPullRecyclerViewListener(MessageActivitty.this);
                        }
                    }
                });
    }

    private void initView() {
        viewHeaderTitle.setText("我的消息");
        messageRecyclerView.setLinearLayout();
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
        Intent intent = new Intent(mContext, MessageDetailsActivity.class);
        intent.putExtra("data", (Serializable) data.get(pos));
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        pageNum = 1;
        initData();
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        initData();
    }
}
