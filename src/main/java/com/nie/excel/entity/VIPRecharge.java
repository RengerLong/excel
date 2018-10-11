package com.nie.excel.entity;

import java.math.BigDecimal;
import java.util.Date;

/***
 * 会员充值类
 */
public class VIPRecharge {
    private Integer cardNumber;         //卡号
    private Date rechargeTime;      //充值时间
    private String rechargeType;    //充值类型 续充值||预充值
    private String rechargeTheWay;  //充值方式 现金||支付宝||微信||等
    private BigDecimal rechargeMoney;//充值金额
    private BigDecimal rechargeBalabce;//充值后余额

    public VIPRecharge() {
    }

    public VIPRecharge(Integer cardNumber, Date rechargeTime, String rechargeType, String rechargeTheWay, BigDecimal rechargeMoney, BigDecimal rechargeBalabce) {
        this.cardNumber = cardNumber;
        this.rechargeTime = rechargeTime;
        this.rechargeType = rechargeType;
        this.rechargeTheWay = rechargeTheWay;
        this.rechargeMoney = rechargeMoney;
        this.rechargeBalabce = rechargeBalabce;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    public String getRechargeTheWay() {
        return rechargeTheWay;
    }

    public void setRechargeTheWay(String rechargeTheWay) {
        this.rechargeTheWay = rechargeTheWay;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public BigDecimal getRechargeBalabce() {
        return rechargeBalabce;
    }

    public void setRechargeBalabce(BigDecimal rechargeBalabce) {
        this.rechargeBalabce = rechargeBalabce;
    }
}
