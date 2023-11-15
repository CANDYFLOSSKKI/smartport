package com.demo.smartport.Controller;

import com.demo.smartport.Entity.Space.SpaceInfo;
import com.demo.smartport.Entity.Space.SpaceInfoListResp;
import com.demo.smartport.Entity.Space.SpaceOriginReq;
import com.demo.smartport.Service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/space")
public class SpaceController {
    @Autowired
    private SpaceService spaceService;

    @GetMapping("/get/list")
    public SpaceInfoListResp getSpaceInfoList(@RequestParam String account){
        return spaceService.getList(account);
    }

    @PostMapping("/get/list/update")
    public SpaceInfoListResp getSpaceInfoUpdate(@RequestBody SpaceOriginReq req){
        return spaceService.getUpdateList(req);
    }

    @GetMapping("/get/info")
    public SpaceInfo getSpaceInfo(@RequestParam String appkey){
        return spaceService.getSpaceInfo(appkey);
    }
}
