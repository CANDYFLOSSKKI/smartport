package com.demo.smartport.Controller;

import com.demo.smartport.Util.Handler.EmailAuthCodeHandler;
import com.demo.smartport.Util.Handler.ImgUploadHandler;
import com.demo.smartport.Entity.UserAndEmployee.*;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ImgUploadHandler imgUploadHandler;
    @Autowired
    private EmailAuthCodeHandler emailAuthCodeHandler;

    @PostMapping("/signup")
    public FlagResp signUpUser(@RequestBody UserSignUpReq req){
        return userService.signUp(req);
    }

    @PostMapping("/login")
    public UserTokenResp logInUser(@RequestBody UserLogInReq req){
        return userService.logIn(req);
    }

    @GetMapping("/info")
    public UserInfoResp getUserByToken(@RequestParam String token){
        return userService.getOneByToken(token);
    }

    @GetMapping("/logout")
    public FlagResp logOutUser(@RequestParam String token){
        return userService.logOut(token);
    }

    @PostMapping("/update/pass")
    public FlagResp updateUserPass(@RequestBody UserUpdatePassReq req){
        return userService.updatePass(req);
    }

    @PostMapping("/update/portrait")
    public FlagResp updateUserPortrait(@RequestParam MultipartFile file) throws Exception {
        return imgUploadHandler.uploadUserImg(file);
    }

    @PostMapping("/update/basic")
    public FlagResp updateUserBasicInfo(@RequestBody UserBindPortraitReq req){
        return userService.bindInfor(req);
    }

    @PostMapping("/bind/email")
    public FlagResp bindEmail(@RequestBody UserBindEmailReq req){
        return userService.bindEmail(req);
    }

    @PostMapping("/send/email")
    public FlagResp sendVerifyEmail(@RequestBody UserBindEmailReq req){
        return emailAuthCodeHandler.sendEmail(EmailAuthCodeHandler.getEmail(req));
    }

    @PostMapping("/confirm/email")
    public FlagResp confirmBindEmail(@RequestBody UserHandleEmailReq req){
        return emailAuthCodeHandler.sendEmail(req.getEmail());
    }

    @PostMapping("/del/email")
    public FlagResp delEmail(@RequestBody UserHandleEmailReq req){
        return userService.delUserEmail(req);
    }

    @GetMapping("/search")
    public UserSearchResp searchOne(@RequestParam String account){
        return userService.searchOne(account);
    }
}
