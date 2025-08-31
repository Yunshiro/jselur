package com.yunshiro.examples;

public class Account {
    private String id;
    private String name;
    private String accountType;
    private int credits;
    private double discount;


    public Account() {
    }

    public Account(String id, String name, String accountType, int credits, double discount) {
        this.id = id;
        this.name = name;
        this.accountType = accountType;
        this.credits = credits;
        this.discount = discount;
    }

    /**
     * 获取
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return accountType
     */
    public String getaccountType() {
        return accountType;
    }

    /**
     * 设置
     * @param accountType
     */
    public void setaccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * 获取
     * @return credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * 设置
     * @param credits
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * 获取
     * @return discount
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * 设置
     * @param discount
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String toString() {
        return "Account{id = " + id + ", name = " + name + ", accountType = " + accountType + ", credits = " + credits + ", discount = " + discount + "}";
    }
}
