package com.nie.excel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/***
 * 会员充值类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VIPRecharge {
    private Integer cardNumber;         //卡号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date rechargeTime;         //充值时间
    private String rechargeType;       //充值类型 续充值||预充值
    private String rechargeTheWay;     //充值方式 现金||支付宝||微信||等
    private BigDecimal rechargeMoney;  //充值金额
    private BigDecimal rechargeBalabce;//充值后余额
}
