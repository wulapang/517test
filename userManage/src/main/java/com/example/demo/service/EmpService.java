package com.example.demo.service;

import com.example.demo.entity.Emp;
import com.example.demo.entity.User;
import com.example.demo.mapper.EmpMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpService {
    @Autowired
    EmpMapper empMapper;

    public List<User> queryPage(Integer startRows) {
        return empMapper.queryPage(startRows);
    }

    public int getRowCount() {
        return empMapper.getRowCount();
    }

    public Emp insertEmp(Emp emp) {
        empMapper.insertEmp(emp);
        return emp;
    }

    public List<Emp> ListEmp(){
        return empMapper.ListEmp();
    }

    public int Update(Emp emp){
        return empMapper.Update(emp);
    }

    public int delete(int id){
        return empMapper.delete(id);
    }

    public Workbook getExcelFile() {
        List<Emp> emps = ListEmp();
        Workbook workbook = getWorbook(emps);
        return workbook;
    }

    private static CellStyle getworkDataStyle(Workbook wb) {
        CellStyle dataStyle = wb.createCellStyle();
        //单元格边框
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        // 设置居中样式，水平居中
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        return dataStyle;
    }


    private static CellStyle getworkTitleStyle(Workbook wb) {
        //标题和页码
        CellStyle titleStyle = wb.createCellStyle();
        // 设置单元格对齐方式,水平居左
        titleStyle.setAlignment(HorizontalAlignment.LEFT);
        //单元格边框
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setBorderBottom(BorderStyle.THIN);
        // 设置单元格对齐方式,水平居左
        titleStyle.setAlignment(HorizontalAlignment.LEFT);
        // 设置字体样式
        Font titleFont = wb.createFont();
        // 字体高度
        titleFont.setFontHeightInPoints((short) 12);
        // 字体样式
        titleFont.setFontName("黑体");
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

    private Workbook getWorbook(List<Emp> emps) {
        Workbook wb = new HSSFWorkbook();
        CellStyle titleStyle = getworkTitleStyle(wb);
        CellStyle dataStyle = getworkDataStyle(wb);
        Sheet sheet = wb.createSheet("用户信息");
        sheet.autoSizeColumn(0);
        // 添加标题数据
        Row row = sheet.createRow(0);
        int index = 0;
        Cell cell = row.createCell(index++);
        cell.setCellValue("编号");
        cell.setCellStyle(titleStyle);
        cell = row.createCell(index++);
        cell.setCellValue("员工姓名");
        cell.setCellStyle(titleStyle);
        cell = row.createCell(index++);
        cell.setCellValue("年龄");
        cell.setCellStyle(titleStyle);
        cell = row.createCell(index++);
        cell.setCellValue("性别");
        cell.setCellStyle(titleStyle);
        cell = row.createCell(index++);
        cell.setCellValue("部门");
        cell.setCellStyle(titleStyle);
        cell = row.createCell(index);
        cell.setCellValue("学历");
        cell.setCellStyle(titleStyle);

        // 添加数据部分数据
        try {
            for (int i = 0; i < emps.size(); ++i) {
                Row datarow = sheet.createRow(i + 1);
                index = 0;
                Emp emp = emps.get(i);
                cell = datarow.createCell(index++);
                cell.setCellValue(emp.getId());
                cell.setCellStyle(dataStyle);

                cell = datarow.createCell(index++);
                cell.setCellValue(emp.getEmpName());
                cell.setCellStyle(dataStyle);

                cell = datarow.createCell(index++);
                cell.setCellValue(emp.getAge());
                cell.setCellStyle(dataStyle);

                cell = datarow.createCell(index++);
                cell.setCellValue(emp.getSex());
                cell.setCellStyle(dataStyle);

                cell = datarow.createCell(index++);
                cell.setCellValue(emp.getDeptName());
                cell.setCellStyle(dataStyle);

                cell = datarow.createCell(index++);
                cell.setCellValue(emp.getEmpDegreeName());
                cell.setCellStyle(dataStyle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    public Map<String, Object> search(Emp body) {
        Integer count = empMapper.searchCount(body);
        List<Emp> emps = empMapper.search(body);
        Map<String, Object> res = new HashMap<>();
        res.put("data", emps);
        res.put("total", count);
        return res;
    }
}
