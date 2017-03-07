package com.wenld.flea.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;

/**
 * 用户实体类
 */
@Entity
public class User {
    @Id
    private Long id;

    private String user_id;//用户id

    private String icon;    //头像

    private String name;    //名字

    private String account;    //账户

    private String contact;    //联系方式

    private int flag;        //1：qq		0:微博

    @Transient
    private ArrayList<Goods> listSale;            //用户出售的物品

    private String describe;        //摊位描述  mark：服务器暂时没添加


    public User() {
    }

    public User(String icon, String name, String account, int flag) {
        this.icon = icon;
        this.name = name;
        this.account = account;
        this.flag = flag;
    }

    /**
     * @param icon
     * @param name
     * @param account
     * @param contact
     * @param list
     */
    public User(String icon, String name, String account, String contact, ArrayList<Goods> list) {
        super();
        this.icon = icon;
        this.name = name;
        this.account = account;
        this.contact = contact;
        this.listSale = list;
    }

    @Generated(hash = 1073190313)
    public User(Long id, String user_id, String icon, String name, String account, String contact,
            int flag, String describe) {
        this.id = id;
        this.user_id = user_id;
        this.icon = icon;
        this.name = name;
        this.account = account;
        this.contact = contact;
        this.flag = flag;
        this.describe = describe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }


    public ArrayList<Goods> getListSale() {
        return listSale;
    }

    public void setListSale(ArrayList<Goods> listSale) {
        this.listSale = listSale;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "User{" +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", contact='" + contact + '\'' +
                ", flag=" + flag +
                ", listSale=" + listSale +
                '}';
    }
}
