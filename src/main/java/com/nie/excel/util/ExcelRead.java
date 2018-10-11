package com.nie.excel.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取Excel
 * @author lp
 *
 */
@Slf4j
public class ExcelRead {
        public int totalRows; //sheet中总行数
        public static int totalCells; //每一行总单元格数
        /**
         * read the Excel .xlsx,.xls
         * @param file 上传文件
         * @return
         * @throws IOException
         * 判断文件是否无误
         */
        public List<ArrayList<Object>> readExcel(MultipartFile file) throws IOException {
            if(file==null||ExcelUtil.EMPTY.equals(file.getOriginalFilename().trim())){
                return null;
            }else{
                String postfix = ExcelUtil.getPostfix(file.getOriginalFilename());
                if(!ExcelUtil.EMPTY.equals(postfix)){
                    //判断excel级别
                    if(ExcelUtil.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)){
                        return readXls(file);
                    }else if(ExcelUtil.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)){
                        return readXlsx(file);
                    }else{
                        return null;
                    }
                }
            }
            return null;
        }

//    /**
//     * 读取 2010 版的 Excel 文件  以Map<String ,List<String>> 数据存储
//     * read the Excel 2010 .xlsx
//     * @param file
//     * @return
//     * @throws IOException
//     */
//    public List<ArrayList<Object>> readXlsxByMap(MultipartFile file){
//        //List<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
//        Map<String, List<String>> map = new HashMap<>();
//        // IO流读取文件
//        InputStream input = null;
//        //创建工作薄对象
//        XSSFWorkbook wb = null;
//        //用于保存每行数据
//        ArrayList<Object> rowList = null;
//        try {
//            input = file.getInputStream();
//            wb = new XSSFWorkbook(input);
//            //读取sheet(页)
//            log.info("excel文件的sheet数量：wb.getNumberOfSheets() = {}",wb.getNumberOfSheets());
//            for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){
//                //获取工作表
//                XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
//                if(xssfSheet == null){
//                    continue;
//                }
//                //获取列名
//                XSSFRow xssfRow = xssfSheet.getRow(3);
//                //getLastRowNum()  获取最后一行的编号（编号从0开始）
//                totalRows = xssfSheet.getLastRowNum();
//                //获取这个一行最后不为空的 列
//                totalCells = xssfRow.getLastCellNum();
//
//                if(xssfRow!=null){
//                    rowList = new ArrayList<Object>();
//                    //读取行，从第一行开始
//                    for(int c=0;c<totalCells;c++){
//                        XSSFCell cell = xssfRow.getCell(c);
//                        if(cell==null){
//                            rowList.add(ExcelUtil.EMPTY);
//                            continue;
//                        }
//                        map.put(ExcelUtil.getXValue(cell).trim(), null);
//                    }
//                }
//                /** 循环Excel的行 */
//
//                for (int r = 0; r < this.totalRows; r++)
//                {
//                    String key = "";
//
//                    XSSFRow xssfRow2 = xssfSheet.getRow(3);
//
//                    XSSFRow row = xssfSheet.getRow(r);
//                    if (row == null)
//                    {
//
//                        continue;
//
//                    }
//
//                    /***
//                     * 先读取 列名放到key 值
//                     * 在读取 列值 放到map里面
//                     * 完成Map<列名，List<列值> > 的存储
//                     */
//
//
//                    List<String> rowLst = new ArrayList<String>();
//
//                    /** 循环Excel的列 获取集合*/
//
//                    for (int c = 0; c < totalCells; c++)
//                    {
//                        XSSFCell cell = row.getCell(c);
//
//                    rowList.add(ExcelUtil.getXValue(cell).trim());
//
//                    }
//                    map.put("", rowLst);
//
//                }
//
//
//
//
//
//            }
//            return list;
//        } catch (IOException e) {
//            log.error(e.getMessage());
//        } finally{
//            try {
//                input.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

    /**
         * 读取 2010 版的 Excel 文件
         * read the Excel 2010 .xlsx
         * @param file
         * @return
         * @throws IOException
         */
        public List<ArrayList<Object>> readXlsx(MultipartFile file){
            List<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
            // IO流读取文件
            InputStream input = null;
            //创建工作薄对象
            XSSFWorkbook wb = null;
            //用于保存每行数据
            ArrayList<Object> rowList = null;
            try {
                input = file.getInputStream();
                wb = new XSSFWorkbook(input);
                //读取sheet(页)
                log.info("excel文件的sheet数量：wb.getNumberOfSheets() = {}",wb.getNumberOfSheets());
                for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){
                    //获取工作表
                    XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
                    if(xssfSheet == null){
                        continue;
                    }
                    //getLastRowNum()  获取最后一行的编号（编号从0开始）
                    totalRows = xssfSheet.getLastRowNum();
                    //读取Row,从第二行开始
                    for(int rowNum = 0;rowNum <= totalRows;rowNum++){
                        //获取一行内的信息
                        XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                        if(xssfRow!=null){
                            rowList = new ArrayList<Object>();
                            //获取这个一行最后不为空的 列
                            totalCells = xssfRow.getLastCellNum();
                            log.info("列数：totalCells={}",totalCells);
                            //读取列，从第一列开始
                            for(int c=0;c<totalCells;c++){
                                XSSFCell cell = xssfRow.getCell(c);
                                log.info("cell的值为：{}",cell);
                                //log.info("cell.getRawValue() = {}",cell.getRawValue());
                                if(cell==null){
                                    log.error("如果cell为空，向list中添加：ExcelUtil.EMPTY={}",ExcelUtil.EMPTY);
                                    rowList.add(ExcelUtil.EMPTY);
                                    continue;
                                }
                                rowList.add(ExcelUtil.getXValue(cell).trim());
                                //rowList.add(cell.getStringCellValue());
                                log.info("单元格值：ExcelUtil.getXValue(cell).trim()={}",ExcelUtil.getXValue(cell).trim());
                            }
                            list.add(rowList);
                        }
                    }
                }
                return list;
            } catch (IOException e) {
                log.error(e.getMessage());
            } finally{
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
             }
            return null;
        }


        /**
         * read the Excel 2003-2007 .xls
         * @param file
         * @return
         * @throws IOException
         */
        public List<ArrayList<Object>> readXls(MultipartFile file){
            List<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
            // IO流读取文件
            InputStream input = null;
            HSSFWorkbook wb = null;
            ArrayList<Object> rowList = null;
            try {
                input = file.getInputStream();
                // 创建文档
                wb = new HSSFWorkbook(input);
                //读取sheet(页)
                for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){
                    HSSFSheet hssfSheet = wb.getSheetAt(numSheet);
                    if(hssfSheet == null){
                        continue;
                    }
                    totalRows = hssfSheet.getLastRowNum();
                    //读取Row,从第二行开始
                    for(int rowNum = 1;rowNum <= totalRows;rowNum++){
                        HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                        if(hssfRow!=null){
                            rowList = new ArrayList<Object>();
                            totalCells = hssfRow.getLastCellNum();
                            //读取列，从第一列开始
                            for(short c=0;c<=totalCells+1;c++){
                                HSSFCell cell = hssfRow.getCell(c);
                                if(cell==null){
                                    rowList.add(ExcelUtil.EMPTY);
                                    continue;
                                }
                                rowList.add(ExcelUtil.getHValue(cell).trim());
                            }
                            list.add(rowList);
                        }
                    }
                }
                return list;
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }



