package com.easychange.admin.easychangemerchant.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.adapter.MingxiAdapter;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.views.WanRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MingxiActivity extends BaseActivity implements MingxiAdapter.OnItemClickListener, WanRecyclerView.PullRecyclerViewCallBack {

    @BindView(R.id.view_header_back)
    FrameLayout viewHeaderBack;
    @BindView(R.id.view_header_title)
    TextView viewHeaderTitle;
    @BindView(R.id.view_header_rightBtn)
    TextView viewHeaderRightBtn;
    @BindView(R.id.mingxi_recyclerView)
    WanRecyclerView mingxiRecyclerView;
    private List<String> lists;

    private MingxiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mingxi);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        viewHeaderTitle.setText("明细");
        mingxiRecyclerView.setLinearLayout();

        lists = new ArrayList<>();
        adapter = new MingxiAdapter(lists, this);
        mingxiRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        mingxiRecyclerView.setPullRecyclerViewListener(this);
    }

    @OnClick({R.id.view_header_back, R.id.view_header_rightBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_header_back:
                finish();
                break;
            case R.id.view_header_rightBtn:
                break;
        }
    }

    @Override
    public void onItemClickListener(int position) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
