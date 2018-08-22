package com.easychange.admin.easychangemerchant.bean;

import java.io.Serializable;

public class MessageBean implements Serializable{

    /**
     * id : 1
     * state : 0
     * title : 标题1
     * content : 内容1
     * createDate : 1533517062000
     */
    private int id;
    private String state;
    private String title;
    private String content;
    private long createDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getCreateDate() {
        return createDate;
    }
}
