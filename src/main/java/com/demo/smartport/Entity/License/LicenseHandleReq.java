package com.demo.smartport.Entity.License;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
//港口客户用户认证请求体
public class LicenseHandleReq implements Serializable {
    private String account;     //用户名
    private String company;     //所属公司名
    private String name;        //真实姓名
    private String auth;        //公司出示许可url
}
