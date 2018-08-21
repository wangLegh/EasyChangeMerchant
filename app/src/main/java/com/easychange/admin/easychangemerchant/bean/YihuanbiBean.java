package com.easychange.admin.easychangemerchant.bean;

import java.util.List;

public class YihuanbiBean {



        private int residueCurrency;
        private int circulation;
        private List<ListBean> list;

        public int getResidueCurrency() {
            return residueCurrency;
        }

        public void setResidueCurrency(int residueCurrency) {
            this.residueCurrency = residueCurrency;
        }

        public int getCirculation() {
            return circulation;
        }

        public void setCirculation(int circulation) {
            this.circulation = circulation;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 18
             * beginCount : 20
             * endCount : null
             * createTime : 1534413161000
             * dealTime : 1565949161000
             * shopId : 1
             * state : 4
             * type : 2
             * passTime : 1565949161000
             * countYear : null
             * token : null
             * pageNum : null
             * pageSize : null
             */

            private int id;
            private int beginCount;
            private Object endCount;
            private long createTime;
            private long dealTime;
            private int shopId;
            private String state;
            private String type;
            private long passTime;
            private Object countYear;
            private Object token;
            private Object pageNum;
            private Object pageSize;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getBeginCount() {
                return beginCount;
            }

            public void setBeginCount(int beginCount) {
                this.beginCount = beginCount;
            }

            public Object getEndCount() {
                return endCount;
            }

            public void setEndCount(Object endCount) {
                this.endCount = endCount;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getDealTime() {
                return dealTime;
            }

            public void setDealTime(long dealTime) {
                this.dealTime = dealTime;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public long getPassTime() {
                return passTime;
            }

            public void setPassTime(long passTime) {
                this.passTime = passTime;
            }

            public Object getCountYear() {
                return countYear;
            }

            public void setCountYear(Object countYear) {
                this.countYear = countYear;
            }

            public Object getToken() {
                return token;
            }

            public void setToken(Object token) {
                this.token = token;
            }

            public Object getPageNum() {
                return pageNum;
            }

            public void setPageNum(Object pageNum) {
                this.pageNum = pageNum;
            }

            public Object getPageSize() {
                return pageSize;
            }

            public void setPageSize(Object pageSize) {
                this.pageSize = pageSize;
            }
        }
}
