package com.easychange.admin.easychangemerchant.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * admin  2018/8/20 wan
 */
public class ActionBean implements Parcelable{

    /**
     * id : 2
     * price : 199
     * image : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2280195286,4209940003&fm=27&gp=0.jpg
     * full : 199
     * sub : 150
     * count : 100
     * beginTime : 1531971454000
     * endTime : 1532662657000
     * shopId : 1
     * onsale : 1
     * activityTitle : 双11
     * timeLimit : 10:00-22:00
     * status : 3
     * coupon : null
     * content : 1
     * createTime : 1534301502000
     * activityTime : 1
     * currencyCount : null
     * orderQuantity : 0
     * gainCurrency : 0
     */

    private int id;
    private int price;
    private String image;
    private int full;
    private int sub;
    private int count;
    private long beginTime;
    private long endTime;
    private int shopId;
    private String onsale;
    private String activityTitle;
    private String timeLimit;
    private String status;
    private Object coupon;
    private String content;
    private long createTime;
    private String activityTime;
    private Object currencyCount;
    private int orderQuantity;
    private int gainCurrency;

    protected ActionBean(Parcel in) {
        id = in.readInt();
        price = in.readInt();
        image = in.readString();
        full = in.readInt();
        sub = in.readInt();
        count = in.readInt();
        beginTime = in.readLong();
        endTime = in.readLong();
        shopId = in.readInt();
        onsale = in.readString();
        activityTitle = in.readString();
        timeLimit = in.readString();
        status = in.readString();
        content = in.readString();
        createTime = in.readLong();
        activityTime = in.readString();
        orderQuantity = in.readInt();
        gainCurrency = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(price);
        dest.writeString(image);
        dest.writeInt(full);
        dest.writeInt(sub);
        dest.writeInt(count);
        dest.writeLong(beginTime);
        dest.writeLong(endTime);
        dest.writeInt(shopId);
        dest.writeString(onsale);
        dest.writeString(activityTitle);
        dest.writeString(timeLimit);
        dest.writeString(status);
        dest.writeString(content);
        dest.writeLong(createTime);
        dest.writeString(activityTime);
        dest.writeInt(orderQuantity);
        dest.writeInt(gainCurrency);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ActionBean> CREATOR = new Creator<ActionBean>() {
        @Override
        public ActionBean createFromParcel(Parcel in) {
            return new ActionBean(in);
        }

        @Override
        public ActionBean[] newArray(int size) {
            return new ActionBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFull() {
        return full;
    }

    public void setFull(int full) {
        this.full = full;
    }

    public int getSub() {
        return sub;
    }

    public void setSub(int sub) {
        this.sub = sub;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getOnsale() {
        return onsale;
    }

    public void setOnsale(String onsale) {
        this.onsale = onsale;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getCoupon() {
        return coupon;
    }

    public void setCoupon(Object coupon) {
        this.coupon = coupon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public Object getCurrencyCount() {
        return currencyCount;
    }

    public void setCurrencyCount(Object currencyCount) {
        this.currencyCount = currencyCount;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public int getGainCurrency() {
        return gainCurrency;
    }

    public void setGainCurrency(int gainCurrency) {
        this.gainCurrency = gainCurrency;
    }
}
