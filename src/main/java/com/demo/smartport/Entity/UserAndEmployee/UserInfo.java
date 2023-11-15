package com.demo.smartport.Entity.UserAndEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfo implements Serializable {
    private int id;
    private String account;
    private String password;
    private int grade;
    private String name;
    private String email;
    private String portrait;
    private Integer enable;     //许可证审核状态
                                //0:没有绑定的许可证
                                //1:许可证等待审批中
                                //2:正在审批中
                                //3:许可证审批未通过
                                //4:许可证通过审批
}
