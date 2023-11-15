package com.demo.smartport.Entity.UserAndEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
//用户修改密码请求体
public class UserUpdatePassReq implements Serializable {
    private String account;         //用户名
    private String fore;            //原密码
    private String after;           //新密码
}
