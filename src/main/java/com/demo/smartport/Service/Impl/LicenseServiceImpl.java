package com.demo.smartport.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.smartport.Entity.Approval.ApprovalHandleReq;
import com.demo.smartport.Entity.License.LicenseHandleReq;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.License.LicenseInfo;
import com.demo.smartport.Entity.LogAndRecord.LogApplyReq;
import com.demo.smartport.Service.*;
import com.demo.smartport.Table.Approval;
import com.demo.smartport.Table.License;
import com.demo.smartport.Mapper.LicenseMapper;
import com.demo.smartport.Mapper.UserMapper;
import com.demo.smartport.Table.User;
import com.demo.smartport.Util.Handler.ApprovalKeyHandler;
import com.demo.smartport.Util.Handler.PrimaryWrapperHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin
public class LicenseServiceImpl extends ServiceImpl<LicenseMapper, License> implements LicenseService {
    @Autowired
    private LicenseMapper licenseMapper;
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private LogService logService;

    @Override
    public FlagResp addLicense(LicenseHandleReq req) {
        if(licenseMapper.selectOne(PrimaryWrapperHandler.getLicenseAccountQuery(req.getAccount()))!=null){
            return FlagResp.OFF().MES("该用户已有许可证信息");
        }
        String key= ApprovalKeyHandler.getApprovalKey(License.class,req.getAccount());
        License license=null;
        if(req.getAuth()==null){
            license=new License(0,req.getAccount(),req.getCompany(),req.getName(),null,key);
        }else{
            license=new License(0,req.getAccount(),req.getCompany(),req.getName(),req.getAuth(),key);
        }
        licenseMapper.insert(license);
        license=licenseMapper.selectOne(PrimaryWrapperHandler.getLicenseAccountQuery(req.getAccount()));
        approvalService.addApproval(new ApprovalHandleReq(key,"LICENSE",license.getId(),license.getAccount()));
        User user=userMapper.selectOne(PrimaryWrapperHandler.getUserAccountQuery(license.getAccount()));
        user.setLicense(license.getId());
        userMapper.update(user,PrimaryWrapperHandler.getUserAccountUpdate(license.getAccount()));
        recordService.record(1);
        logService.apply(LogApplyReq.GEN(req.getAccount(),"申请用户许可证 "+key));
        return FlagResp.ON().MES("添加许可证成功");
    }

    @Override
    public FlagResp delLicense(String account) {
        License license=licenseMapper.selectOne(PrimaryWrapperHandler.getLicenseAccountQuery(account));
        if(approvalService.confirmStatus(license.getAppkey()).isFlag()){
            licenseMapper.delete(PrimaryWrapperHandler.getLicenseAccountQuery(account));
            approvalService.delLicenseApproval(account);
            userService.resetLicense(account);
            return FlagResp.ON().MES("删除成功");
        }
        return approvalService.confirmStatus(license.getAppkey());
    }

    @Override
    public FlagResp updateLicense(LicenseHandleReq req) {
        License license=licenseMapper.selectOne(PrimaryWrapperHandler.getLicenseAccountQuery(req.getAccount()));
        license.setCompany(req.getCompany());
        license.setName(req.getName());
        if(req.getAuth()==null){
            license.setAuth(null);
        }else{
            license.setAuth(req.getAuth());
        }
        if(approvalService.confirmStatus(license.getAppkey()).isFlag()){
            licenseMapper.update(license,PrimaryWrapperHandler.getLicenseAccountUpdate(req.getAccount()));
            approvalService.resetLicenseApproval(req.getAccount());
            return FlagResp.ON().MES("修改成功");
        }
        return approvalService.confirmStatus(license.getAppkey());
    }

    @Override
    public LicenseInfo getOneById(int id) {
        License license=licenseMapper.selectOne(PrimaryWrapperHandler.getLicenseIdQuery(id));
        Approval approval=approvalService.getOneByKey(license.getAppkey());
        return new LicenseInfo(license.getAccount(),
                license.getCompany(),
                license.getName(),
                license.getAuth(),
                license.getAppkey(),
                approval.getFlag()==1,
                approval.getResult()==1,
                approval.getApprove(),
                approval.getReview());
    }

    @Override
    public LicenseInfo getOneByAccount(String account) {
        License license=licenseMapper.selectOne(PrimaryWrapperHandler.getLicenseAccountQuery(account));
        Approval approval=approvalService.getOneByKey(license.getAppkey());
        return new LicenseInfo(license.getAccount(),
                license.getCompany(),
                license.getName(),
                license.getAuth(),
                license.getAppkey(),
                approval.getFlag()==1,
                approval.getResult()==1,
                approval.getApprove(),
                approval.getReview());
    }
}
