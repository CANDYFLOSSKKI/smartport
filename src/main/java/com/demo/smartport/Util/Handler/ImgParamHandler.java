package com.demo.smartport.Util.Handler;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ImgParamHandler {
    public static String getUUID(String name){
        String suffix;
        if(name.endsWith(".jpeg")){
            suffix=name.substring(name.length()-5);
            name=name.substring(0,name.length()-5);
        }else{
            suffix=name.substring(name.length()-4);
            name=name.substring(0,name.length()-4);
        }
        LocalDateTime localDateTime=LocalDateTime.now();
        return name+"_"
                +localDateTime.getMonthValue()
                +localDateTime.getDayOfMonth()
                +localDateTime.getHour()
                +localDateTime.getMinute()
                +localDateTime.getSecond()
                +suffix;
    }

    public static String getHtmlURL(String url){
        int index=url.indexOf("blob");
        String url_a=url.substring(0,index);
        String url_b=url.substring(index+4);
        return url_a+"raw"+url_b;
    }
}
