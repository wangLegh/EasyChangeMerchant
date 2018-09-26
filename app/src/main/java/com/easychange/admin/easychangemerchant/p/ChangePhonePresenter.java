package com.easychange.admin.easychangemerchant.p;

import android.app.Activity;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.EasyApplication;
import com.easychange.admin.easychangemerchant.bean.ChangePhoneBean;
import com.easychange.admin.easychangemerchant.http.DialogCallback;
import com.easychange.admin.easychangemerchant.http.HttpManager;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.easychange.admin.easychangemerchant.http.ResponseBean2;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/8/20 wan
 */
public class ChangePhonePresenter {
    private Activity activity;

    private ChangePhoneCallBack callBack;

    public ChangePhonePresenter(Activity activity, ChangePhoneCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    //更换手机号
    public void ChangePhoneRequest(String phone, String code) {
        new HttpManager<ResponseBean2<ChangePhoneBean>>("/merchantApp/updatePhone", this)
                .addParams("id", EasyApplication.getUserId())
                .addParams("phone", phone)
                .addParams("code", code)
                .postRequest(new DialogCallback<ResponseBean2<ChangePhoneBean>>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean2<ChangePhoneBean>> response) {
                        callBack.requestChangePhoneSuccess(response);
                        Toast.makeText(activity, response.body().msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Response<ResponseBean2<ChangePhoneBean>> response) {
                        super.onError(response);
                        callBack.requestFail(response.getException().getMessage());
                    }
                });

    }

    /*
    * phone	是	string	手机号
    id	是	string	商家id
    type	是	string	发送验证码类型（1 获取原手机号 2 获取新手机号）

    * 获取验证码
    * */
    public void GetCodeRequest(String phone, String type) {
        new HttpManager<ResponseBean>("/pc/myShop/nextAuthCode", this)
                .addParams("id", EasyApplication.getUserId())
                .addParams("phone", phone)
                .addParams("type", type)
                .getRequets(new DialogCallback<ResponseBean>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean> response) {
                        if (response.body().code == 200) {
                            callBack.requestGetCodeSuccess();
                        }
                        Toast.makeText(activity, response.body().msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Response<ResponseBean> response) {
                        super.onError(response);
                        callBack.requestFail(response.getException().getMessage());
                    }
                });

    }


    public void NextRequest(String code) {
        new HttpManager<ResponseBean>("/pc/myShop/next", this)
                .addParams("id", EasyApplication.getUserId())
                .addParams("code", code)
                .postRequest(new DialogCallback<ResponseBean>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean> response) {
                        callBack.requestNextSuccess(response);
                    }

                    @Override
                    public void onError(Response<ResponseBean> response) {
                        super.onError(response);
                        callBack.requestFail(response.getException().getMessage());
                    }
                });

    }

    public interface ChangePhoneCallBack {

        void requestChangePhoneSuccess(Response<ResponseBean2<ChangePhoneBean>> response);

        void requestFail(String message);

        void requestGetCodeSuccess();

        void requestNextSuccess(Response<ResponseBean> message);
    }
}
