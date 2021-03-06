package com.easychange.admin.easychangemerchant.http;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class HttpManager<T> {

    private static final String BASE_URL = "http://39.106.220.142/";

    private HttpParams params;

    private String url;

    private Object tag;

    private JSONObject jsonObject;
    private HashMap<String, Object> hashMap;

    public HttpManager(String url, Object tag) {
        this.url = url;
        this.tag = tag;
        params = new HttpParams();
        hashMap = new HashMap<>();
    }

    /**
     * get
     *
     * @param callback
     * @return
     */
    public HttpManager<T> getRequets(JsonCallback<T> callback) {
        Log.d("======>>", "http method get");
        OkGo.<T>get(BASE_URL + url)
                .tag(tag)
                .params(params)
                .execute(callback);
        return this;
    }

    /**
     * post
     *
     * @param callback
     * @return
     */
    public HttpManager<T> postRequest(JsonCallback<T> callback) {
        Log.d("======>>", "http method post");
        OkGo.<T>post(BASE_URL + url)
                .tag(tag)
                .params(params)
                .execute(callback);
        return this;
    }

    /**
     * 上传多个文件
     *
     * @param paramsKey
     * @param paramsValue
     * @param callback
     * @return
     */
    public HttpManager<T> postFileRequest(String paramsKey, List<File> paramsValue, JsonCallback<T> callback) {
        Log.d("======>>", "http method postFile");
        OkGo.<T>post(BASE_URL + url)
                .tag(tag)
                .isMultipart(true) //使用 multipart/form-data 表单上传
                .params(params)
                .addFileParams(paramsKey, paramsValue)
                .execute(callback);
        return this;
    }

    /**
     * 上传单个文件
     *
     * @param callback
     * @return
     */
    public HttpManager<T> postFileRequest(JsonCallback<T> callback) {
        Log.d("======>>", "http method post");
        OkGo.<T>post(BASE_URL + url)
                .tag(tag)
                .isMultipart(true) //使用 multipart/form-data 表单上传
                .params(params)
                .execute(callback);
        return this;
    }

    public HttpManager<T> postTestRequest(JsonCallback<T> callback) {
        Log.d("======>>", "http method post");
        OkGo.<T>post(BASE_URL + url)
                .tag(tag)
                .params(params)
                .execute(callback);
        return this;
    }

    public HttpManager<T> postRequestJson(JsonCallback<T> callback) {
        Log.d("======>>", "http method post");
        OkGo.<T>post(BASE_URL + url)
                .tag(tag)
                .upJson(jsonObject)
                .execute(callback);
        return this;
    }

    public HttpManager<T> addParams(String paramsKey, String paramsValue) {
        params.put(paramsKey, paramsValue);
        return this;
    }

    public HttpManager<T> addParams(String paramsKey, int paramsValue) {
        params.put(paramsKey, paramsValue);
        return this;
    }

    public HttpManager<T> addParams(String paramsKey, long paramsValue) {
        params.put(paramsKey, paramsValue);
        return this;
    }

    public HttpManager<T> addParams(String paramsKey, File paramsValue) {
        params.put(paramsKey, paramsValue);
        return this;
    }

    public HttpManager<T> addParams(String paramsKey, List<String> paramsValue) {
        params.putUrlParams(paramsKey, paramsValue);
        return this;
    }

    public HttpManager<T> addParamsJson(String paramsKey,Object value){
        hashMap.put(paramsKey,value);
        return this;
    }

    public HttpManager<T> createPostJsonObject() {
        jsonObject=new JSONObject(hashMap);
        return this;
    }
}
