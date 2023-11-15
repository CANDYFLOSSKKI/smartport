package com.demo.smartport.Table;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("tb_user")
//用户信息
public class User implements Serializable {
    @TableId
    private Integer id;             //(主键)id
    private String account;         //用户名
    private String password;        //密码
    private Integer grade;          //用户等级
                                    //1:客户用户
                                    //2:港口后台审批用户
                                    //3:超级管理员(待补)

    private String name;            //昵称
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String email;           //绑定邮箱
    private String portrait;        //头像图床地址
    private Integer license;        //许可证编号
}
