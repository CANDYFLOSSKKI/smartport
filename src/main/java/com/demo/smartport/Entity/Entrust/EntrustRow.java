package com.demo.smartport.Entity.Entrust;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntrustRow implements Serializable {
    private String appkey;
    private String craft;
    private String name;
    private String arrive;
    private int status;
}
