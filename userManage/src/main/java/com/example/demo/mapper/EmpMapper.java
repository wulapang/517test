package com.example.demo.mapper;

import com.example.demo.entity.Emp;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {

    List<Emp> ListEmp();

    List<User> queryPage(Integer startRows);

    int getRowCount();

    int insertEmp(Emp emp);

    int delete(int id);

    int Update(Emp emp);

    Integer searchCount(Emp body);

    List<Emp> search(Emp body);
}
