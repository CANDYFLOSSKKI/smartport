package com.demo.smartport.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.smartport.Table.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
}
