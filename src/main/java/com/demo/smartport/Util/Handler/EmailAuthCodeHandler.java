package com.demo.smartport.Util.Handler;

import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.UserAndEmployee.UserBindEmailReq;
import lombok.NoArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

@NoArgsConstructor
@Component
public class EmailAuthCodeHandler {
    @Resource
    private JavaMailSender sender;

    private static final char[] ch = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
        'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static String getEmail(UserBindEmailReq req){
        return req.getFore().trim()+"@"+req.getLast().trim();
    }

    public static String getAuthCode(){
        Random random=new Random();
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<5;++i){
            int index=random.nextInt(ch.length);
            builder.append(ch[index]);
        }
        return builder.toString();
    }

    public FlagResp sendEmail(String email){
        SimpleMailMessage message=new SimpleMailMessage();
        String code=getAuthCode();
        message.setSubject("【智慧港口】邮箱验证码");
        message.setText("您的验证码为："+code+"\n请及时完成相关操作");
        message.setFrom("cteyneuron@163.com");
        message.setTo(email);
        sender.send(message);
        return FlagResp.ON().MES(code);
    }
}
