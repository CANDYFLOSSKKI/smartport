package com.demo.smartport.Table;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("tb_license")
//用户许可证信息
public class License implements Serializable {
    @TableId(type= AUTO)
    private Integer id;         //(主键)id
    private String account;     //用户名
    private String company;     //所属公司名
    private String name;        //真实姓名
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String auth;        //公司出示许可url
    private String appkey;         //审批工单key
}
