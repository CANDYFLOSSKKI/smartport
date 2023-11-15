package com.demo.smartport.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
//无数据返回情况下的通用响应体
public class FlagResp implements Serializable {
    private boolean flag;       //操作成功标记
    private String mes;     //附带信息

    public static FlagResp ON(){
        return new FlagResp(true,null);
    }

    public static FlagResp OFF(){
        return new FlagResp(false,null);
    }

    public FlagResp MES(String message){
        this.mes = message;
        return this;
    }
}
