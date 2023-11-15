package com.demo.smartport.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.smartport.Entity.LogAndRecord.LogApplyReq;
import com.demo.smartport.Entity.LogAndRecord.LogFullBundleResp;
import com.demo.smartport.Entity.LogAndRecord.LogInfo;
import com.demo.smartport.Entity.LogAndRecord.RecordInfo;
import com.demo.smartport.Mapper.LogMapper;
import com.demo.smartport.Service.LogService;
import com.demo.smartport.Service.RecordService;
import com.demo.smartport.Service.RestoreService;
import com.demo.smartport.Table.Log;
import com.demo.smartport.Table.Restore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private RestoreService restoreService;
    @Autowired
    private RecordService recordService;


    @Override
    public void apply(LogApplyReq req) {
        LocalDateTime ldt=LocalDateTime.now();
        String daytime=ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logMapper.insert(new Log(0,req.getName(),req.getMes(),daytime));
    }

    @Override
    public List<LogInfo> getList() {
        List<Log> list=new ArrayList<>();
        List<LogInfo> result=new ArrayList<>();
        list=logMapper.selectList(null);
        if(list.isEmpty()){
            return null;
        }
        for(Log log:list){
            result.add(LogInfo.GEN(log));
        }
        return result;
    }

    @Override
    public LogFullBundleResp getFullBundle() {
        RecordInfo record=RecordInfo.GEN(recordService.getOne());
        List<Restore> restoreList=restoreService.getList();
        List<LogInfo> logList = new ArrayList<>(getList());
        if(logList.isEmpty()){
            return new LogFullBundleResp(false,record,restoreList,null);
        }
        return new LogFullBundleResp(true,record,restoreList,logList);
    }
}
