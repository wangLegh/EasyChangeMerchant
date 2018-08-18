package com.easychange.admin.easychangemerchant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * admin  2018/8/16 wan
 */
public class PayFragment extends BaseFragment {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Unbinder unbinder;
    private List<String> tab_list = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_pay, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initLazyData() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (tab_list.size() == 0) {
            tab_list.add("优惠券核销");
            tab_list.add("易换币核销");
        }
        if (fragmentList.size() == 0) {
            fragmentList.add(new QuanFragment());
            fragmentList.add(new BiFragment());
        }
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < tab_list.size(); i++) {
            tablayout.addTab(tablayout.newTab().setText(tab_list.get(i)));
        }
        //设置中间竖线
        LinearLayout linearLayout = (LinearLayout) tablayout.getChildAt(0);
        linearLayout.setDividerPadding(30);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(mContext,
                R.drawable.layout_divider_vertical));
        BaseTabAdapter adapter = new BaseTabAdapter(getChildFragmentManager(), fragmentList, tab_list);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}