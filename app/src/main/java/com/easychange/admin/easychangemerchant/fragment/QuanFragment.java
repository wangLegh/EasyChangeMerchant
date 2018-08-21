package com.easychange.admin.easychangemerchant.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseFragment;
import com.easychange.admin.easychangemerchant.http.BaseResponseBean;
import com.easychange.admin.easychangemerchant.p.SaoMaPresenter;
import com.easychange.admin.easychangemerchant.utils.CacheUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class QuanFragment extends BaseFragment implements View.OnClickListener, SaoMaPresenter.SaoMaCallBack {
    private TextView tv_sao;
    private static final int REQUEST_CODE_ZXING = 1001;
    private static final int CODE_FOR_CAMERA_PERMISSION = 1002;
    private String permission = Manifest.permission.CAMERA;
    private SaoMaPresenter presenter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_quanhexiao, null);
        tv_sao = view.findViewById(R.id.tv_sao);
        tv_sao.setOnClickListener(this);
        presenter = new SaoMaPresenter(mActivity,this);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sao:
                checkPermission(permission);
                break;
        }
    }

    private void checkPermission(String permission) {
        //使用兼容库就无需判断系统版本
        int hasWriteStoragePermission = ContextCompat.checkSelfPermission(mContext,
                permission);
        if (hasWriteStoragePermission == PackageManager.PERMISSION_GRANTED) {
            //拥有权限，执行操作
            Intent intent = new Intent(mContext, MyZxingActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ZXING);
        } else {
            //没有权限，向用户请求权限
            ActivityCompat.requestPermissions(mActivity, new String[]{permission}, CODE_FOR_CAMERA_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //通过requestCode来识别是否同一个请求
        if (requestCode == CODE_FOR_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意，执行操作
                Intent intent = new Intent(mContext, MyZxingActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ZXING);
                //这里的REQUEST_CODE是我们定义的int型常量,这里设置为5，为了方便接受onActivityResult分别进行处理
            } else {
                //用户不同意，向用户展示该权限作用
                if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.CAMERA)) {
                    Toast.makeText(mContext, "扫码需要此权限", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /*zxing扫描的回调*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ZXING) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //                    Toast.makeText(mContext, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    try {
                        int shopId = CacheUtils.get("id");
                        presenter.getQuanSaoData(shopId, Integer.parseInt(result));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void bisao(BaseResponseBean responseBean) {

    }

    @Override
    public void quansao(BaseResponseBean responseBean) {

    }
}
