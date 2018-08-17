package com.easychange.admin.easychangemerchant.http;

import java.io.Serializable;

public class ResponseBean<T> implements Serializable {

    public int code;
    public String msg;
    public T data;
}