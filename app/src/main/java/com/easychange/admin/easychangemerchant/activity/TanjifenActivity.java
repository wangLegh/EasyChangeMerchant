package com.easychange.admin.easychangemerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.adapter.TanjifenAdapter;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.views.WanRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TanjifenActivity extends BaseActivity implements TanjifenAdapter.OnItemClickListener, WanRecyclerView.PullRecyclerViewCallBack {

    @BindView(R.id.view_header_back)
    FrameLayout viewHeaderBack;
    @BindView(R.id.view_header_title)
    TextView viewHeaderTitle;
    @BindView(R.id.view_header_rightBtn)
    TextView viewHeaderRightBtn;
    @BindView(R.id.tv_tanjifen_num)
    TextView tvTanjifenNum;
    @BindView(R.id.tanjifen_recyclerView)
    WanRecyclerView tanjifenRecyclerView;
    private List<String> lists;

    private TanjifenAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanjifen);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        viewHeaderTitle.setText("碳积分");
        viewHeaderRightBtn.setText("明细");
        viewHeaderRightBtn.setVisibility(View.VISIBLE);
        tanjifenRecyclerView.setLinearLayout();

        lists = new ArrayList<>();
        adapter = new TanjifenAdapter(lists, this);
        tanjifenRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        tanjifenRecyclerView.setPullRecyclerViewListener(this);
    }

    @OnClick({R.id.view_header_back, R.id.view_header_rightBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_header_back:
                finish();
                break;
            case R.id.view_header_rightBtn:
                startActivity(new Intent(mContext,MingxiActivity.class));
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
