package com.demo.smartport.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.smartport.Entity.Approval.ApprovalHandleReq;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.LogAndRecord.LogApplyReq;
import com.demo.smartport.Entity.Order.*;
import com.demo.smartport.Mapper.OrderMapper;
import com.demo.smartport.Service.*;
import com.demo.smartport.Table.*;
import com.demo.smartport.Util.Handler.ApprovalKeyHandler;
import com.demo.smartport.Util.Handler.PrimaryWrapperHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@CrossOrigin
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private SpaceService spaceService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private LogService logService;


    @Override
    public Order getOneByKey(String appkey) {
        return orderMapper.selectOne(PrimaryWrapperHandler.getOrderKeyQuery(appkey));
    }

    @Override
    public FlagResp addOne(OrderHandleReq req) {
        if(!spaceService.preOrderExamine(req.getGoods(),req.getValue(),1,0)){
            return FlagResp.OFF().MES("容量不足,请刷新页面");
        }
        String appkey= ApprovalKeyHandler.getApprovalKey(Order.class,req.getAccount());
        orderMapper.insert(new Order(0,
                req.getAccount(),
                appkey,
                req.getAgent(),
                req.getGoods(),
                req.getValue(),
                req.getType(),
                req.getCraft(),
                req.getWagon(),
                req.getName(),
                req.getPhone(),
                req.getArrive(),
                req.getAuth(),
                0
        ));
        Order order=orderMapper.selectOne(PrimaryWrapperHandler.getOrderKeyQuery(appkey));
        approvalService.addApproval(new ApprovalHandleReq(appkey,"ORDER",order.getId(),order.getAccount()));
        spaceService.reserveOrder(req.getGoods(),req.getValue());
        recordService.record(3);
        logService.apply(LogApplyReq.GEN(req.getAccount(),"申请发货指令 "+appkey));
        return FlagResp.ON().MES("申请成功");
    }

    @Override
    public FlagResp updateOne(OrderUpdateReq req) {
        Order order=orderMapper.selectOne(PrimaryWrapperHandler.getOrderKeyQuery(req.getAppkey()));
        if(Objects.equals(req.getGoods(),order.getGoods())){
            if(!spaceService.preOrderExamine(req.getGoods(),req.getValue(),2,order.getValue())){
                return FlagResp.OFF().MES("容量不足,请刷新页面");
            }
        }else{
            if(!spaceService.preOrderExamine(req.getGoods(),req.getValue(),1,0)){
                return FlagResp.OFF().MES("容量不足,请刷新页面");
            }
        }
        spaceService.resetOrder(order.getGoods(),order.getValue());
        order.setAgent(req.getAgent());
        order.setGoods(req.getGoods());
        order.setValue(req.getValue());
        order.setType(req.getType());
        order.setCraft(req.getCraft());
        order.setWagon(req.getWagon());
        order.setName(req.getName());
        order.setPhone(req.getPhone());
        order.setArrive(req.getArrive());
        order.setAuth(req.getAuth());
        orderMapper.update(order,PrimaryWrapperHandler.getOrderKeyQuery(req.getAppkey()));
        spaceService.reserveOrder(order.getGoods(),order.getValue());
        approvalService.resetOrderApproval(req.getAppkey());
        return FlagResp.ON().MES("提交成功");
    }

    @Override
    public OrderRowListResp getList(String account) {
        List<Order> list=orderMapper.selectList(PrimaryWrapperHandler.getOrderAccountQuery(account));
        if(list.isEmpty()){
            return new OrderRowListResp(false,0,null);
        }
        List<OrderRow> result=new ArrayList<>();
        for(Order i:list) {
            System.out.println(i.getGoods());
            Space space=spaceService.getOneByKey(i.getGoods());
            Approval approval=approvalService.getOneByKey(i.getAppkey());
            int status=0;
            if(approval.getApprove()==null){
                status=1;
            }else{
                if(approval.getFlag()==0){
                    status=2;
                }else{
                    status=approval.getResult()+3;
                }
            }
            result.add(new OrderRow(
                    i.getAppkey(),
                    i.getAgent(),
                    space.getName(),
                    i.getType(),
                    i.getArrive(),
                    status,
                    i.getProcess()==1
            ));
        }
        return new OrderRowListResp(true,result.size(),result);
    }

    @Override
    public OrderInfo getInfo(String appkey) {
        Order order=orderMapper.selectOne(PrimaryWrapperHandler.getOrderKeyQuery(appkey));
        Approval approval=approvalService.getOneByKey(appkey);
        return new OrderInfo(appkey,
                order.getAgent(),
                order.getGoods(),
                order.getValue(),
                order.getType(),
                order.getCraft(),
                order.getWagon(),
                order.getName(),
                order.getPhone(),
                order.getArrive(),
                order.getAuth(),
                order.getProcess()==1,
                approval.getFlag()==1,
                approval.getResult()==1,
                approval.getApprove(),
                approval.getReview()
        );
    }

    @Override
    public FlagResp delOne(String appkey) {
        Order order=orderMapper.selectOne(PrimaryWrapperHandler.getOrderKeyQuery(appkey));
        spaceService.resetOrder(order.getGoods(),order.getValue());
        orderMapper.delete(PrimaryWrapperHandler.getOrderKeyQuery(appkey));
        approvalService.delOrderApproval(appkey);
        return FlagResp.ON().MES("删除成功");
    }

    @Override
    public FlagResp processOne(String appkey) {
        Order order=orderMapper.selectOne(PrimaryWrapperHandler.getOrderKeyQuery(appkey));
        order.setProcess(1);
        orderMapper.update(order,PrimaryWrapperHandler.getOrderKeyUpdate(appkey));
        spaceService.processOrder(order.getGoods(),order.getValue());
        Approval approval=approvalService.getOneByKey(appkey);
        logService.apply(LogApplyReq.GEN(approval.getApply(),"发货指令执行 "+appkey));
        return FlagResp.ON().MES("发货成功");
    }
}
