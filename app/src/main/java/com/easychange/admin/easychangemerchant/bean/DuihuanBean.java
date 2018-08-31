package com.easychange.admin.easychangemerchant.bean;

import java.util.List;

public class DuihuanBean {

        /**
         * list : [{"id":2,"name":"塑料瓶","sort":20,"currency":5,"image":"http://ech.oss-cn-beijing.aliyuncs.com/3c4a81288d8d48ceaa44c767337696dd","state":"0","unit":"公斤"},{"id":10,"name":"测试","sort":1,"currency":55,"image":"http://ech.oss-cn-beijing.aliyuncs.com/3c4a81288d8d48ceaa44c767337696dd","state":"0","unit":"个"},{"id":4,"name":"鞋类","sort":5,"currency":20,"image":"http://ech.oss-cn-beijing.aliyuncs.com/3c4a81288d8d48ceaa44c767337696dd","state":"0","unit":"个"},{"id":5,"name":"金属","sort":4,"currency":25,"image":"http://hwdc.oss-ap-northeast-1.aliyuncs.com/1533692699312Lighthouse.jpg","state":"0","unit":"件"},{"id":6,"name":"纸箱","sort":3,"currency":30,"image":"http://ech.oss-cn-beijing.aliyuncs.com/3c4a81288d8d48ceaa44c767337696dd","state":"0","unit":"件"},{"id":7,"name":"大家电","sort":2,"currency":35,"image":"http://ech.oss-cn-beijing.aliyuncs.com/3c4a81288d8d48ceaa44c767337696dd","state":"0","unit":"件"}]
         * carboniNtegral : 100
         */

        private int carboniNtegral;
        private List<ListBean> list;

        public int getCarboniNtegral() {
            return carboniNtegral;
        }

        public void setCarboniNtegral(int carboniNtegral) {
            this.carboniNtegral = carboniNtegral;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * name : 塑料瓶
             * sort : 20
             * currency : 5
             * image : http://ech.oss-cn-beijing.aliyuncs.com/3c4a81288d8d48ceaa44c767337696dd
             * state : 0
             * unit : 公斤
             */

            private int id;
            private String name;
            private int sort;
            private int currency;
            private String image;
            private String state;
            private String unit;

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

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }
}
