package com.demo.smartport.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.smartport.Entity.License.LicenseHandleReq;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.License.LicenseInfo;
import com.demo.smartport.Table.License;

public interface LicenseService extends IService<License> {

    //港口客户用户许可证新增
    public FlagResp addLicense(LicenseHandleReq req);

    //港口客户用户许可证删除
    public FlagResp delLicense(String account);

    //港口客户用户许可证更新
    public FlagResp updateLicense(LicenseHandleReq req);

    //单个港口客户用户许可证(id/用户名)
    public LicenseInfo getOneById(int id);
    public LicenseInfo getOneByAccount(String account);

}
