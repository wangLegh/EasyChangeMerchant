package com.easychange.admin.easychangemerchant.bean;

public class MingxiBean {

        /**
         * id : 412
         * userId : 1
         * userType : 4
         * type : 2
         * content : 消费优惠券，发送碳积分！
         * currencyType : 2
         * amount : 10
         * createDate : 1534385653000
         * balance : 10
         * pageNum : null
         * pageSize : null
         * shopTitle : Nike专卖店
         * shopAddr : 中关村软件园23号楼
         * orderNo : null
         */

        private int id;
        private int userId;
        private String userType;
        private String type;
        private String content;
        private String currencyType;
        private int amount;
        private long createDate;
        private int balance;
        private Object pageNum;
        private Object pageSize;
        private String shopTitle;
        private String shopAddr;
        private Object orderNo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCurrencyType() {
            return currencyType;
        }

        public void setCurrencyType(String currencyType) {
            this.currencyType = currencyType;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
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

        public String getShopTitle() {
            return shopTitle;
        }

        public void setShopTitle(String shopTitle) {
            this.shopTitle = shopTitle;
        }

        public String getShopAddr() {
            return shopAddr;
        }

        public void setShopAddr(String shopAddr) {
            this.shopAddr = shopAddr;
        }

        public Object getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Object orderNo) {
            this.orderNo = orderNo;
        }
}
