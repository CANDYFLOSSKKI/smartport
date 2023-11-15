package com.demo.smartport.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.smartport.Entity.Approval.*;
import com.demo.smartport.Entity.Entrust.EntrustInfo;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.LogAndRecord.LogApplyReq;
import com.demo.smartport.Entity.RestoreAndGoods.GoodsRestoreReq;
import com.demo.smartport.Entity.RestoreAndGoods.RestoreOccupyReq;
import com.demo.smartport.Entity.ShipAndCraft.ShipKeyWordInfo;
import com.demo.smartport.Mapper.ApprovalMapper;
import com.demo.smartport.Mapper.LicenseMapper;
import com.demo.smartport.Service.*;
import com.demo.smartport.Table.*;
import com.demo.smartport.Util.Handler.PrimaryWrapperHandler;
import com.demo.smartport.Util.Handler.StreamMethodsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@CrossOrigin
public class ApprovalServiceImpl extends ServiceImpl<ApprovalMapper, Approval> implements ApprovalService {
    @Autowired
    private ApprovalMapper approvalMapper;
    @Autowired
    private LicenseMapper licenseMapper;
    @Autowired
    private EntrustService entrustService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RestoreService restoreService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private LogService logService;

    @Override
    public FlagResp addApproval(ApprovalHandleReq req) {
        if(approvalMapper.selectOne(PrimaryWrapperHandler.getApprovalKeyQuery(req.getKey()))!=null){
            return FlagResp.OFF().MES("已存在该key对应的审批工单");
        }
        LocalDateTime localDateTime=LocalDateTime.now();
        String date=localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Approval approval=new Approval(0,req.getKey(),req.getType(),req.getIntro(),0,0,req.getApply(),null,null,date,null);
        approvalMapper.insert(approval);
        return FlagResp.ON().MES("创建工单成功");
    }

    @Override
    public FlagResp delLicenseApproval(String account) {
        approvalMapper.delete(PrimaryWrapperHandler.getApprovalLicenseQuery(account));
        return FlagResp.ON().MES("删除许可证工单成功");
    }

    @Override
    public FlagResp resetLicenseApproval(String account) {
        Approval approval=approvalMapper.selectOne(PrimaryWrapperHandler.getApprovalLicenseQuery(account));
        if(approval.getFlag()==1){
            recordService.record(1);
            logService.apply(LogApplyReq.GEN(account,"申请用户许可证 "+approval.getAppkey()));
        }
        approval.setFlag(0);
        approval.setResult(0);
        approval.setApprove(null);
        approval.setReview(null);
        LocalDateTime localDateTime=LocalDateTime.now();
        String date=localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        approval.setAppday(date);
        approval.setComday(null);
        approvalMapper.update(approval,PrimaryWrapperHandler.getApprovalLicenseUpdate(account));
        return FlagResp.ON().MES("重置许可证工单成功");
    }

    @Override
    public Approval getOneByKey(String key) {
        return approvalMapper.selectOne(PrimaryWrapperHandler.getApprovalKeyQuery(key));
    }

    @Override
    public FlagResp confirmStatus(String appkey) {
        Approval approval=getOneByKey(appkey);
        if(approval.getApprove()!=null){
            if(approval.getFlag()!=0){
                return FlagResp.ON().MES("已完成");
            }
            return FlagResp.OFF().MES("已预约未完成");
        }
        return FlagResp.ON().MES("未被预约");
    }

    @Override
    public ApprovalInfo transApprovalToInfo(Approval approval) {
        ApprovalInfo info=new ApprovalInfo();
        info.setAppday(approval.getAppday());
        info.setType(approval.getType());
        info.setAppkey(approval.getAppkey());
        info.setApply(approval.getApply());
        info.setIntro(approval.getIntro());
        return info;
    }

    @Override
    public ApprovalInfoListResp getKeyWordList(String key) {
        String keyUp=key.toUpperCase();
        LambdaQueryWrapper<Approval> wrapper_key=new LambdaQueryWrapper<>();
        wrapper_key.eq(Approval::getAppkey,keyUp).or()
                .like(Approval::getAppkey,keyUp).or()
                .likeLeft(Approval::getAppkey,keyUp).or()
                .likeRight(Approval::getAppkey,keyUp);
        LambdaQueryWrapper<Approval> wrapper_account=new LambdaQueryWrapper<>();
        wrapper_account.eq(Approval::getApply,key);
        List<Approval> list=new ArrayList<>();
        list.addAll(approvalMapper.selectList(wrapper_key));
        list.addAll(approvalMapper.selectList(wrapper_account));
        if(list.isEmpty()){
            return new ApprovalInfoListResp(false,0,null);
        }
        list=list.stream().distinct().filter(i->i.getApprove()==null).collect(Collectors.toList());
        List<ApprovalInfo> result=new ArrayList<>();
        for(Approval i:list){
            result.add(transApprovalToInfo(i));
        }
        return new ApprovalInfoListResp(true,result.size(),result);
    }

    @Override
    public ApprovalInfoListResp getFreeList() {
        LambdaQueryWrapper<Approval> wrapper=new LambdaQueryWrapper<>();
        wrapper.isNull(Approval::getApprove);
        List<Approval> list=approvalMapper.selectList(wrapper);
        if(list.isEmpty()){
            return new ApprovalInfoListResp(false,0,null);
        }
        List<ApprovalInfo> result=new ArrayList<>();
        for(Approval i:list){
            result.add(transApprovalToInfo(i));
        }
        return new ApprovalInfoListResp(true,result.size(),result);
    }

    @Override
    public ApprovalInfoListResp getReservedList(String account) {
        LambdaQueryWrapper<Approval> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Approval::getApprove,account).eq(Approval::getFlag,0);
        List<Approval> list=approvalMapper.selectList(wrapper);
        if(list.isEmpty()){
            return new ApprovalInfoListResp(false,0,null);
        }
        List<ApprovalInfo> result=new ArrayList<>();
        for(Approval i:list){
            result.add(transApprovalToInfo(i));
        }
        return new ApprovalInfoListResp(true,result.size(),result);
    }

    @Override
    public FlagResp reserveOne(ApprovalReserveReq req) {
        Approval approval=approvalMapper.selectOne(PrimaryWrapperHandler.getApprovalKeyQuery(req.getAppkey()));
        if(approval==null){
            return FlagResp.OFF().MES("该工单已被用户撤销");
        }
        approval.setApprove(req.getAccount());
        approvalMapper.update(approval,PrimaryWrapperHandler.getApprovalKeyUpdate(req.getAppkey()));
        logService.apply(LogApplyReq.GEN(req.getAccount(),"预约审批工单 "+req.getAppkey()));
        return FlagResp.ON().MES("预约成功");
    }

    @Override
    public FlagResp resetReserveOne(ApprovalReserveReq req) {
        Approval approval=approvalMapper.selectOne(PrimaryWrapperHandler.getApprovalKeyQuery(req.getAppkey()));
        if(approval==null){
            return FlagResp.OFF().MES("未找到已预约的工单");
        }
        if(!Objects.equals(approval.getApprove(),req.getAccount())){
            return FlagResp.OFF().MES("该工单已由其他人预约/处理");
        }
        approval.setApprove(null);
        approvalMapper.update(approval,PrimaryWrapperHandler.getApprovalKeyUpdate(req.getAppkey()));
        logService.apply(LogApplyReq.GEN(req.getAccount(),"取消预约审批工单 "+req.getAppkey()));
        return FlagResp.ON().MES("取消预约成功");
    }

    @Override
    public FlagResp dealOne(ApprovalDealReq req) {
        Approval approval=approvalMapper.selectOne(PrimaryWrapperHandler.getApprovalKeyQuery(req.getAppkey()));
        if(approval==null){
            return FlagResp.OFF().MES("未找到已预约的工单");
        }
        if(!Objects.equals(approval.getApprove(),req.getAccount())){
            return FlagResp.OFF().MES("该工单已由其他人预约/处理");
        }
        approval.setFlag(1);
        approval.setResult(req.isResult()?1:0);
        approval.setReview(req.getReview());
        approval.setApprove(req.getAccount());
        LocalDateTime localDateTime= LocalDateTime.now();
        String dateKey= localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        approval.setComday(dateKey);
        approvalMapper.update(approval,PrimaryWrapperHandler.getApprovalKeyQuery(req.getAppkey()));
        recordService.record(req.isResult()?4:5);
        logService.apply(LogApplyReq.GEN(req.getAccount(),"审批用户许可证工单 "+req.getAppkey()+" "+(req.isResult()?"通过":"打回")));
        return FlagResp.ON().MES("提交成功");
    }

    @Override
    public ApprovalLog transApprovalToLog(Approval approval) {
        ApprovalLog log=new ApprovalLog();
        log.setAppkey(approval.getAppkey());
        log.setType(approval.getType());
        log.setResult(approval.getResult()==1);
        log.setApply(approval.getApply());
        log.setAppday(approval.getAppday());
        log.setComday(approval.getComday());
        return log;
    }

    @Override
    public ApprovalLogListResp getLogList(String account) {
        LambdaQueryWrapper<Approval> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Approval::getApprove,account).eq(Approval::getFlag,1);
        List<Approval> list=approvalMapper.selectList(wrapper);
        if(list.isEmpty()){
            return new ApprovalLogListResp(false,0,null);
        }
        List<ApprovalLog> result=new ArrayList<>();
        for(Approval i:list){
            result.add(transApprovalToLog(i));
        }
        return new ApprovalLogListResp(true,result.size(),result);
    }

    @Override
    public ApprovalTypeLicense getLicenseApproval(String appkey) {
        License license=licenseMapper.selectOne(PrimaryWrapperHandler.getLicenseKeyQuery(appkey));
        return new ApprovalTypeLicense(license.getAccount(),
                license.getCompany(),
                license.getName(),
                license.getAuth(),
                appkey
        );
    }

    @Override
    public ApprovalTypeLicenseLog getLicenseApprovalLog(String appkey) {
        License license=licenseMapper.selectOne(PrimaryWrapperHandler.getLicenseKeyQuery(appkey));
        Approval approval=approvalMapper.selectOne(PrimaryWrapperHandler.getApprovalKeyQuery(appkey));
        return new ApprovalTypeLicenseLog(license.getAccount(),
                license.getCompany(),
                license.getName(),
                license.getAuth(),
                approval.getAppkey(),
                approval.getResult()==1,
                approval.getReview()
        );
    }

    @Override
    public FlagResp resetEntrustApproval(String appkey) {
        Approval approval=approvalMapper.selectOne(PrimaryWrapperHandler.getApprovalKeyQuery(appkey));
        if(approval.getFlag()==1){
            recordService.record(2);
            logService.apply(LogApplyReq.GEN(approval.getApply(),"申请装卸作业委托书 "+appkey));
        }
        approval.setFlag(0);
        approval.setResult(0);
        approval.setApprove(null);
        String daytime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        approval.setComday(null);
        approval.setAppday(daytime);
        approval.setReview(null);
        approvalMapper.update(approval,PrimaryWrapperHandler.getApprovalKeyUpdate(appkey));
        return FlagResp.ON().MES("提交成功");
    }

    @Override
    public FlagResp delEntrustApproval(String appkey) {
        approvalMapper.delete(PrimaryWrapperHandler.getApprovalKeyQuery(appkey));
        return FlagResp.ON().MES("删除成功");
    }

    @Override
    public ApprovalTypeEntrust getEntrustApproval(String appkey) {
        Approval approval=approvalMapper.selectOne(PrimaryWrapperHandler.getApprovalKeyQuery(appkey));
        EntrustInfo info=entrustService.getInfo(appkey);
        return new ApprovalTypeEntrust(approval.getApply(),
                appkey,
                info.getCraft(),
                info.getArrive(),
                info.getAuth(),
                info.getName(),
                info.getSignature(),
                info.getPack(),
                info.getValue(),
                info.getWeight(),
                info.getVolume()
        );
    }

    @Override
    public ApprovalTypeEntrustLog getEntrustApprovalLog(String appkey) {
        Approval approval=approvalMapper.selectOne(PrimaryWrapperHandler.getApprovalKeyQuery(appkey));
        EntrustInfo info=entrustService.getInfo(appkey);
        Goods goods=goodsService.getOneByKey(appkey);
        return new ApprovalTypeEntrustLog(approval.getApply(),
                appkey,
                info.getCraft(),
                info.getArrive(),
                info.getAuth(),
                info.getName(),
                info.getSignature(),
                info.getPack(),
                info.getValue(),
                info.getWeight(),
                info.getVolume(),
                goods.getRestore(),
                approval.getResult()==1,
                approval.getReview()
        );
    }

    @Override
    public FlagResp dealEntrustOne(ApprovalEntrustDealReq req) {
        Approval approval=approvalMapper.selectOne(PrimaryWrapperHandler.getApprovalKeyQuery(req.getAppkey()));
        if(!req.isResult()){
            approval.setResult(0);
            approval.setReview(req.getReview());
        }else{
            approval.setResult(1);
            EntrustInfo info=entrustService.getInfo(req.getAppkey());
            goodsService.restoreOne(new GoodsRestoreReq(req.getAppkey(),req.getRestore()));
            restoreService.restoreOccupy(new RestoreOccupyReq(req.getRestore(),info.getVolume()));
        }
        approval.setFlag(1);
        approval.setApprove(req.getAccount());
        LocalDateTime localDateTime= LocalDateTime.now();
        String dateKey=localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        approval.setComday(dateKey);
        approvalMapper.update(approval,PrimaryWrapperHandler.getApprovalKeyQuery(req.getAppkey()));
        recordService.record(req.isResult()?4:5);
        logService.apply(LogApplyReq.GEN(req.getAccount(),"审批装卸作业委托书工单 "+req.getAppkey()+" "+(req.isResult()?"通过":"打回")));
        return FlagResp.ON().MES("提交成功");
    }

    @Override
    public FlagResp resetOrderApproval(String appkey) {
        Approval approval=approvalMapper.selectOne(PrimaryWrapperHandler.getApprovalKeyQuery(appkey));
        if(approval.getFlag()==1){
            recordService.record(3);
            logService.apply(LogApplyReq.GEN(approval.getApply(),"申请发货指令 "+appkey));
        }
        approval.setFlag(0);
        approval.setResult(0);
        approval.setApprove(null);
        approval.setReview(null);
        LocalDateTime localDateTime=LocalDateTime.now();
        String date=localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        approval.setAppday(date);
        approval.setComday(null);
        approvalMapper.update(approval,PrimaryWrapperHandler.getApprovalKeyUpdate(appkey));
        return FlagResp.ON().MES("重置发货指令工单成功");
    }

    @Override
    public FlagResp delOrderApproval(String appkey) {
        approvalMapper.delete(PrimaryWrapperHandler.getApprovalKeyQuery(appkey));
        return FlagResp.ON().MES("删除发货指令工单成功");
    }

    @Override
    public ApprovalTypeOrder getOrderApproval(String appkey) {
        Order order=orderService.getOneByKey(appkey);
        return new ApprovalTypeOrder(appkey,
                order.getAccount(),
                order.getAgent(),
                order.getGoods(),
                order.getValue(),
                order.getType(),
                order.getCraft(),
                order.getWagon(),
                order.getName(),
                order.getPhone(),
                order.getArrive(),
                order.getAuth()
        );
    }

    @Override
    public ApprovalTypeOrderLog getOrderApprovalLog(String appkey) {
        Approval approval=getOneByKey(appkey);
        Order order=orderService.getOneByKey(appkey);
        return new ApprovalTypeOrderLog(appkey,
                order.getAccount(),
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
                approval.getResult()==1,
                approval.getReview()
        );
    }

    @Override
    public FlagResp dealOrderOne(ApprovalDealReq req) {
        Approval approval=approvalMapper.selectOne(PrimaryWrapperHandler.getApprovalKeyQuery(req.getAppkey()));
        approval.setFlag(1);
        approval.setResult(req.isResult()?1:0);
        approval.setReview(req.getReview());
        LocalDateTime localDateTime=LocalDateTime.now();
        String date=localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        approval.setComday(date);
        approvalMapper.update(approval,PrimaryWrapperHandler.getApprovalKeyUpdate(req.getAppkey()));
        recordService.record(req.isResult()?4:5);
        logService.apply(LogApplyReq.GEN(req.getAccount(),"审批发货指令工单 "+req.getAppkey()+" "+(req.isResult()?"通过":"打回")));
        return FlagResp.ON().MES("提交成功");
    }
}
