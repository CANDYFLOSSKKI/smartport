package com.demo.smartport.Util.Handler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.smartport.Entity.UserAndEmployee.UserLogInReq;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@NoArgsConstructor
@Component
public class TokenCodeHandler {
    //密钥
    private static final String SING = "smartport";

    //生成Token
    public static String getToken(UserLogInReq req){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("account",req.getAccount());
        if(req.getPassword()!=null){
            builder.withClaim("password",req.getPassword());
        }
        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SING));
    }

    //验证token合法性
    public static DecodedJWT verifyToken(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

}
