package com.demo.smartport.Controller;

import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.ShipAndCraft.CraftBatchReq;
import com.demo.smartport.Entity.ShipAndCraft.CraftHandleReq;
import com.demo.smartport.Entity.ShipAndCraft.CraftInfoPreResp;
import com.demo.smartport.Entity.ShipAndCraft.CraftInfoResp;
import com.demo.smartport.Service.CraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/craft")
public class CraftController {
    @Autowired
    private CraftService craftService;

    @PostMapping("/add")
    public FlagResp addCraft(@RequestBody CraftHandleReq req){
        return craftService.addCraft(req);
    }

    @GetMapping("/del")
    public FlagResp delCraft(@RequestParam String name){
        return craftService.delCraft(name);
    }

    @PostMapping("/confirm")
    public FlagResp confirmCraft(@RequestBody CraftHandleReq req){
        return craftService.confirmCraft(req);
    }

    @GetMapping("/get/account")
    public CraftInfoResp getCraftInfoByAccount(@RequestParam String account){
        return craftService.getCraftInfoList(account);
    }

    @GetMapping("/get/pre")
    public CraftInfoPreResp getCraftPre(@RequestParam String account){
        return craftService.getCraftPreUserConfirm(account);
    }


    @PostMapping("/update/batch")
    public FlagResp updateBatchStatus(@RequestBody CraftBatchReq req){
        return craftService.updateBatchStatus(req);
    }

    @PostMapping("/del/batch")
    public FlagResp delBatch(@RequestBody CraftBatchReq req){
        return craftService.delBatch(req);
    }

    @GetMapping("/get/entrust/account")
    public CraftInfoResp getEntrustApplyInfo(@RequestParam String account){
        return craftService.getEntrustApplyInfo(account);
    }
}
