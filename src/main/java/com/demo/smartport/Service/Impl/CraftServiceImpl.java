package com.demo.smartport.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.LogAndRecord.LogApplyReq;
import com.demo.smartport.Entity.ShipAndCraft.*;
import com.demo.smartport.Mapper.CraftMapper;
import com.demo.smartport.Mapper.UserMapper;
import com.demo.smartport.Service.CraftService;
import com.demo.smartport.Service.LogService;
import com.demo.smartport.Service.ShipService;
import com.demo.smartport.Service.UserService;
import com.demo.smartport.Table.Craft;
import com.demo.smartport.Table.Ship;
import com.demo.smartport.Table.User;
import com.demo.smartport.Util.Handler.PrimaryWrapperHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.mail.Flags;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@CrossOrigin
public class CraftServiceImpl extends ServiceImpl<CraftMapper, Craft> implements CraftService {
    @Autowired
    private CraftMapper craftMapper;
    @Autowired
    private ShipService shipService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LogService logService;

    @Override
    public FlagResp addCraft(CraftHandleReq req) {
        Craft craft=craftMapper.selectOne(PrimaryWrapperHandler.getCraftNameQuery(req.getName()));
        if(craft!=null){
            if(Objects.equals(craft.getAccount(),req.getAccount())){
                return FlagResp.OFF().MES("不能重复绑定");
            }else{
                return FlagResp.OFF().MES("该船舶已被其他用户绑定");
            }
        }
        craftMapper.insert(new Craft(0,req.getAccount(),req.getName()));
        logService.apply(LogApplyReq.GEN(req.getAccount(),"绑定船舶 "+req.getName()));
        return FlagResp.ON().MES("绑定成功");
    }

    @Override
    public FlagResp delCraft(String name) {
        if(craftMapper.selectOne(PrimaryWrapperHandler.getCraftNameQuery(name))==null){
            return FlagResp.OFF().MES("未找到绑定信息");
        }
        Craft craft=craftMapper.selectOne(PrimaryWrapperHandler.getCraftNameQuery(name));
        logService.apply(LogApplyReq.GEN(craft.getAccount(),"解绑船舶 "+name));
        craftMapper.delete(PrimaryWrapperHandler.getCraftNameQuery(name));
        return FlagResp.ON().MES("解绑成功");
    }

    @Override
    public FlagResp confirmCraft(CraftHandleReq req) {
        Craft craft=craftMapper.selectOne(PrimaryWrapperHandler.getCraftNameQuery(req.getName()));
        if(craft==null){
            return FlagResp.ON().MES("该船舶还未被绑定,您可以将其加入至绑定列表中");
        }else{
            if(Objects.equals(craft.getAccount(),req.getAccount())){
                return FlagResp.OFF().MES("该船舶已被您绑定,您可至<已绑定列表>中对其操作");
            }
        }
        return FlagResp.OFF().MES("该船舶已被用户<"+craft.getAccount()+">绑定,您暂不能对其进行操作");
    }

    @Override
    public CraftInfoResp getCraftInfoList(String account) {
        List<CraftInfo> infoList=new ArrayList<>();
        List<Craft> craftList=craftMapper.selectList(PrimaryWrapperHandler.getCraftAccountQuery(account));
        if(!craftList.isEmpty()){
            for(Craft craft:craftList) {
                ShipInfo info = shipService.getOneByName(craft.getName());
                infoList.add(new CraftInfo(info.getName(), info.getLetter(), info.getImo(), info.getMmsi(), info.getType(), info.getStatus(), info.getPortrait()));
            }
        }
        if(infoList.isEmpty()){
            return new CraftInfoResp(false,0,null);
        }
        return new CraftInfoResp(true,infoList.size(),infoList);
    }

    @Override
    public CraftInfoPreResp getCraftPreUserConfirm(String account) {
        User user=userMapper.selectOne(PrimaryWrapperHandler.getUserAccountQuery(account));
        if(user==null){
            return new CraftInfoPreResp(false,null,null);
        }
        return new CraftInfoPreResp(true,user.getAccount(),user.getName());
    }

    @Override
    public FlagResp updateBatchStatus(CraftBatchReq req) {
        for(String name: req.getName()){
            shipService.updateStatus(new ShipStatusReq(name,req.getStatus()));
        }
        return FlagResp.ON().MES("修改成功");
    }

    @Override
    public FlagResp delBatch(CraftBatchReq req) {
        for(String name: req.getName()){
            delCraft(name);
        }
        return FlagResp.ON().MES("修改成功");
    }

    @Override
    public CraftInfoResp getEntrustApplyInfo(String account) {
        List<CraftInfo> result=new ArrayList<>();
        List<Craft> list=craftMapper.selectList(PrimaryWrapperHandler.getCraftAccountQuery(account));
        if(list.isEmpty()){
            return new CraftInfoResp(false,0,null);
        }
        for(Craft i:list){
            ShipInfo info=shipService.getOneByName(i.getName());
            if((!Objects.equals(info.getStatus(),"维修"))&&(!Objects.equals(info.getStatus(),"故障"))){
                result.add(new CraftInfo(i.getName(),info.getLetter(),info.getImo(),info.getMmsi(),info.getType(),info.getStatus(),info.getPortrait()));
            }
        }
        if(result.isEmpty()){
            return new CraftInfoResp(false,0,null);
        }
        return new CraftInfoResp(true,result.size(),result);
    }
}
