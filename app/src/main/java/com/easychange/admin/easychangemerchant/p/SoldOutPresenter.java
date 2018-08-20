package com.easychange.admin.easychangemerchant.p;

import android.app.Activity;

import com.easychange.admin.easychangemerchant.EasyApplication;
import com.easychange.admin.easychangemerchant.http.DialogCallback;
import com.easychange.admin.easychangemerchant.http.HttpManager;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/8/20 wan
 */
public class SoldOutPresenter {
    private Activity activity;

    private SoldOutCallBack callBack;

    public SoldOutPresenter(Activity activity, SoldOutCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    public void setSoldOutRequest(String id){
        new HttpManager<ResponseBean>("manager/myShop/soldOutActivity", this)
                .addParams("userId", EasyApplication.getUserId())
                .addParams("id", id)
                .postRequest(new DialogCallback<ResponseBean>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean> response) {
                        callBack.requestSoldOutSuccess();
                    }

                    @Override
                    public void onError(Response<ResponseBean> response) {
                        super.onError(response);
                        callBack.requestSoldOutFail();
                    }
                });

    }

    public interface SoldOutCallBack {

        void requestSoldOutSuccess();

        void requestSoldOutFail();
    }
}
