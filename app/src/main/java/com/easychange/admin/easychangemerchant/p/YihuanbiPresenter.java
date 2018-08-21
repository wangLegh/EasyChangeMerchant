package com.easychange.admin.easychangemerchant.p;

import android.app.Activity;

import com.easychange.admin.easychangemerchant.EasyApplication;
import com.easychange.admin.easychangemerchant.bean.YihuanbiBean;
import com.easychange.admin.easychangemerchant.http.DialogCallback;
import com.easychange.admin.easychangemerchant.http.HttpManager;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/8/20 wan
 */
public class YihuanbiPresenter {
    private Activity activity;

    private YihuanbiCallBack callBack;

    public YihuanbiPresenter(Activity activity, YihuanbiCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }
    //易换币列表记录
    public void YihuanbiRequest(int pageNum,int pageSize){
        new HttpManager<ResponseBean< YihuanbiBean>>("/merchantApp/applicationRecord", this)
                .addParams("id", EasyApplication.getUserId())
                .addParams("pageNum", pageNum)
                .addParams("pageSize", pageSize)
                .getRequets(new DialogCallback<ResponseBean<YihuanbiBean>>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean<YihuanbiBean>> response) {
                        callBack.requestYihuanbiSuccess(response.body().data);
                    }

                    @Override
                    public void onError(Response<ResponseBean<YihuanbiBean>> response) {
                        super.onError(response);
                        callBack.requestFail(response.getException().getMessage());
                    }
                });

    }
    //易换币申请
    public void ApplyRequest(String count,String year){
        new HttpManager<ResponseBean>("/merchantApp/applyMoney", this)
                .addParams("shopId", EasyApplication.getUserId())
                .addParams("count", count)
                .addParams("year", year)
                .postRequest(new DialogCallback<ResponseBean>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean> response) {
                        callBack.requestApplySuccess();
                    }

                    @Override
                    public void onError(Response<ResponseBean> response) {
                        super.onError(response);
                        callBack.requestFail(response.getException().getMessage());
                    }
                });

    }

    public interface YihuanbiCallBack {

        void requestYihuanbiSuccess(YihuanbiBean  datas);

        void requestFail(String message);

        void requestApplySuccess();
    }
}
