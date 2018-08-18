package com.easychange.admin.easychangemerchant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;

import com.easychange.admin.easychangemerchant.adapter.FragPagerAdapter;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.fragment.HomeFragment;
import com.easychange.admin.easychangemerchant.fragment.MineFragment;
import com.easychange.admin.easychangemerchant.fragment.PayFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager viewPager;

    private RadioButton rbAction;

    private RadioButton rbPay;

    private RadioButton rbMine;

    private List<Fragment> fragments;

    private FragPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.act_main_vp);
        rbAction = (RadioButton) findViewById(R.id.act_main_action);
        rbPay = (RadioButton) findViewById(R.id.act_main_pay);
        rbMine = (RadioButton) findViewById(R.id.act_main_mine);

        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new PayFragment());
        fragments.add(new MineFragment());

        pagerAdapter = new FragPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(this);

        rbAction.setOnClickListener(this);
        rbPay.setOnClickListener(this);
        rbMine.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                rbAction.setChecked(true);
                break;

            case 1:
                rbPay.setChecked(true);
                break;

            case 2:
                rbMine.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_main_action:
                viewPager.setCurrentItem(0);
                break;

            case R.id.act_main_pay:
                viewPager.setCurrentItem(1);
                break;

            case R.id.act_main_mine:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
