package com.demo.smartport.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.smartport.Entity.LogAndRecord.RecordInfo;
import com.demo.smartport.Table.Record;

public interface RecordService extends IService<Record> {
    public void record(int type);

    public Record getOne();

}
