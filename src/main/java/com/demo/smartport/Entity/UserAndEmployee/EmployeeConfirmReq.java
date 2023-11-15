package com.demo.smartport.Entity.UserAndEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
//港口后台审批用户注册认证请求体
public class EmployeeConfirmReq implements Serializable {
    private String code;    //工号
    private String name;    //真实姓名
}
