package com.demo.smartport.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.RestoreAndGoods.RestoreInfo;
import com.demo.smartport.Entity.RestoreAndGoods.RestoreOccupyReq;
import com.demo.smartport.Entity.RestoreAndGoods.RestoreInfoListResp;
import com.demo.smartport.Table.Restore;

import java.util.List;

public interface RestoreService extends IService<Restore> {

    public RestoreInfoListResp getInfoList();

    public FlagResp restoreOccupy(RestoreOccupyReq req);

    public FlagResp restoreBack(int id,double value);

    public List<Restore> getList();

}
