package com.demo.smartport.Util.Handler;

import com.demo.smartport.Entity.UserAndEmployee.UserLogInReq;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@NoArgsConstructor
@Component
public class ApprovalKeyHandler {
    public static String getApprovalKey(Class clazz, String account){
        StringBuilder builder=new StringBuilder();
        System.out.println(clazz.getName());
        switch(clazz.getName()){
            case "com.demo.smartport.Table.License"->{builder.append("LCS");}
            case "com.demo.smartport.Table.Entrust"->{builder.append("ENT");}
            case "com.demo.smartport.Table.Order"->{builder.append("ODR");}
        }
        builder.append(account.length()%10);
        LocalDateTime ldt=LocalDateTime.now();
        builder.append(ldt.getMonthValue()<10?"0"+ldt.getMonthValue():ldt.getMonthValue());
        builder.append(ldt.getDayOfMonth()<10?"0"+ldt.getDayOfMonth():ldt.getDayOfMonth());
        Random random=new Random();
        builder.append(random.nextInt(10));
        builder.append(random.nextInt(10));
        return builder.toString();
    }
}
