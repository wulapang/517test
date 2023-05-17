package com.example.demo.controller;

import com.example.demo.entity.Emp;
import com.example.demo.entity.User;
import com.example.demo.service.EmpService;
import com.example.demo.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmpCtrl {
    @Autowired
    private EmpService empService;

    @PostMapping("/search")
    public Map<String, Object> search(@RequestBody Emp body) {
        return empService.search(body);
    }

    @GetMapping("/importUser")
    public void importItem(HttpServletResponse response) throws IOException {
        Workbook workbook = empService.getExcelFile();
        OutputStream output = response.getOutputStream();
        String fileName = "用户数据.xls";
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" + "filename*=utf-8''" + fileName);
        response.setCharacterEncoding("UTF-8");
        workbook.write(output);
        output.close();
    }

    @RequestMapping(value = "/delete")
    public Integer delete(Integer id) {
        int result = empService.delete(id);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestBody Emp emp) {
        int result = empService.Update(emp);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Emp insert(@RequestBody Emp emp) {
        return empService.insertEmp(emp);
    }

    @RequestMapping("/ListEmp")
    @ResponseBody
    public List<Emp> ListEmp() {
        return empService.ListEmp();
    }


    /**
     * 分页
     * @return
     */
    @RequestMapping(value="/page")
    @ResponseBody
    public List<User> page(Integer page){
        int pageNow = page == null ? 1 : page;
        int pageSize = 10;
        int startRows = pageSize*(pageNow-1);
        List<User> list = empService.queryPage(startRows);
        return list;
    }

    /**
     * rows
     * @return
     */
    @RequestMapping(value="/rows")
    @ResponseBody
    public int rows(){
        return empService.getRowCount();
    }
}
