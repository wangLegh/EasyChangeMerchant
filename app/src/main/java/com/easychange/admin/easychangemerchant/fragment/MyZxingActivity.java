package com.easychange.admin.easychangemerchant.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyZxingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.fl_my_container)
    FrameLayout mFlMyContainer;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.activity_second)
    FrameLayout mActivitySecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_zxing);
        ButterKnife.bind(this);
        //在Activity中执行Fragment的初始化操作
        //执行扫码Fragment的初始化操作
        CaptureFragment captureFragment = new CaptureFragment();
        //为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.fragment_myzxing);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_my_container, captureFragment);
        transaction.commit();
        mIvBack.setOnClickListener(this);
    }

    //二维码解析回调函数
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            MyZxingActivity.this.setResult(RESULT_OK, resultIntent);
            MyZxingActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            MyZxingActivity.this.setResult(RESULT_OK, resultIntent);
            MyZxingActivity.this.finish();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
