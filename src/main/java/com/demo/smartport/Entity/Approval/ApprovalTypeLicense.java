package com.demo.smartport.Entity.Approval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApprovalTypeLicense implements Serializable {
    private String account;     //用户名
    private String company;     //所属公司名
    private String name;        //真实姓名
    private String auth;        //公司出示许可url
    private String appkey;
}
