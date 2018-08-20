package com.easychange.admin.easychangemerchant.p;

import android.app.Activity;

import com.easychange.admin.easychangemerchant.EasyApplication;
import com.easychange.admin.easychangemerchant.bean.ActionBean;
import com.easychange.admin.easychangemerchant.http.DialogCallback;
import com.easychange.admin.easychangemerchant.http.HttpManager;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * admin  2018/8/20 wan
 */
public class ActionPresenter {
    private Activity activity;

    private ActionCallBack callBack;

    public ActionPresenter(Activity activity, ActionCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    public void getActionList(int page, String status, String time){
        new HttpManager<ResponseBean<List<ActionBean>>>("merchantApp/selectActivityList", this)
                .addParams("pageNum", page)
                .addParams("status", status)
                .addParams("token", EasyApplication.getUserToken())
                .addParams("time", time)
                .getRequets(new DialogCallback<ResponseBean<List<ActionBean>>>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean<List<ActionBean>>> response) {
                        callBack.getActionList(response.body().data);
                    }

                    @Override
                    public void onError(Response<ResponseBean<List<ActionBean>>> response) {
                        super.onError(response);
                        callBack.getActionListFail();
                    }
                });
    }

    public interface ActionCallBack{

        void getActionList(List<ActionBean> data);

        void getActionListFail();
    }
}
