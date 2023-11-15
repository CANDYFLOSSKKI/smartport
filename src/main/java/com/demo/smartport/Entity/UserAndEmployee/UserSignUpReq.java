package com.demo.smartport.Entity.UserAndEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
//用户注册请求体
public class UserSignUpReq implements Serializable {
    private String account;         //用户名
    private String password;        //密码
    private Integer grade;          //用户等级
    private EmployeeConfirmReq employee;    //(港口后台审批用户专用)职工信息
                                            //客户用户注册时置空即可
}
