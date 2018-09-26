package com.easychange.admin.easychangemerchant.p;

import android.app.Activity;

import com.easychange.admin.easychangemerchant.http.DialogCallback;
import com.easychange.admin.easychangemerchant.http.HttpManager;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.lzy.okgo.model.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * admin  2018/8/20 wan
 */
public class ActionEditPresenter {
    private Activity activity;

    private ActionEditCallBack callBack;

    public ActionEditPresenter(Activity activity, ActionEditCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    public void setEditActionRequest(int id, String activityTitle, String beginTime, String endTime){
        try {
            activityTitle= URLEncoder.encode(activityTitle, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        new HttpManager<ResponseBean>("merchantApp/updateActivityDetail", this)
                .addParams("id", id)
                .addParams("activityTitle", activityTitle)
                .addParams("beginTime", beginTime)
                .addParams("endTime", endTime)
                .postRequest(new DialogCallback<ResponseBean>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean> response) {
                        callBack.editActionRequest();
                    }

                    @Override
                    public void onError(Response<ResponseBean> response) {
                        super.onError(response);
                        callBack.editActionRequestFail();
                    }
                });
    }

    public interface ActionEditCallBack{

        void editActionRequest();

        void editActionRequestFail();
    }
}
