package com.easychange.admin.easychangemerchant.http;

import java.io.Serializable;

public class ResponseBean2<T> implements Serializable {

    public String code;
    public String msg;
    public T data;
}