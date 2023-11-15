package com.demo.smartport.Controller;

import com.demo.smartport.Entity.Entrust.EntrustHandleReq;
import com.demo.smartport.Entity.Entrust.EntrustInfo;
import com.demo.smartport.Entity.Entrust.EntrustRowListResp;
import com.demo.smartport.Entity.Entrust.EntrustUpdateReq;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Service.EntrustService;
import com.demo.smartport.Util.Handler.ImgUploadHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/entrust")
public class EntrustController {
    @Autowired
    private EntrustService entrustService;
    @Autowired
    private ImgUploadHandler imgUploadHandler;

    @PostMapping("/update/portrait")
    public FlagResp updateApplyPortrait(@RequestParam MultipartFile file) throws Exception {
        return imgUploadHandler.uploadEntrustFile(file);
    }

    @PostMapping("/add")
    public FlagResp addEntrustApply(@RequestBody EntrustHandleReq req){
        return entrustService.addOne(req);
    }

    @GetMapping("/get/list")
    public EntrustRowListResp getEntrustList(@RequestParam String account){
        return entrustService.getList(account);
    }

    @GetMapping("/get/info")
    public EntrustInfo getEntrustInfo(@RequestParam String appkey){
        return entrustService.getInfo(appkey);
    }

    @PostMapping("/update")
    public FlagResp updateEntrust(@RequestBody EntrustUpdateReq req){
        return entrustService.updateOne(req);
    }

    @GetMapping("/del")
    public FlagResp delEntrust(@RequestParam String appkey){
        return entrustService.delOne(appkey);
    }

}
