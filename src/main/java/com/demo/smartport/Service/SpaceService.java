package com.demo.smartport.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.Space.SpaceInfo;
import com.demo.smartport.Entity.Space.SpaceInfoListResp;
import com.demo.smartport.Entity.Space.SpaceOriginReq;
import com.demo.smartport.Table.Goods;
import com.demo.smartport.Table.Space;

public interface SpaceService extends IService<Space> {

    public FlagResp addGoodsToSpace(Goods goods);

    public SpaceInfoListResp getList(String account);

    public SpaceInfoListResp getUpdateList(SpaceOriginReq req);

    public SpaceInfo getSpaceInfo(String appkey);

    public Space getOneByKey(String key);


    public FlagResp reserveOrder(String appkey,double value);
    public FlagResp resetOrder(String appkey, double value);
    public FlagResp processOrder(String appkey,double value);

    public boolean preOrderExamine(String appkey,double value,int sign,double origin);
}
