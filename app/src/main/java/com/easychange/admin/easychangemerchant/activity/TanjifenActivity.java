package com.easychange.admin.easychangemerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.adapter.TanjifenAdapter;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.bean.DuihuanBean;
import com.easychange.admin.easychangemerchant.bean.MingxiBean;
import com.easychange.admin.easychangemerchant.p.TanjifenPresenter;
import com.easychange.admin.easychangemerchant.utils.CacheUtils;
import com.easychange.admin.easychangemerchant.views.WanRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TanjifenActivity extends BaseActivity implements TanjifenAdapter.OnItemClickListener, WanRecyclerView.PullRecyclerViewCallBack, TanjifenPresenter.MingxiCallBack {

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
    @BindView(R.id.tv_no_more)
    TextView tvNoMore;
    private List<String> lists;
    private TanjifenAdapter adapter;
    int pageNum = 1;
    private List<DuihuanBean> mData = new ArrayList<>();
    private TanjifenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanjifen);
        ButterKnife.bind(this);
        presenter = new TanjifenPresenter(this, this);
        presenter.getDuihuanList();
        initView();
    }

    private void initView() {
        viewHeaderTitle.setText("碳积分");
        viewHeaderRightBtn.setText("明细");
        tvTanjifenNum.setText(CacheUtils.get("tanjifen") + "");
        viewHeaderRightBtn.setVisibility(View.VISIBLE);
        tanjifenRecyclerView.setLinearLayout();

        lists = new ArrayList<>();
        adapter = new TanjifenAdapter(mData, this);
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
                startActivity(new Intent(mContext, MingxiActivity.class));
                break;
        }
    }

    @Override
    public void onItemClickListener(int position) {

    }

    @Override
    public void onRefresh() {
        mData.clear();
        presenter.getDuihuanList();
    }

    @Override
    public void onLoadMore() {
        presenter.getDuihuanList();
    }

    @Override
    public void getMingxiList(List<MingxiBean> datas) {
    }

    @Override
    public void getDuihuanList(List<DuihuanBean> datas) {
        if (datas != null) {
            if (datas.size()<=0){
                tanjifenRecyclerView.setVisibility(View.GONE);
                tvNoMore.setVisibility(View.VISIBLE);
                return;
            }
            tanjifenRecyclerView.setVisibility(View.VISIBLE);
            tvNoMore.setVisibility(View.GONE);
            mData.addAll(datas);
            tanjifenRecyclerView.setHasMore(datas.size(), 10);
        } else {
            tanjifenRecyclerView.setHasMore(0, 10);
        }
        adapter.notifyDataSetChanged();
        tanjifenRecyclerView.setStateView(mData.size());
    }

    @Override
    public void getMingxiListFail(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
