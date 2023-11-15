package com.demo.smartport.Entity.Entrust;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntrustUpdateReq implements Serializable {
    private String appkey;
    private String arrive;
    private String auth;
    private String name;            //货物名
    private String signature;       //货物标志
    private String pack;            //货物包装
    private Double value;           //数量
    private Double weight;          //重量
    private Double volume;          //体积
}
