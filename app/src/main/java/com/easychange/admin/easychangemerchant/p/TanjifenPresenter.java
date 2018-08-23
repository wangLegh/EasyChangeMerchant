package com.easychange.admin.easychangemerchant.p;

import android.app.Activity;

import com.easychange.admin.easychangemerchant.EasyApplication;
import com.easychange.admin.easychangemerchant.bean.DuihuanBean;
import com.easychange.admin.easychangemerchant.bean.MingxiBean;
import com.easychange.admin.easychangemerchant.http.DialogCallback;
import com.easychange.admin.easychangemerchant.http.HttpManager;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * admin  2018/8/20 wan
 */
public class TanjifenPresenter {
    private Activity activity;

    private MingxiCallBack callBack;

    public TanjifenPresenter(Activity activity, MingxiCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    public void getMingxiList(int pageNum, int pageSize){
        new HttpManager<ResponseBean<List<MingxiBean>>>("/merchantApp/carboniList", this)
                .addParams("id", EasyApplication.getUserId())
                .addParams("pageNum", pageNum)
                .addParams("pageSize", pageSize)
                .getRequets(new DialogCallback<ResponseBean<List<MingxiBean>>>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean<List<MingxiBean>>> response) {
                        callBack.getMingxiList(response.body().data);
                    }

                    @Override
                    public void onError(Response<ResponseBean<List<MingxiBean>>> response) {
                        super.onError(response);
                        callBack.getMingxiListFail(response.getException().getMessage());
                    }
                });
    }
    public void getDuihuanList(){
            new HttpManager<ResponseBean<List<DuihuanBean>>>("/merchantApp/carboniWasteList", this)
                .addParams("id", EasyApplication.getUserId())
                .getRequets(new DialogCallback<ResponseBean<List<DuihuanBean>>>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean<List<DuihuanBean>>> response) {
                        callBack.getDuihuanList(response.body().data);
                    }

                    @Override
                    public void onError(Response<ResponseBean<List<DuihuanBean>>> response) {
                        super.onError(response);
                        callBack.getMingxiListFail(response.getException().getMessage());
                    }
                });
    }
    public interface MingxiCallBack{

        void getMingxiList(List<MingxiBean> data);

        void getDuihuanList(List<DuihuanBean> data);

        void getMingxiListFail(String msg);
    }
}
