package com.demo.smartport.Entity.UserAndEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
//用户登录请求体
//登录时自动识别用户等级
public class UserLogInReq implements Serializable {
    private String account;         //用户名
    private String password;        //密码
}
