package com.easychange.admin.easychangemerchant.bean;

import java.util.List;

/**
 * Created by lxk on 2018/8/14.
 */

public class AboutUsBean {

    /**
     * msg : 查询成功
     * code : 200
     * data : [{"id":1,"state":1,"content":"18515885055"},{"id":2,"state":2,"content":"wx6165163"},{"id":3,"state":3,"content":"a1958@163.com"},{"id":4,"state":4,"content":"www.5c6c7c.com"},{"id":5,"state":5,"content":"6472000"},{"id":6,"state":6,"content":"<p>撒旦撒旦撒多2222<\/p>"}]
     */
    private String msg;
    private String code;
    private List<DataEntity> data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * id : 1
         * state : 1
         * content : 18515885055
         */
        private int id;
        private int state;
        private String content;

        public void setId(int id) {
            this.id = id;
        }

        public void setState(int state) {
            this.state = state;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public int getState() {
            return state;
        }

        public String getContent() {
            return content;
        }
    }
}
