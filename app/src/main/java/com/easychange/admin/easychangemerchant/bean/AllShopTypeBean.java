package com.easychange.admin.easychangemerchant.bean;

import java.io.Serializable;

public class AllShopTypeBean implements Serializable{


    /**
     * msg : 查询成功
     * code : 200
     * data : [{"createTime":1531814311000,"id":1,"title":"衣服"},{"createTime":1531814318000,"id":2,"title":"水果"},{"createTime":1531814326000,"id":3,"title":"百货"},{"createTime":1531814331000,"id":4,"title":"男装"},{"createTime":1531814342000,"id":5,"title":"家居"},{"createTime":1533710371000,"id":7,"title":"a"},{"createTime":1534146349000,"id":8,"title":"钟鑫"}]
     */
    private long createTime;
    private int id;
    private String title;

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateTime() {
        return createTime;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
