package com.demo.smartport.Table;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("tb_space")
public class Space implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String appkey;
    private String account;
    private Integer restore;
    private String name;            //货物名
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String signature;       //货物标志
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String pack;            //货物包装
    private Double value;           //数量
    private Double weight;          //重量
    private Double volume;          //体积
    private Double process;
}
