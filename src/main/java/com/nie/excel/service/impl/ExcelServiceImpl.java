package com.nie.excel.service.impl;

import com.nie.excel.dao.ExcelDao;
import com.nie.excel.entity.VIPRecharge;
import com.nie.excel.service.ExcelService;
import com.nie.excel.util.ExcelConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Resource
    private ExcelDao excelDao;

    @Override
    public int insertVIPRecharge(List<ArrayList<Object>> vipRechargeList) {
        List<VIPRecharge> rechargeList = ExcelConversion.getExcelResource(vipRechargeList);
        if (rechargeList == null) return 0;
        return excelDao.insertVIPRecharge(rechargeList);
    }

    @Override
    public List<VIPRecharge> selVIPRechargeList() {
        return excelDao.selVIPRechargeList();
    }
}
