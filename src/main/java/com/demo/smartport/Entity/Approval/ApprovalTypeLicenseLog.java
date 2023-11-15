package com.demo.smartport.Entity.Approval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApprovalTypeLicenseLog implements Serializable {
    private String account;     //用户名
    private String company;     //所属公司名
    private String name;        //真实姓名
    private String auth;        //公司出示许可url
    private String appkey;
    private boolean result;
    private String review;
}
