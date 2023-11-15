package com.demo.smartport.Entity.Entrust;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntrustInfo implements Serializable {
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
    private int restore;            //堆场编号

    private boolean flag;       //是否已审批
    private boolean result;     //审批结果
    private String approve;     //审批用户
    private String review;      //审批意见
}
