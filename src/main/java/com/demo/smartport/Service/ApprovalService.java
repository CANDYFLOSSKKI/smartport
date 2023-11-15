package com.demo.smartport.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.smartport.Entity.Approval.*;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Table.Approval;
import org.springframework.web.bind.annotation.RequestParam;

public interface ApprovalService extends IService<Approval> {

    public Approval getOneByKey(String key);

    //添加审批工单
    public FlagResp addApproval(ApprovalHandleReq req);

    public FlagResp delLicenseApproval(String account);

    public FlagResp resetLicenseApproval(String account);


    public FlagResp confirmStatus(String appkey);

    public ApprovalInfo transApprovalToInfo(Approval approval);

    public ApprovalInfoListResp getKeyWordList(String key);

    public ApprovalInfoListResp getFreeList();

    public ApprovalInfoListResp getReservedList(String account);

    public FlagResp reserveOne(ApprovalReserveReq req);


    public FlagResp resetReserveOne(ApprovalReserveReq req);

    public FlagResp dealOne(ApprovalDealReq req);

    public ApprovalLog transApprovalToLog(Approval approval);

    public ApprovalLogListResp getLogList(String account);

    public ApprovalTypeLicense getLicenseApproval(String appkey);

    public ApprovalTypeLicenseLog getLicenseApprovalLog(String appkey);

    public FlagResp resetEntrustApproval(String appkey);

    public FlagResp delEntrustApproval(String appkey);

    public ApprovalTypeEntrust getEntrustApproval(String appkey);

    public ApprovalTypeEntrustLog getEntrustApprovalLog(String appkey);

    public FlagResp dealEntrustOne(ApprovalEntrustDealReq req);



    public FlagResp resetOrderApproval(String appkey);

    public FlagResp delOrderApproval(String appkey);

    public ApprovalTypeOrder getOrderApproval(String appkey);

    public ApprovalTypeOrderLog getOrderApprovalLog(String appkey);

    public FlagResp dealOrderOne(ApprovalDealReq req);
}
