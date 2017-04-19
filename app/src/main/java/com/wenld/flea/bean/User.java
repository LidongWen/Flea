package com.wenld.flea.bean;

/**
 * 用户实体类
 */
public class User {

    private Long id;

    private String name;    //名字

    private String account;    //账户

    /**
     * id : 3
     * image :
     * phone : 1101111111
     */

    private String image;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
