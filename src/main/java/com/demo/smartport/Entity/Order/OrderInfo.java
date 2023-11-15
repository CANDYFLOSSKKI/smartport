package com.demo.smartport.Entity.Order;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderInfo implements Serializable {
    private String appkey;      //工单号
    private String agent;       //货代名称
    private String goods;       //货物key
    private double value;       //发货数量

    private Integer type;       //运输类型
                                //0:陆运
                                //1:船运

    private String craft;       //船名
    private String wagon;       //车牌号
    private String name;        //对接人
    private String phone;       //对接人电话
    private String arrive;      //提货时间
    private String auth;        //合同材料
    private boolean process;

    private boolean flag;       //是否已审批
    private boolean result;     //审批结果
    private String approve;     //审批用户
    private String review;      //审批意见
}
