package com.demo.smartport.Entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderHandleReq implements Serializable {
    private String account;
    private String agent;
    private String goods;
    private double value;
    private int type;
    private String craft;
    private String wagon;
    private String name;
    private String phone;
    private String arrive;
    private String auth;
}
