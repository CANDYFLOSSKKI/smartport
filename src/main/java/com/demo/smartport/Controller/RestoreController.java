package com.demo.smartport.Controller;

import com.demo.smartport.Entity.RestoreAndGoods.RestoreInfoListResp;
import com.demo.smartport.Service.RestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restore")
public class RestoreController {
    @Autowired
    private RestoreService restoreService;

    @GetMapping("/get/list")
    public RestoreInfoListResp getRestoreInfoList() {
        return restoreService.getInfoList();
    }
}
