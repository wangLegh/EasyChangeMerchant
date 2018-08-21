package com.easychange.admin.easychangemerchant.p;

import android.app.Activity;

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
    public void YihuanbiRequest(String id,int pageNum,int pageSize){
        new HttpManager<ResponseBean< YihuanbiBean>>("/merchantApp/applicationRecord", this)
                .addParams("id", id)
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
                        callBack.requestFail(response.message());
                    }
                });

    }
    //易换币申请
    public void ApplyRequest(String shopId,int count,int year){
        new HttpManager<ResponseBean>("/merchantApp/applyMoney", this)
                .addParams("id", shopId)
                .addParams("pageNum", count)
                .addParams("pageSize", year)
                .getRequets(new DialogCallback<ResponseBean>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean> response) {
                        callBack.requestApplySuccess();
                    }

                    @Override
                    public void onError(Response<ResponseBean> response) {
                        super.onError(response);
                        callBack.requestFail(response.message());
                    }
                });

    }

    public interface YihuanbiCallBack {

        void requestYihuanbiSuccess(YihuanbiBean  datas);

        void requestFail(String message);

        void requestApplySuccess();
    }
}
