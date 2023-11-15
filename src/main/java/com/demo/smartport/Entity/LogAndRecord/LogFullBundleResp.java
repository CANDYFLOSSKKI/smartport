package com.demo.smartport.Entity.LogAndRecord;

import com.demo.smartport.Table.Restore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogFullBundleResp implements Serializable {
    private boolean flag;
    private RecordInfo record;
    private List<Restore> restore;
    private List<LogInfo> log;
}
