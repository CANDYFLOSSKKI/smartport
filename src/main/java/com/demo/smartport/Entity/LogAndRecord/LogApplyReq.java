package com.demo.smartport.Entity.LogAndRecord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogApplyReq implements Serializable {
    private String name;
    private String mes;


    public static LogApplyReq GEN(String name,String mes){
        return new LogApplyReq(name,mes);
    }
}
