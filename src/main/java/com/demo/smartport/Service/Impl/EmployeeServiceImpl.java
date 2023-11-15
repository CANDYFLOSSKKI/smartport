package com.demo.smartport.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.smartport.Entity.UserAndEmployee.EmployeeConfirmReq;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Table.Employee;
import com.demo.smartport.Table.License;
import com.demo.smartport.Mapper.EmployeeMapper;
import com.demo.smartport.Mapper.LicenseMapper;
import com.demo.smartport.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private LicenseMapper licenseMapper;

    @Override
    public FlagResp confirmEmployee(EmployeeConfirmReq req) {
        LambdaQueryWrapper<Employee> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getCode,req.getCode()).eq(Employee::getName,req.getName());
        if(employeeMapper.selectOne(wrapper)==null){
            return FlagResp.OFF().MES("未找到工作证信息");
        }
        LambdaQueryWrapper<License> wp_license=new LambdaQueryWrapper<License>();
        wp_license.eq(License::getName,req.getName());
        if(licenseMapper.selectOne(wp_license)!=null){
            return FlagResp.OFF().MES("该用户已注册,请检查工作证信息或登录");
        }
        return FlagResp.ON().MES("验证成功");
    }
}
