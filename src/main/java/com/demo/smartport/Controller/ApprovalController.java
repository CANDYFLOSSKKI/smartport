package com.demo.smartport.Controller;

import com.demo.smartport.Entity.Approval.*;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/approval")
public class ApprovalController {
    @Autowired
    private ApprovalService approvalService;

    @GetMapping("/confirm")
    public FlagResp confirmStatus(@RequestParam String appkey){
        return approvalService.confirmStatus(appkey);
    }

    @GetMapping("/get/key")
    public ApprovalInfoListResp getKeyWordList(@RequestParam String key){
        return approvalService.getKeyWordList(key);
    }

    @GetMapping("/get/free")
    public ApprovalInfoListResp getFreeList(){
        return approvalService.getFreeList();
    }

    @GetMapping("/get/reserved")
    public ApprovalInfoListResp getReservedList(@RequestParam String account){
        return approvalService.getReservedList(account);
    }

    @PostMapping("/reserve")
    public FlagResp reserveOne(@RequestBody ApprovalReserveReq req){
        return approvalService.reserveOne(req);
    }

    @PostMapping("/reserve/reset")
    public FlagResp resetReserveOne(@RequestBody ApprovalReserveReq req){
        return approvalService.resetReserveOne(req);
    }

    @PostMapping("/deal")
    public FlagResp dealOne(@RequestBody ApprovalDealReq req){
        return approvalService.dealOne(req);
    }

    @GetMapping("/get/log")
    public ApprovalLogListResp getLogList(@RequestParam String account){
        return approvalService.getLogList(account);
    }

    @GetMapping("/get/license")
    public ApprovalTypeLicense getLicenseApproval(@RequestParam String appkey){
        return approvalService.getLicenseApproval(appkey);
    }

    @GetMapping("/get/license/log")
    public ApprovalTypeLicenseLog getLicenseApprovalLog(@RequestParam String appkey){
        return approvalService.getLicenseApprovalLog(appkey);
    }

    @GetMapping("/get/entrust")
    public ApprovalTypeEntrust getEntrustApproval(@RequestParam String appkey){
        return approvalService.getEntrustApproval(appkey);
    }

    @GetMapping("/get/entrust/log")
    public ApprovalTypeEntrustLog getEntrustApprovalLog(@RequestParam String appkey){
        return approvalService.getEntrustApprovalLog(appkey);
    }

    @PostMapping("/deal/entrust")
    public FlagResp dealEntrustApproval(@RequestBody ApprovalEntrustDealReq req){
        return approvalService.dealEntrustOne(req);
    }

    @GetMapping("/get/order")
    public ApprovalTypeOrder getOrderApproval(@RequestParam String appkey){
        return approvalService.getOrderApproval(appkey);
    }

    @GetMapping("/get/order/log")
    public ApprovalTypeOrderLog getOrderApprovalLog(@RequestParam String appkey) {
        return approvalService.getOrderApprovalLog(appkey);
    }

    @PostMapping("/deal/order")
    public FlagResp dealOrderApproval(@RequestBody ApprovalDealReq req) {
        return approvalService.dealOrderOne(req);
    }

}
