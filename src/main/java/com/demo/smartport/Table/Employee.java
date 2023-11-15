package com.demo.smartport.Table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("tb_employee")
//港口后台审批人员职工信息(注册认证用,均为静态数据)
public class Employee implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer id;     //(主键)id
    private String code;    //工号
    private String name;    //真实姓名
}
