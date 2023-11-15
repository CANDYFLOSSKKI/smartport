package com.demo.smartport.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.smartport.Entity.Entrust.EntrustHandleReq;
import com.demo.smartport.Entity.Entrust.EntrustUpdateReq;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.RestoreAndGoods.GoodsRestoreReq;
import com.demo.smartport.Table.Goods;

public interface GoodsService extends IService<Goods> {
    public FlagResp addOne(EntrustHandleReq req,String appkey);

    public Goods getOneByKey(String appkey);

    public FlagResp updateOne(EntrustUpdateReq req);

    public FlagResp delOne(String appkey);

    public FlagResp restoreOne(GoodsRestoreReq req);


}
