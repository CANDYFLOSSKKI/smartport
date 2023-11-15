package com.demo.smartport.Table;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("tb_entrust")
public class Entrust implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String account;
    private String appkey;
    private String craft;           //进港船舶名
    private String arrive;          //进港时间
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String auth;            //合同文件
}
