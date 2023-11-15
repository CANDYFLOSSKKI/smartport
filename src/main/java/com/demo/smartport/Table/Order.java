package com.demo.smartport.Table;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("tb_order")
public class Order implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String account;     //申请人
    private String appkey;      //工单号
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String agent;       //货代名称

    private String goods;       //货物key
    private Double value;       //发货数量

    private Integer type;       //运输类型
                                //0:船运
                                //1:陆运

    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String craft;       //船名
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String wagon;       //车牌号
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String name;        //对接人
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String phone;       //对接人电话


    private String arrive;      //提货时间
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String auth;        //合同材料

    private Integer process;    //是否已发货
                                //0:未发货
                                //1:已发货
}
