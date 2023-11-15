package com.demo.smartport.Entity.Approval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApprovalTypeOrderLog implements Serializable {

    private String appkey;      //工单号
    private String account;
    private String agent;       //货代名称
    private String goods;       //货物key
    private double value;       //发货数量

    private int type;       //运输类型
                                //0:陆运
                                //1:船运

    private String craft;       //船名
    private String wagon;       //车牌号
    private String name;        //对接人
    private String phone;       //对接人电话
    private String arrive;      //提货时间
    private String auth;        //合同材料

    private boolean result;
    private String review;
}
