package com.demo.smartport.Entity.Approval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApprovalTypeEntrustLog implements Serializable {
    private String account;
    private String appkey;
    private String craft;           //进港船舶名
    private String arrive;          //进港时间
    private String auth;            //合同文件
    private String name;
    private String signature;       //货物标志
    private String pack;            //货物包装
    private double value;           //数量
    private double weight;          //重量
    private double volume;          //体积

    private int restore;
    private boolean result;
    private String review;
}
