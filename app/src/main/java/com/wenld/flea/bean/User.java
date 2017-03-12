package com.wenld.flea.bean;

import java.util.ArrayList;

/**
 * 用户实体类
 */
public class User {
    private Long id;

    private String user_id;//用户id

    private String icon;    //头像

    private String name;    //名字

    private String account;    //账户

    private String contact;    //联系方式


    private ArrayList<Goods> listSale;            //用户出售的物品



    public User() {
    }

    public User(String icon, String name, String account) {
        this.icon = icon;
        this.name = name;
        this.account = account;
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
    public User(Long id, String user_id, String icon, String name, String account, String contact,
           String describe) {
        this.id = id;
        this.user_id = user_id;
        this.icon = icon;
        this.name = name;
        this.account = account;
        this.contact = contact;
    }

    public User(Long id, String user_id, String icon, String name, String account, String contact) {
        this.id = id;
        this.user_id = user_id;
        this.icon = icon;
        this.name = name;
        this.account = account;
        this.contact = contact;
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
                ", listSale=" + listSale +
                '}';
    }
}
