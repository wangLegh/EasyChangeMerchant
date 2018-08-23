package com.easychange.admin.easychangemerchant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.activity.CreateActivity;
import com.easychange.admin.easychangemerchant.activity.HistoryActivity;
import com.easychange.admin.easychangemerchant.activity.OnLineActivity;
import com.easychange.admin.easychangemerchant.activity.SubmitActivity;
import com.easychange.admin.easychangemerchant.activity.TanjifenActivity;
import com.easychange.admin.easychangemerchant.adapter.HomeGridAdapter;
import com.easychange.admin.easychangemerchant.base.BaseFragment;
import com.easychange.admin.easychangemerchant.bean.HomeGridBean;

import java.util.ArrayList;
import java.util.List;

/**
 * admin  2018/8/16 wan
 */
public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private TextView title;

    private GridView gridView;

    private HomeGridAdapter gridAdapter;

    private List<HomeGridBean> beans;

    private ImageView iv_tanjifen;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home, null);
        view.findViewById(R.id.view_header_back).setVisibility(View.GONE);
        title = view.findViewById(R.id.view_header_title);
        title.setText("首页");

        gridView = view.findViewById(R.id.frag_gridView);
        iv_tanjifen = view.findViewById(R.id.iv_tanjifen);
        iv_tanjifen.setOnClickListener(this);
        beans = new ArrayList<>();
        HomeGridBean onLineBean = new HomeGridBean();
        onLineBean.icon = R.drawable.home_on_line;
        onLineBean.title = "已上线活动";
        HomeGridBean submitBean = new HomeGridBean();
        submitBean.icon = R.drawable.home_submit;
        submitBean.title = "已提交活动";
        HomeGridBean historyBean = new HomeGridBean();
        historyBean.icon = R.drawable.home_history;
        historyBean.title = "历史活动";
        HomeGridBean createBean = new HomeGridBean();
        createBean.icon = R.drawable.home_create;
        createBean.title = "创建活动";
        beans.add(onLineBean);
        beans.add(submitBean);
        beans.add(historyBean);
        beans.add(createBean);

        gridAdapter = new HomeGridAdapter(beans, mContext);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(this);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(mContext, OnLineActivity.class);
                startActivity(intent);
                break;

            case 1:
                intent = new Intent(mContext, SubmitActivity.class);
                startActivity(intent);
                break;

            case 2:
                intent = new Intent(mContext, HistoryActivity.class);
                startActivity(intent);
                break;

            case 3:
                intent = new Intent(mContext, CreateActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_tanjifen:
                startActivity(new Intent(mContext, TanjifenActivity.class));
                break;
        }
    }
}
