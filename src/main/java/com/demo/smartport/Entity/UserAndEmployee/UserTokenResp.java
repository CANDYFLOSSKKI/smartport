package com.demo.smartport.Entity.UserAndEmployee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTokenResp implements Serializable {
    private boolean flag;
    private String token;

    public static UserTokenResp ON(){
        return new UserTokenResp(true,null);
    }
    public static UserTokenResp OFF(){
        return new UserTokenResp(false,null);
    }

    public UserTokenResp TOKEN(String token){
        this.token=token;
        return this;
    }
}
