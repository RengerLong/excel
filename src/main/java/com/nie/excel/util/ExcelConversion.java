package com.nie.excel.util;

import com.nie.excel.entity.VIPRecharge;
import com.nie.excel.enums.CinemaEnum;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * excel 转换类
 * 把ArrayList 转换为 对象
 */
@Slf4j
public class ExcelConversion {
    private static String excelSystem;
    //private static String excelTable;
    /***
     * 判断系统 进入该系统对应的方法
     * @param excelList
     */
    public static List<VIPRecharge> getExcelResource(List<ArrayList<Object>> excelList) {
        excelSystem = (String) excelList.get(0).get(0);
        System.out.println(CinemaEnum.CINEMA_HUAXIA.getMsg());
        if (CinemaEnum.CINEMA_ZONGYING.getMsg().equals(excelSystem)) {
            //湖北省孝感市中影元素国际影城
            return ZY_Conversion(excelList);
        } else if(CinemaEnum.CINEMA_HUAXIA.getMsg().equals(excelSystem)){
            //孝感华夏影城
            return HX_Conversion(excelList);
        }
        return null;
    }


    /***
     * 中影元素影城   会员充值卡记录转换
     * @param excelList  Excel中读取到的数据
     * @return 返回对象集合
     */
    private static List<VIPRecharge> ZY_Conversion(List<ArrayList<Object>> excelList) {
        List<VIPRecharge> vipRechargeList = new ArrayList<>();
        VIPRecharge vipRecharge = null;
        for (int i = 3; i < excelList.size(); i++) {
            vipRecharge = new VIPRecharge(
                    new Integer(excelList.get(i).get(2).toString()),
                    new Date(),
                    null,
                    (String) excelList.get(i).get(3),
                    new BigDecimal(new Double(excelList.get(i).get(4).toString())),
                    null);


            log.info("cardNumber:" + vipRecharge.getCardNumber()
                    + "--Balabce:" + vipRecharge.getRechargeBalabce()
                    + "--Money:" + vipRecharge.getRechargeMoney()
                    + "--TheWay:" + vipRecharge.getRechargeTheWay()
                    + "--Type: " + vipRecharge.getRechargeType());
            log.info("* * * * * * * * * * * * * * * * * * * * *");

            vipRechargeList.add(vipRecharge);
        }
        return vipRechargeList;
    }

    /***
     * 孝感华夏影城   会员充值卡记录转换
     * @param excelList  Excel中读取到的数据
     * @return 返回对象集合
     */
    private static List<VIPRecharge> HX_Conversion(List<ArrayList<Object>> excelList) {
        List<VIPRecharge> vipRechargeList = new ArrayList<>();
        VIPRecharge vipRecharge = null;
        for (int i = 3; i < excelList.size(); i++) {

      vipRecharge = new VIPRecharge(
                    new Integer(excelList.get(i).get(0).toString()),
                    null,
                    (String) excelList.get(i).get(1),
                    (String) excelList.get(i).get(3),
                    new BigDecimal(new Double(excelList.get(i).get(2).toString())),
                    new BigDecimal(new Double(excelList.get(i).get(4).toString())));
            vipRechargeList.add(vipRecharge);

            log.info("cardNumber:" + vipRecharge.getCardNumber()
                    + "--Balabce:" + vipRecharge.getRechargeBalabce()
                    + "--Money:" + vipRecharge.getRechargeMoney()
                    + "--TheWay:" + vipRecharge.getRechargeTheWay()
                    + "--Type: " + vipRecharge.getRechargeType());
            log.info("* * * * * * * * * * * * * * * * * * * * *");
        }
        return vipRechargeList;
    }

}
