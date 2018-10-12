package com.nie.excel.controller;

import com.alibaba.fastjson.JSONObject;
import com.nie.excel.entity.VIPRecharge;
import com.nie.excel.service.impl.ExcelServiceImpl;
import com.nie.excel.util.ExcelConversion;
import com.nie.excel.util.ExcelRead;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReadExcelController {
    @Resource
    private ExcelServiceImpl excelService;


    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/readExcel")
    @ResponseBody
    public List<VIPRecharge> readExcel(@RequestParam(value = "excelFile") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException {
        List<ArrayList<Object>> list = new ExcelRead().readExcel(file);
        if (list == null) {
            return null;
        }
        //不放到session里 换另外的方式
        //session.setAttribute("excelList",list);
        List<VIPRecharge> vipRechargeList = excelService.listTOVIPRecharge(list);

        System.out.println("判断返回的数据："+vipRechargeList.size());

        return vipRechargeList;
    }

    @ResponseBody
    @RequestMapping("/insertExcel")
    public int insertExcel(@RequestBody String data, HttpSession session){
        System.out.println("data数据:"+data);

        //此处有bug 需要前台或者这里判断 data
        List<VIPRecharge> vipRechargeList = new ArrayList<>();

//TODO 添加验证
        vipRechargeList = JSONObject.parseArray(data, VIPRecharge.class);
        return excelService.insVIPRechargeByEntity(vipRechargeList);

    }

    @RequestMapping("/read")
    public String read() {
        return "success";
    }
}
