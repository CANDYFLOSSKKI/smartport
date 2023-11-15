package com.demo.smartport.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.smartport.Entity.Approval.ApprovalHandleReq;
import com.demo.smartport.Entity.Entrust.*;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.LogAndRecord.LogApplyReq;
import com.demo.smartport.Mapper.EntrustMapper;
import com.demo.smartport.Service.*;
import com.demo.smartport.Table.Approval;
import com.demo.smartport.Table.Entrust;
import com.demo.smartport.Table.Goods;
import com.demo.smartport.Util.Handler.ApprovalKeyHandler;
import com.demo.smartport.Util.Handler.PrimaryWrapperHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Service
@CrossOrigin
public class EntrustServiceImpl extends ServiceImpl<EntrustMapper, Entrust> implements EntrustService {
    @Autowired
    private EntrustMapper entrustMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private LogService logService;

    @Override
    public FlagResp addOne(EntrustHandleReq req) {
        String appkey=ApprovalKeyHandler.getApprovalKey(Entrust.class,req.getAccount());
        entrustMapper.insert(new Entrust(0,
            req.getAccount(),
            appkey,
            req.getCraft(),
            req.getArrive(),
            req.getAuth()
        ));
        Entrust entrust=entrustMapper.selectOne(PrimaryWrapperHandler.getEntrustKeyQuery(appkey));
        goodsService.addOne(req,appkey);
        approvalService.addApproval(new ApprovalHandleReq(appkey,"ENTRUST",entrust.getId(),entrust.getAccount()));
        recordService.record(2);
        logService.apply(LogApplyReq.GEN(req.getAccount(),"申请装卸作业委托书 "+appkey));
        return FlagResp.ON().MES("申请成功");
    }

    @Override
    public EntrustRowListResp getList(String account) {

        List<Entrust> list=entrustMapper.selectList(PrimaryWrapperHandler.getEntrustAccountQuery(account));
        List<EntrustRow> result=new ArrayList<>();
        if(list.isEmpty()){
            return new EntrustRowListResp(false,0,null);
        }
        for(Entrust i:list){
            String appkey=i.getAppkey();
            Goods good=goodsService.getOneByKey(appkey);
            Approval approval=approvalService.getOneByKey(appkey);
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
            result.add(new EntrustRow(appkey,
                    i.getCraft(),
                    good.getName(),
                    i.getArrive(),
                    status
            ));
        }
        return new EntrustRowListResp(true,result.size(),result);
    }

    @Override
    public EntrustInfo getInfo(String appkey) {
        Entrust entrust=entrustMapper.selectOne(PrimaryWrapperHandler.getEntrustKeyQuery(appkey));
        Goods good=goodsService.getOneByKey(appkey);
        Approval approval=approvalService.getOneByKey(appkey);
        return new EntrustInfo(appkey,
            entrust.getCraft(),
            entrust.getArrive(),
            entrust.getAuth(),
            good.getName(),
            good.getSignature(),
            good.getPack(),
            good.getValue(),
            good.getWeight(),
            good.getVolume(),
            good.getRestore(),
            approval.getFlag()==1,
            approval.getResult()==1,
            approval.getApprove(),
            approval.getReview()
        );
    }

    @Override
    public FlagResp updateOne(EntrustUpdateReq req) {
        Entrust entrust=entrustMapper.selectOne(PrimaryWrapperHandler.getEntrustKeyQuery(req.getAppkey()));
        entrust.setArrive(req.getArrive());
        entrust.setAuth(req.getAuth());
        entrustMapper.update(entrust,PrimaryWrapperHandler.getEntrustKeyUpdate(req.getAppkey()));
        goodsService.updateOne(req);
        approvalService.resetEntrustApproval(req.getAppkey());
        return FlagResp.ON().MES("提交成功");
    }

    @Override
    public FlagResp delOne(String appkey) {
        entrustMapper.delete(PrimaryWrapperHandler.getEntrustKeyQuery(appkey));
        goodsService.delOne(appkey);
        approvalService.delEntrustApproval(appkey);
        return FlagResp.ON().MES("删除成功");
    }
}
