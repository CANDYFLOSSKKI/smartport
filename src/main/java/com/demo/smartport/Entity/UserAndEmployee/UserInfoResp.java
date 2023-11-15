package com.demo.smartport.Entity.UserAndEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoResp implements Serializable {
    private boolean flag;
    private UserInfo userinfo;

    public static UserInfoResp ON(){
        return new UserInfoResp(true,null);
    }
    public static UserInfoResp OFF(){
        return new UserInfoResp(false,null);
    }

    public UserInfoResp INFO(UserInfo info) {
        this.userinfo = info;
        return this;
    }
}
