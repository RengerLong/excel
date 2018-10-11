package com.nie.excel.service;

import com.nie.excel.entity.VIPRecharge;

import java.util.ArrayList;
import java.util.List;

public interface ExcelService {
    int insertVIPRecharge(List<ArrayList<Object>> vipRechargeList);
    List<VIPRecharge> selVIPRechargeList();
}
