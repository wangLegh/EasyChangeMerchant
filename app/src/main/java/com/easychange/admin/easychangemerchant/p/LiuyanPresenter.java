package com.easychange.admin.easychangemerchant.p;

import android.app.Activity;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.EasyApplication;
import com.easychange.admin.easychangemerchant.http.DialogCallback;
import com.easychange.admin.easychangemerchant.http.HttpManager;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/8/20 wan
 */
public class LiuyanPresenter {
    private Activity activity;

    private LiuyanCallBack callBack;

    public LiuyanPresenter(Activity activity, LiuyanCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    public void getLiuyanList( String content) {
        new HttpManager<ResponseBean>("/merchantApp/addFeedback", this)
                .addParamsJson("userId", EasyApplication.getUserId())
                .addParamsJson("title", "")
                .addParamsJson("pageSize", content)
                .createPostJsonObject()
                .postRequestJson(new DialogCallback<ResponseBean>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean> response) {
                        if (response.body().code==200){
                            callBack.getLiuyanList();
                            Toast.makeText(activity, response.body().msg, Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<ResponseBean> response) {
                        super.onError(response);
                        callBack.getLiuyanListFail(response.getException().getMessage());
                    }
                });

    }

    public interface LiuyanCallBack {

        void getLiuyanList();

        void getLiuyanListFail(String msg);
    }
}
