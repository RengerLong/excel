package com.nie.excel.controller;

import com.nie.excel.entity.VIPRecharge;
import com.nie.excel.service.impl.ExcelServiceImpl;
import com.nie.excel.util.ExcelConversion;
import com.nie.excel.util.ExcelRead;
import org.springframework.stereotype.Controller;
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
    public List<ArrayList<Object>> readExcel(@RequestParam(value = "excelFile") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException {
        List<ArrayList<Object>> list = new ExcelRead().readExcel(file);
        //可以在这里转换成对象集合在返回

        if (list == null) {
            return null;
        }
        session.setAttribute("excelList",list);
        return list;
    }

    @ResponseBody
    @RequestMapping("/insertExcel")
    public String insertExcel( HttpSession session){
        List<ArrayList<Object>> excelList = (List<ArrayList<Object>>) session.getAttribute("excelList");
        int isok = excelService.insertVIPRecharge(excelList);
        if (isok == 0) return "error";
        else return "success";
    }

    @RequestMapping("/read")
    public String read() {
        return "success";
    }
}
