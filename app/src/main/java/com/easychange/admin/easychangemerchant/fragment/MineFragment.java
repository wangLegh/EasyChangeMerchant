package com.easychange.admin.easychangemerchant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseFragment;

/**
 * admin  2018/8/16 wan
 */
public class MineFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_mine, null);
        return view;
    }

    @Override
    protected void initLazyData() {

    }
}