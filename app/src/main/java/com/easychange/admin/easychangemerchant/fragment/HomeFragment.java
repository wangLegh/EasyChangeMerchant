package com.easychange.admin.easychangemerchant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.adapter.HomeGridAdapter;
import com.easychange.admin.easychangemerchant.base.BaseFragment;
import com.easychange.admin.easychangemerchant.bean.HomeGridBean;

import java.util.ArrayList;
import java.util.List;

/**
 * admin  2018/8/16 wan
 */
public class HomeFragment extends BaseFragment {

    private TextView title;

    private GridView gridView;

    private HomeGridAdapter gridAdapter;

    private List<HomeGridBean> beans;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home, null);
        view.findViewById(R.id.view_header_back).setVisibility(View.GONE);
        title = view.findViewById(R.id.view_header_title);
        title.setText("首页");

        gridView = view.findViewById(R.id.frag_gridView);

        beans = new ArrayList<>();

        gridAdapter = new HomeGridAdapter(beans, mContext);
        gridView.setAdapter(gridAdapter);
        return view;
    }

    @Override
    protected void initLazyData() {

    }
}
