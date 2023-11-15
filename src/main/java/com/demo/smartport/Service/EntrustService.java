package com.demo.smartport.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.smartport.Entity.Entrust.EntrustHandleReq;
import com.demo.smartport.Entity.Entrust.EntrustInfo;
import com.demo.smartport.Entity.Entrust.EntrustRowListResp;
import com.demo.smartport.Entity.Entrust.EntrustUpdateReq;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Table.Entrust;

public interface EntrustService extends IService<Entrust> {
    public FlagResp addOne(EntrustHandleReq req);

    public EntrustRowListResp getList(String account);

    public EntrustInfo getInfo(String appkey);

    public FlagResp updateOne(EntrustUpdateReq req);

    public FlagResp delOne(String appkey);
}
