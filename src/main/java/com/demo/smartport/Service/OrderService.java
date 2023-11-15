package com.demo.smartport.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.smartport.Entity.Entrust.EntrustHandleReq;
import com.demo.smartport.Entity.Entrust.EntrustInfo;
import com.demo.smartport.Entity.Entrust.EntrustRowListResp;
import com.demo.smartport.Entity.Entrust.EntrustUpdateReq;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.Order.OrderHandleReq;
import com.demo.smartport.Entity.Order.OrderInfo;
import com.demo.smartport.Entity.Order.OrderRowListResp;
import com.demo.smartport.Entity.Order.OrderUpdateReq;
import com.demo.smartport.Table.Order;

public interface OrderService extends IService<Order> {
    public Order getOneByKey(String appkey);

    public FlagResp addOne(OrderHandleReq req);

    public OrderRowListResp getList(String account);

    public OrderInfo getInfo(String appkey);

    public FlagResp updateOne(OrderUpdateReq req);

    public FlagResp delOne(String appkey);

    public FlagResp processOne(String appkey);
}
