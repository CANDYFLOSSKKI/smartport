package com.demo.smartport.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.smartport.Entity.UserAndEmployee.EmployeeConfirmReq;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Table.Employee;

public interface EmployeeService extends IService<Employee> {

    //港口后台审批用户信息认证
    public FlagResp confirmEmployee(EmployeeConfirmReq req);
}
