package com.nie.excel.dao;

import com.nie.excel.entity.VIPRecharge;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ExcelDao {
    int insertVIPRecharge(List<VIPRecharge> vipRechargeList);
    List<VIPRecharge> selVIPRechargeList();
}
