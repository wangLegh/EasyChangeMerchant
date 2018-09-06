package com.easychange.admin.easychangemerchant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.activity.AboutUsActivity;
import com.easychange.admin.easychangemerchant.activity.ChangePhoneActivitty;
import com.easychange.admin.easychangemerchant.activity.MessageActivitty;
import com.easychange.admin.easychangemerchant.activity.MyCodeActivity;
import com.easychange.admin.easychangemerchant.activity.TanjifenActivity;
import com.easychange.admin.easychangemerchant.activity.YihuanbiActivity;
import com.easychange.admin.easychangemerchant.base.BaseFragment;
import com.easychange.admin.easychangemerchant.utils.CacheUtils;
import com.easychange.admin.easychangemerchant.views.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * admin  2018/8/16 wan
 */
public class MineFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.img_mine_header)
    CircleImageView imgMineHeader;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.tv_mine_address)
    TextView tvMineAddress;
    @BindView(R.id.layout_mine_address)
    LinearLayout layoutMineAddress;
    @BindView(R.id.layout_mine_phone)
    LinearLayout layoutMinePhone;
    @BindView(R.id.layout_mine_message)
    LinearLayout layoutMineMessage;
    @BindView(R.id.layout_mine_tanjifen)
    LinearLayout layoutMineTanjifen;
    @BindView(R.id.layout_mine_yihuanbi)
    LinearLayout layoutMineYihuanbi;
    @BindView(R.id.layout_mine_erweima)
    LinearLayout layoutMineErweima;
    @BindView(R.id.layout_mine_about_us)
    LinearLayout layoutMineAboutUs;
    @BindView(R.id.tv_mine_phone)
    TextView tvMinePhone;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_mine, null);
        unbinder = ButterKnife.bind(this, view);
        setContent();
        return view;
    }

    private void setContent() {
        if (CacheUtils.get("head")!=null){
            String head = CacheUtils.get("head");
            Glide.with(mContext).load(head).into(imgMineHeader);
        }
        if (CacheUtils.get("name")!=null){
            String name = CacheUtils.get("name");
            tvMineName.setText(name);
        }
        if (CacheUtils.get("address")!=null){
            String address = CacheUtils.get("address");
            tvMineAddress.setText(address);
        }
        if (CacheUtils.get("phone")!=null){
            String phone = CacheUtils.get("phone");
            tvMinePhone.setText(phone);
        }

    }


    @OnClick({R.id.img_mine_header, R.id.layout_mine_address, R.id.layout_mine_phone, R.id.layout_mine_message, R.id.layout_mine_tanjifen, R.id.layout_mine_yihuanbi, R.id.layout_mine_erweima, R.id.layout_mine_about_us})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_mine_header://头像
                break;
            case R.id.layout_mine_address://商家地址
                break;
            case R.id.layout_mine_phone://更换手机
                startActivity(new Intent(mContext, ChangePhoneActivitty.class));
                break;
            case R.id.layout_mine_message://我的消息
                startActivity(new Intent(mContext, MessageActivitty.class));
                break;
            case R.id.layout_mine_tanjifen://碳积分
                startActivity(new Intent(mContext, TanjifenActivity.class));
                break;
            case R.id.layout_mine_yihuanbi://易换券
                startActivity(new Intent(mContext, YihuanbiActivity.class));
                break;
            case R.id.layout_mine_erweima://商家二维码
                startActivity(new Intent(mContext, MyCodeActivity.class));
                break;
            case R.id.layout_mine_about_us://关于我们
                startActivity(new Intent(mContext, AboutUsActivity.class));
                break;
        }
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}