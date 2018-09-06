package com.easychange.admin.easychangemerchant.p;

import android.app.Activity;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.http.BaseResponseBean;
import com.easychange.admin.easychangemerchant.http.DialogCallback;
import com.easychange.admin.easychangemerchant.http.HttpManager;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.easychange.admin.easychangemerchant.http.ResponseBean2;
import com.lzy.okgo.model.Response;

public class SaoMaPresenter {

    private Activity activity;
    private SaoMaCallBack callBack;

    public SaoMaPresenter(Activity activity, SaoMaCallBack saoMaCallBack) {
        this.activity = activity;
        this.callBack = saoMaCallBack;
    }

    public void getBiSaoData(int shopId, int residentId, int count) {
        new HttpManager<ResponseBean2>("merchantApp/verification", this)
                .addParams("shopId", shopId)
                .addParams("residentId", residentId)
                .addParams("type", 2)
                .addParams("count", count)
                .postRequest(new DialogCallback<ResponseBean2>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean2> response) {
                        if (response.body().data != null) {
//                            if (response.body().code.equals("200"))
//                                callBack.bisao(response.body().data);
                        }
                        Toast.makeText(activity, response.body().msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getQuanSaoData(int shopId, int code) {
        new HttpManager<ResponseBean2>("merchantApp/verification", this)
                .addParams("shopId", shopId)
                .addParams("code", code)
                .addParams("type", 1)
                .postRequest(new DialogCallback<ResponseBean2>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean2> response) {
                        if (response.body().data != null) {
//                            if (response.body().code.equals("200"))
//                                callBack.quansao(response.body().data);
                        }
                        Toast.makeText(activity, response.body().msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public interface SaoMaCallBack {
        void bisao(ResponseBean2 responseBean);

        void quansao(ResponseBean2 responseBean);
    }
}
