package com.demo.smartport.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Table.User;
import com.demo.smartport.Entity.UserAndEmployee.*;


public interface UserService extends IService<User> {

    //注册
    public FlagResp signUp(UserSignUpReq req);

    //登录
    public UserTokenResp logIn(UserLogInReq req);

    //登出
    //待补:用户登出逻辑
    public FlagResp logOut(String token);

    //修改密码
    //待补:成功修改密码后退出当前登录
    public FlagResp updatePass(UserUpdatePassReq req);

    //修改绑定邮箱
    public FlagResp bindEmail(UserBindEmailReq req);

    //修改个人信息
    public FlagResp bindInfor(UserBindPortraitReq req);

    //Token获取单个用户信息
    public UserInfoResp getOneByToken(String token);

    //表结构数据User转前端结构数据UserInfo
    public UserInfo transUserToInfo(User user);
    public int getLicenseStatus(User user);

    public FlagResp delUserEmail(UserHandleEmailReq req);

    public FlagResp resetLicense(String account);

    public UserSearchResp searchOne(String account);
}
