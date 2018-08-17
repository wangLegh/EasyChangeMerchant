package com.easychange.admin.easychangemerchant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.adapter.FragPagerAdapter;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.fragment.AuditActionFragment;
import com.easychange.admin.easychangemerchant.fragment.PassActionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * admin  2018/8/16 wan
 */
public class SubmitActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private RelativeLayout rlAudit;
    private TextView rlAuditTv;
    private View rlAuditView;
    private RelativeLayout rlPass;
    private TextView rlPassTv;
    private View rlPassView;

    private ViewPager viewPager;
    private List<Fragment> fragments;
    private FragPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        TextView title = findViewById(R.id.view_header_title);
        title.setText("已提交活动");
        findViewById(R.id.view_header_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rlAudit = findViewById(R.id.act_submit_audit);
        rlAuditTv = findViewById(R.id.act_submit_audit_text);
        rlAuditView = findViewById(R.id.act_submit_audit_line);
        rlPass = findViewById(R.id.act_submit_pass);
        rlPassTv = findViewById(R.id.act_submit_pass_text);
        rlPassView = findViewById(R.id.act_submit_pass_line);

        viewPager = findViewById(R.id.act_submit_vp);

        fragments = new ArrayList<>();
        fragments.add(new AuditActionFragment());
        fragments.add(new PassActionFragment());

        pagerAdapter = new FragPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnPageChangeListener(this);

        rlAudit.setOnClickListener(this);
        rlPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_submit_audit:
                rlAuditTv.setTextColor(getResources().getColor(R.color.colorPrimary));
                rlAuditView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rlPassTv.setTextColor(getResources().getColor(R.color.text));
                rlPassView.setBackgroundColor(getResources().getColor(R.color.white));
                viewPager.setCurrentItem(0);
                break;

            case R.id.act_submit_pass:
                rlPassTv.setTextColor(getResources().getColor(R.color.colorPrimary));
                rlPassView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rlAuditTv.setTextColor(getResources().getColor(R.color.text));
                rlAuditView.setBackgroundColor(getResources().getColor(R.color.white));
                viewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                rlAuditTv.setTextColor(getResources().getColor(R.color.colorPrimary));
                rlAuditView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rlPassTv.setTextColor(getResources().getColor(R.color.text));
                rlPassView.setBackgroundColor(getResources().getColor(R.color.white));
                break;

            case 1:
                rlPassTv.setTextColor(getResources().getColor(R.color.colorPrimary));
                rlPassView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rlAuditTv.setTextColor(getResources().getColor(R.color.text));
                rlAuditView.setBackgroundColor(getResources().getColor(R.color.white));
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
