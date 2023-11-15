package com.demo.smartport.Entity.RestoreAndGoods;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodsHandleReq implements Serializable {
    private String name;            //货物名
    private String signature;       //货物标志
    private String pack;            //货物包装
    private double value;           //数量
    private double weight;          //重量
    private double volume;          //体积
}
