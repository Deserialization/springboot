package com.zs.demo.dao;

import com.zs.demo.bean.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentDao {


    Department deptById(Integer id);
}
