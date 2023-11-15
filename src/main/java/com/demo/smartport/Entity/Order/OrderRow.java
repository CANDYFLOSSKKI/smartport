package com.demo.smartport.Entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRow implements Serializable {
    private String appkey;
    private String agent;       //货代名
    private String goodsname;   //货物种类
    private int type;           //船运/货运
    private String arrive;      //到达时间
    private int status;     //1:等待审批
                            //2:审批中(已被预约)
                            //3:未通过审批
                            //4:审批通过
    private boolean process;
}
