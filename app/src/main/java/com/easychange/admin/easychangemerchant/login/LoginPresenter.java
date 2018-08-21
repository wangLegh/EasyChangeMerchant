package com.easychange.admin.easychangemerchant.login;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.bean.LoginBean;
import com.easychange.admin.easychangemerchant.http.DialogCallback;
import com.easychange.admin.easychangemerchant.http.HttpManager;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.lzy.okgo.model.Response;

/**
 * Created by Administrator on 2018/7/5.
 */

public class LoginPresenter {
    private Activity activity;

    private TokenCallBack callBack;

    public LoginPresenter(Activity activity, TokenCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 注册
     */
    public void getRegistInfo(String businessName, String legalPerson, String shopPhone, String code,
                              String category, String shopAddr, String telephone,
                              int turnover, int circulation, int deadline, String license) {
        new HttpManager<ResponseBean<ResponseBean>>("merchantApp/applyForEnter?businessName=" + businessName
                + "&legalPerson=" + legalPerson + "&shopPhone=" + shopPhone + "&code=" + code + "&category=" + category
                + "&shopAddr=" + shopAddr + "&telephone=" + telephone + "&turnover=" + turnover + "&circulation=" + circulation
                + "&deadline=" + deadline + "&license=" + license
                , this)
                .postRequest(new DialogCallback<ResponseBean<ResponseBean>>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean<ResponseBean>> response) {
                        if (response.body().data != null) {
                            if (response.body().code == 200)
                                callBack.registShop(response.body().data);
                        }
                        Toast.makeText(activity, response.body().msg, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Response<ResponseBean<ResponseBean>> response) {
                        super.onError(response);
                        Log.e("xxx", response.getException().getMessage());
                    }
                });
    }

    /**
     * 密码登录
     */
    public void getPassLoginInfo(String phone, String password) {
        new HttpManager<ResponseBean<LoginBean>>("merchantApp/login", this)
                .addParams("phone", phone)
                .addParams("password", password)
                .postRequest(new DialogCallback<ResponseBean<LoginBean>>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean<LoginBean>> response) {
                        if (response.body().data != null) {
                            if (response.body().code == 200)
                                callBack.getPassLoginInfo(response.body().data);
                        } else {
                            Toast.makeText(activity, response.body().msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    /**
     * 重置密码
     */
    public void forgetPassrInfo(String phone, String code, String password) {
        new HttpManager<ResponseBean>("merchantApp/retrieve/sendCode", this)
                .addParams("phone", phone)
                .addParams("code", code)
                .addParams("password", password)
                .postRequest(new DialogCallback<ResponseBean>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean> response) {
                        Toast.makeText(activity, response.body().msg, Toast.LENGTH_SHORT).show();
                        if (response.body().code == 200)
                            callBack.getForgetPassInfo(response.body());
                    }
                });
    }

    /**
     * 忘记密码验证码
     */
    public void getPswCode(String phone) {
        new HttpManager<ResponseBean>("merchantApp/retrieve/sendCode", this)
                .addParams("phone", phone)
                .getRequets(new DialogCallback<ResponseBean>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean> response) {
                        Toast.makeText(activity, response.body().msg, Toast.LENGTH_SHORT).show();
                        if (response.body().code == 200)
                            callBack.getForgetPswCode(response.body());
                    }
                });
    }

    /**
     * 注册时候验证码
     */
    public void getRegistCode(String phone) {
        new HttpManager<ResponseBean>("merchantApp/register/sendCode", this)
                .addParams("phone", phone)
                .getRequets(new DialogCallback<ResponseBean>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean> response) {
                        Toast.makeText(activity, response.body().msg, Toast.LENGTH_SHORT).show();
                        if (response.body().code == 200)
                            callBack.getRegistCode(response.body());
                    }
                });
    }

    public interface TokenCallBack {
        void getPassLoginInfo(LoginBean loginBean);

        void getRegistCode(ResponseBean responseBean);

        void registShop(ResponseBean responseBean);

        void getForgetPswCode(ResponseBean responseBean);

        void getForgetPassInfo(ResponseBean responseBean);
    }
}
