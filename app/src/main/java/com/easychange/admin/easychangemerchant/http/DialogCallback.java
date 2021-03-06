package com.easychange.admin.easychangemerchant.http;

import android.app.Activity;

import com.easychange.admin.easychangemerchant.R;
import com.lzy.okgo.request.base.Request;

public abstract class DialogCallback<T> extends JsonCallback<T> {

    private LoadingDialog dialog;

    private void initDialog(Activity activity) {
        dialog = new LoadingDialog(activity, R.style.dialog);
    }

    public DialogCallback(Activity activity) {
        super();
        initDialog(activity);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
