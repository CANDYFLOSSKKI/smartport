package com.demo.smartport.Entity.UserAndEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
//用户绑定个人信息请求体
public class UserBindPortraitReq implements Serializable {
    private String account;     //用户名
    private String name;        //昵称
    private String portrait;    //头像图床地址
}
