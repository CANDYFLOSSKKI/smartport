package com.demo.smartport.Controller;

import com.demo.smartport.Entity.License.LicenseHandleReq;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.License.LicenseInfo;
import com.demo.smartport.Table.License;
import com.demo.smartport.Service.LicenseService;
import com.demo.smartport.Util.Handler.ImgUploadHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/license")
@CrossOrigin
public class LicenseController {
    @Autowired
    private LicenseService licenseService;
    @Autowired
    private ImgUploadHandler imgUploadHandler;

    @PostMapping("/add")
    public FlagResp addLicense(@RequestBody LicenseHandleReq req){
        return licenseService.addLicense(req);
    }

    @GetMapping("/del")
    public FlagResp deleteLicense(@RequestParam String account){
        return licenseService.delLicense(account);
    }

    @PostMapping("/update")
    public FlagResp updateLicense(@RequestBody LicenseHandleReq req){
        return licenseService.updateLicense(req);
    }

    @GetMapping("/get/id")
    public LicenseInfo getOneLicense(@RequestParam int id){
        return licenseService.getOneById(id);
    }

    @GetMapping("/get/account")
    public LicenseInfo getOneLicense(@RequestParam String account){
        return licenseService.getOneByAccount(account);
    }


    @PostMapping("/update/portrait")
    public FlagResp updateUserPortrait(@RequestParam MultipartFile file) throws Exception {
        return imgUploadHandler.uploadLicenseImg(file);
    }

}
