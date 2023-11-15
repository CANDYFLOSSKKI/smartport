package com.demo.smartport.Service.Impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.smartport.Entity.LogAndRecord.LogApplyReq;
import com.demo.smartport.Service.ApprovalService;
import com.demo.smartport.Service.LogService;
import com.demo.smartport.Table.Approval;
import com.demo.smartport.Util.Handler.EmailAuthCodeHandler;
import com.demo.smartport.Util.Handler.TokenCodeHandler;
import com.demo.smartport.Util.Handler.PrimaryWrapperHandler;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Table.License;
import com.demo.smartport.Table.User;
import com.demo.smartport.Entity.UserAndEmployee.*;
import com.demo.smartport.Mapper.LicenseMapper;
import com.demo.smartport.Mapper.UserMapper;
import com.demo.smartport.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Objects;

@Service
@CrossOrigin
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LicenseMapper licenseMapper;
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private LogService logService;

    @Override
    public FlagResp signUp(UserSignUpReq req) {
        if(userMapper.selectOne(PrimaryWrapperHandler.getUserAccountQuery(req.getAccount()))!=null){
            return FlagResp.OFF().MES("该用户已注册,请检查用户名或登录");
        }
        switch(req.getGrade()){
            case 1->{
                userMapper.insert(new User(0,
                        req.getAccount(),
                        req.getPassword(),
                        1,
                        req.getAccount(),
                        null,
                        "https://github.com/CANDYFLOSSKKI/smartport_photo/raw/master/img/user/DEFAULT.jpg",
                        0
                ));
            }
            case 2->{
                licenseMapper.insert(new License(0,
                        req.getAccount(),
                        "智慧港口",
                        req.getEmployee().getName(),
                        "https://github.com/CANDYFLOSSKKI/smartport_photo/raw/master/img/license/DEFAULT.jpg",
                        null
                ));
                License license=licenseMapper.selectOne(PrimaryWrapperHandler.getLicenseAccountQuery(req.getAccount()));
                userMapper.insert(new User(0,
                        req.getAccount(),
                        req.getPassword(),
                        2,
                        req.getAccount(),
                        null,
                        "https://github.com/CANDYFLOSSKKI/smartport_photo/raw/master/img/user/DEFAULT.jpg",
                        license.getId()
                ));
            }
        }
        logService.apply(LogApplyReq.GEN(req.getAccount(),"用户注册"));
        return FlagResp.ON();
    }

    @Override
    public UserTokenResp logIn(UserLogInReq req) {
        User user=null;
        user=userMapper.selectOne(PrimaryWrapperHandler.getUserComplexQuery(req.getAccount(),req.getPassword()));
        if(user==null){
            LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(User::getEmail,req.getAccount());
            user=userMapper.selectOne(wrapper);
            if(user==null){
                return UserTokenResp.OFF();
            }else{
                req.setAccount(user.getAccount());
            }
        }
        String token= TokenCodeHandler.getToken(req);
        logService.apply(LogApplyReq.GEN(req.getAccount(),"用户登录"));
        return UserTokenResp.ON().TOKEN(token);
    }

    @Override
    public UserInfoResp getOneByToken(String token) {
        if(token.length()<5){
            return UserInfoResp.OFF();
        }
        DecodedJWT jwt= TokenCodeHandler.verifyToken(token);
        String account=jwt.getClaim("account").asString();
        if(account==null){
            return UserInfoResp.OFF();
        }
        User user=userMapper.selectOne(PrimaryWrapperHandler.getUserAccountQuery(account));
        return UserInfoResp.ON().INFO(transUserToInfo(user));
    }

    @Override
    public UserInfo transUserToInfo(User user) {
        UserInfo info=new UserInfo();
        info.setId(user.getId());
        info.setAccount(user.getAccount());
        info.setPassword(user.getPassword());
        info.setGrade(user.getGrade());
        info.setName(user.getName());
        info.setEmail(user.getEmail());
        info.setPortrait(user.getPortrait());
        info.setEnable(getLicenseStatus(user));
        return info;
    }

    @Override
    public FlagResp logOut(String token) {
        DecodedJWT jwt= TokenCodeHandler.verifyToken(token);
        String account=jwt.getClaim("account").asString();
        if(account==null){
            return FlagResp.OFF();
        }
        logService.apply(LogApplyReq.GEN(account,"用户登出"));
        return FlagResp.ON();
    }

    @Override
    public FlagResp updatePass(UserUpdatePassReq req) {
        if(req.getAfter().length()<3){
            return FlagResp.OFF().MES("新密码长度过短");
        }
        User user=userMapper.selectOne(PrimaryWrapperHandler.getUserComplexQuery(req.getAccount(),req.getFore()));
        if(user==null){
            return FlagResp.OFF().MES("未找到该用户");
        }
        user.setPassword(req.getAfter());
        userMapper.update(user, PrimaryWrapperHandler.getUserAccountUpdate(user.getAccount()));
        return FlagResp.ON().MES("修改成功");
    }

    @Override
    public FlagResp bindEmail(UserBindEmailReq req) {
        String email= EmailAuthCodeHandler.getEmail(req);
        User user=userMapper.selectOne(PrimaryWrapperHandler.getUserAccountQuery(req.getAccount()));
        if(user==null){
            return FlagResp.OFF().MES("绑定失败");
        }
        user.setEmail(email);
        userMapper.update(user, PrimaryWrapperHandler.getUserAccountUpdate(user.getAccount()));
        return FlagResp.ON().MES("绑定成功");
    }

    @Override
    public FlagResp bindInfor(UserBindPortraitReq req) {
        if(req.getName().length()<3){
            return FlagResp.OFF().MES("新昵称长度过短");
        }
        System.out.println(req.getPortrait());
        User user=userMapper.selectOne(PrimaryWrapperHandler.getUserAccountQuery(req.getAccount()));
        if(user==null){
            return FlagResp.OFF().MES("修改失败");
        }
        user.setName(req.getName());
        if(!Objects.equals(req.getPortrait(), user.getPortrait())){
            user.setPortrait(req.getPortrait());
        }
        userMapper.update(user, PrimaryWrapperHandler.getUserAccountUpdate(user.getAccount()));
        return FlagResp.ON().MES("修改成功");
    }

    @Override
    public FlagResp delUserEmail(UserHandleEmailReq req) {
        User user=userMapper.selectOne(PrimaryWrapperHandler.getUserAccountQuery(req.getAccount()));
        if(user==null||!Objects.equals(user.getEmail(),req.getEmail())){
            return FlagResp.OFF().MES("解除绑定失败");
        }
        user.setEmail(null);
        userMapper.update(user, PrimaryWrapperHandler.getUserAccountUpdate(req.getAccount()));
        return FlagResp.ON().MES("解除绑定成功");
    }

    @Override
    public int getLicenseStatus(User user) {
        if(user.getLicense()==0){
            return 0;
        }
        License license=licenseMapper.selectOne(PrimaryWrapperHandler.getLicenseIdQuery(user.getLicense()));
        String licenseKey=license.getAppkey();
        if(licenseKey==null){
            return 4;
        }
        Approval approval=approvalService.getOneByKey(licenseKey);
        if(approval.getFlag()==0){
            if(approval.getApprove()!=null){
                return 2;
            }
            return 1;
        }
        return approval.getResult()+3;
    }

    @Override
    public FlagResp resetLicense(String account) {
        User user=userMapper.selectOne(PrimaryWrapperHandler.getUserAccountQuery(account));
        user.setLicense(0);
        userMapper.update(user,PrimaryWrapperHandler.getUserAccountUpdate(account));
        return FlagResp.ON();
    }

    @Override
    public UserSearchResp searchOne(String account) {
        User user=userMapper.selectOne(PrimaryWrapperHandler.getUserAccountQuery(account));
        if(user==null){
            return new UserSearchResp(false,0);
        }
        return new UserSearchResp(true,user.getGrade());
    }
}
