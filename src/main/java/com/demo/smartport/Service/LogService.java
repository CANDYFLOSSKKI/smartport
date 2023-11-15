package com.demo.smartport.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.smartport.Entity.LogAndRecord.LogApplyReq;
import com.demo.smartport.Entity.LogAndRecord.LogFullBundleResp;
import com.demo.smartport.Entity.LogAndRecord.LogInfo;
import com.demo.smartport.Table.Log;

import java.util.List;

public interface LogService extends IService<Log> {
    public void apply(LogApplyReq req);

    public List<LogInfo> getList();

    public LogFullBundleResp getFullBundle();
}
