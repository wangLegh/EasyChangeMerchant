package com.easychange.admin.easychangemerchant.p;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.http.DialogCallback;
import com.easychange.admin.easychangemerchant.http.HttpManager;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.lzy.okgo.model.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * admin  2018/8/20 wan
 */
public class CreatePresenter {
    private Activity activity;

    private CreateCallBack callBack;

    public CreatePresenter(Activity activity, CreateCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * activityTitle 	是 	string 	活动名称
     * price 	是 	int 	易换币价值数量
     * shopId 	是 	int 	门店id(商家id)
     * image 	是 	string 	卡券的图片地址
     * full 	是 	int 	满多少
     * sub 	是 	int 	减多少
     * count 	是 	int 	卡券发放数量
     * beginTime 	是 	Date 	活动开始时间
     * endTime 	是 	Date 	活动截止时间
     * timeLimit 	是 	string 	每日限时
     * activityTime 	是 	string 	活动日期（0工作日 1休息日 2不限）
     */
    public void setCreateAction(String activityTitle, String price, String shopId, String full,
                                String sub, String count, String beginTime, String endTime,
                                String timeLimit, String activityTime) {
        try {
            activityTitle= URLEncoder.encode(activityTitle, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        new HttpManager<ResponseBean<Void>>("merchantApp/createActivity", this)
                .addParams("activityTitle", activityTitle)
                .addParams("price", price)
                .addParams("shopId", shopId)
                .addParams("image", "")
                .addParams("full", full)
                .addParams("sub", sub)
                .addParams("count", count)
                .addParams("beginTime", beginTime)
                .addParams("endTime", endTime)
                .addParams("timeLimit", timeLimit)
                .addParams("activityTime", activityTime)
                .postRequest(new DialogCallback<ResponseBean<Void>>(activity) {
                    @Override
                    public void onSuccess(Response<ResponseBean<Void>> response) {
                        if (response.body().code==200){
                            callBack.requestCreateActionSuccess();
                            Toast.makeText(activity, response.body().msg, Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(activity, response.body().msg, Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Response<ResponseBean<Void>> response) {
                        super.onError(response);
                        Log.d("CreatePresenter", response.getException().getMessage()+"---------");
                    }
                });
    }

    public interface CreateCallBack {

        void requestCreateActionFail(int code);

        void requestCreateActionSuccess();
    }
}
