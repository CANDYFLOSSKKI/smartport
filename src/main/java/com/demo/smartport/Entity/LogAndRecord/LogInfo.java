package com.demo.smartport.Entity.LogAndRecord;

import com.demo.smartport.Table.Log;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogInfo implements Serializable {
    private String name;
    private String description;
    private String daytime;

    public static LogInfo GEN(Log log){
        return new LogInfo(log.getName(),log.getDescription(),log.getDaytime());
    }
}
