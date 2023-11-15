package com.demo.smartport.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.ShipAndCraft.ShipInfo;
import com.demo.smartport.Entity.ShipAndCraft.ShipKeyWordListResp;
import com.demo.smartport.Entity.ShipAndCraft.ShipStatusReq;
import com.demo.smartport.Entity.ShipAndCraft.ShipUpdateReq;
import com.demo.smartport.Table.Ship;

import java.util.List;

public interface ShipService extends IService<Ship> {

    //船舶绑定数据列表(全部/关键词)
    public List<ShipInfo> getList();
    public ShipKeyWordListResp getKeyWordList(String key);

    //船舶绑定数据获取(id/船名)
    public ShipInfo getOneById(int id);
    public ShipInfo getOneByName(String name);


    //表结构数据Ship转前端数据ShipInfo
    public ShipInfo transShipToInfo(Ship ship);

    //船舶类型/船舶状态数字转字符串
    public String getShipType(int i);
    public String getShipStatus(int i);
    public int getShipStatus(String status);


    public FlagResp updateOneByName(ShipUpdateReq req);
    public String nullValueProcess(String value);
    public FlagResp updateStatus(ShipStatusReq req);


}
