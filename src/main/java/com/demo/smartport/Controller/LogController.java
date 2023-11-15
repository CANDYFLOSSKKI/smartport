package com.demo.smartport.Controller;

import com.demo.smartport.Entity.LogAndRecord.LogFullBundleResp;
import com.demo.smartport.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/get")
    public LogFullBundleResp getFullBundleLog(){
        return logService.getFullBundle();
    }


}
