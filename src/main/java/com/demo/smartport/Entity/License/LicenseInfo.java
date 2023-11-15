package com.demo.smartport.Entity.License;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.security.SecureRandomParameters;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LicenseInfo implements Serializable {
    private String account;     //用户名
    private String company;     //所属公司名
    private String name;        //真实姓名
    private String auth;        //公司出示许可url
    private String appkey;

    private boolean flag;       //是否已审批
    private boolean result;     //审批结果
    private String approve;     //审批用户
    private String review;      //审批意见
}
