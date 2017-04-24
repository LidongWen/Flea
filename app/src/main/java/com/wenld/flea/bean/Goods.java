package com.wenld.flea.bean;

import java.io.Serializable;

/**
 * 物品实体类
 */
public class Goods implements Serializable {

    /**
     * id : 4
     * uid : 4
     * title : 一缕阳光
     * img :
     * price : 19
     * describe : 在漆黑的世界，一缕阳光是多么重要
     * classify : 0
     * contact : 手机18234567411
     * addtime : 0000-00-00 00:00:00
     * status : 1
     */

    private String id;
    private String uid;
    private String title;
    private String img;
    private String price;
    private String describe;
    private String classify;
    private String contact;
    private String addtime;
    private String status;
    /**
     * sc : 0
     * uname : admin
     */

    private int sc;
    private String uname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSc() {
        return sc;
    }

    public void setSc(int sc) {
        this.sc = sc;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
