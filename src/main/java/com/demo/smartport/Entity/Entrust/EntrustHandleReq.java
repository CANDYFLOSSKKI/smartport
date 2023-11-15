package com.demo.smartport.Entity.Entrust;

import com.demo.smartport.Entity.RestoreAndGoods.GoodsHandleReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntrustHandleReq implements Serializable {
    private String account;
    private String craft;
    private String arrive;
    private String auth;
    private GoodsHandleReq goods;
}
