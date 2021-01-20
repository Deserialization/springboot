package com.example.demo.dao;

import com.example.demo.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentDao {


    Department deptById(Integer id);
}
