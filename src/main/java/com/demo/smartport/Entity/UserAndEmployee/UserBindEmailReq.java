package com.demo.smartport.Entity.UserAndEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
//用户绑定邮箱请求体
public class UserBindEmailReq implements Serializable {
    private String account;     //用户名
    private String fore;
    private String last;
}
