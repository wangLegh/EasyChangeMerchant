package com.easychange.admin.easychangemerchant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.activity.SubmitAuditDetailsActivity;
import com.easychange.admin.easychangemerchant.adapter.AuditActionAdapter;
import com.easychange.admin.easychangemerchant.base.BaseFragment;
import com.easychange.admin.easychangemerchant.views.WanRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * admin  2018/8/17 wan
 */
public class AuditActionFragment extends BaseFragment implements WanRecyclerView.PullRecyclerViewCallBack, AuditActionAdapter.OnItemClickListener {

    private WanRecyclerView mRecyclerView;

    private List<String> lists;

    private AuditActionAdapter adapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_audit_action, null);
        mRecyclerView = view.findViewById(R.id.frag_auditAction_recyclerView);
        mRecyclerView.setLinearLayout();

        lists = new ArrayList<>();
        adapter = new AuditActionAdapter(getContext(), lists);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setPullRecyclerViewListener(this);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClickListener() {
        SubmitAuditDetailsActivity.invoke(mContext);
    }
}
