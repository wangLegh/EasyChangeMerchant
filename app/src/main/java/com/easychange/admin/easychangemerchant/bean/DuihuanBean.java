package com.easychange.admin.easychangemerchant.bean;

public class DuihuanBean {

        /**
         * id : 2
         * name : 塑料瓶
         * sort : 200
         * currency : 5
         * image : http://ech.oss-cn-beijing.aliyuncs.com/3c4a81288d8d48ceaa44c767337696dd
         * state : 0
         */

        private int id;
        private String name;
        private int sort;
        private int currency;
        private String image;
        private String state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getCurrency() {
            return currency;
        }

        public void setCurrency(int currency) {
            this.currency = currency;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
}
