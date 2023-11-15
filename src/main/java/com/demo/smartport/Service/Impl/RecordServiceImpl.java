package com.demo.smartport.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.smartport.Entity.LogAndRecord.RecordInfo;
import com.demo.smartport.Mapper.RecordMapper;
import com.demo.smartport.Service.RecordService;
import com.demo.smartport.Table.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void record(int type) {
        LocalDateTime ldt=LocalDateTime.now();
        String daytime=ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Record record=getOne();
        if(record==null){
            switch(type){
                case 1 -> {recordMapper.insert(new Record(0,daytime,1,0,0,0,0));}
                case 2 -> {recordMapper.insert(new Record(0,daytime,0,1,0,0,0));}
                case 3 -> {recordMapper.insert(new Record(0,daytime,0,0,1,0,0));}
                case 4 -> {recordMapper.insert(new Record(0,daytime,0,0,0,1,0));}
                case 5 -> {recordMapper.insert(new Record(0,daytime,0,0,0,0,1));}
            }
        }else{
            switch(type){
                case 1 -> {record.setApplicense(record.getApplicense()+1);}
                case 2 -> {record.setAppentrust(record.getAppentrust()+1);}
                case 3 -> {record.setApporder(record.getApporder()+1);}
                case 4 -> {record.setApptrue(record.getApptrue()+1);}
                case 5 -> {record.setAppfalse(record.getAppfalse()+1);}
            }
            recordMapper.updateById(record);
        }
    }

    @Override
    public Record getOne() {
        LocalDateTime ldt=LocalDateTime.now();
        String daytime=ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LambdaQueryWrapper<Record> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Record::getDaytime,daytime);
        return recordMapper.selectOne(wrapper);
    }

}
