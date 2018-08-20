package com.easychange.admin.easychangemerchant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.activity.SubmitPassDetailsActivity;
import com.easychange.admin.easychangemerchant.adapter.AuditActionAdapter;
import com.easychange.admin.easychangemerchant.base.BaseFragment;
import com.easychange.admin.easychangemerchant.bean.ActionBean;
import com.easychange.admin.easychangemerchant.p.ActionPresenter;
import com.easychange.admin.easychangemerchant.views.WanRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * admin  2018/8/17 wan
 */
public class PassActionFragment extends BaseFragment implements AuditActionAdapter.OnItemClickListener, WanRecyclerView.PullRecyclerViewCallBack, ActionPresenter.ActionCallBack {
    private WanRecyclerView mRecyclerView;

    private List<ActionBean> lists;

    private AuditActionAdapter adapter;
    private ActionPresenter presenter;

    private int page = 1;

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

        presenter = new ActionPresenter(getActivity(), this);

        return view;
    }

    @Override
    protected void initLazyData() {
        presenter.getActionList(page, "4", "");

    }

    @Override
    public void onRefresh() {
        page = 1;
        lists.clear();
        presenter.getActionList(page, "4", "");
    }

    @Override
    public void onLoadMore() {
        page ++;
        presenter.getActionList(page, "4", "");
    }

    @Override
    public void onItemClickListener(ActionBean actionBean, int position) {
        SubmitPassDetailsActivity.invoke(mContext, actionBean);
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
