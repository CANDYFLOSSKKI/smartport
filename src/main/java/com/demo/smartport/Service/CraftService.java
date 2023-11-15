package com.demo.smartport.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.ShipAndCraft.CraftBatchReq;
import com.demo.smartport.Entity.ShipAndCraft.CraftHandleReq;
import com.demo.smartport.Entity.ShipAndCraft.CraftInfoPreResp;
import com.demo.smartport.Entity.ShipAndCraft.CraftInfoResp;
import com.demo.smartport.Table.Craft;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CraftService extends IService<Craft> {

    //客户用户绑定船舶
    public FlagResp addCraft(CraftHandleReq req);

    //客户用户解绑船舶
    public FlagResp delCraft(String name);

    //检查船舶绑定情况,是否能与该用户绑定
    public FlagResp confirmCraft(CraftHandleReq req);

    //获取单个用户的绑定船舶列表(附加简要信息)
    public CraftInfoResp getCraftInfoList(String account);


    public CraftInfoPreResp getCraftPreUserConfirm(String account);

    public FlagResp updateBatchStatus(CraftBatchReq req);
    public FlagResp delBatch(CraftBatchReq req);

    public CraftInfoResp getEntrustApplyInfo(String account);
}
