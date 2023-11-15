package com.demo.smartport.Entity.LogAndRecord;

import com.demo.smartport.Table.Record;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecordInfo implements Serializable {
    public boolean flag;

    public int apply;
    public int license;
    public int entrust;
    public int order;
    public int approval;
    public int success;
    public int back;

    public static RecordInfo GEN(Record record){
        if(record==null){
            return new RecordInfo(false,0,0,0,0,0,0,0);
        }
        return new RecordInfo(true,
                record.getApplicense()+record.getAppentrust()+record.getApporder(),
                record.getApplicense(),
                record.getAppentrust(),
                record.getApporder(),
                record.getApptrue()+record.getAppfalse(),
                record.getApptrue(),
                record.getAppfalse()
        );
    }
}
