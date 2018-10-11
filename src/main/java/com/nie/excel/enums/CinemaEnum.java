package com.nie.excel.enums;

/**
 * Created by prue on 2018/10/7.
 */
public enum CinemaEnum {
    CINEMA_ZONGYING(1,"影院:湖北省孝感市中影元素国际影城"),
    CINEMA_HUAXIA(2,"影院:孝感华夏影城"),
    CINEMA_ERROR(0,"数据与系统不匹配");

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    CinemaEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
