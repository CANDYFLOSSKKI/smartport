package com.demo.smartport.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.Space.SpaceInfo;
import com.demo.smartport.Entity.Space.SpaceInfoListResp;
import com.demo.smartport.Entity.Space.SpaceOriginReq;
import com.demo.smartport.Mapper.SpaceMapper;
import com.demo.smartport.Service.RestoreService;
import com.demo.smartport.Service.SpaceService;
import com.demo.smartport.Table.Goods;
import com.demo.smartport.Table.Space;
import com.demo.smartport.Util.Handler.PrimaryWrapperHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SpaceServiceImpl extends ServiceImpl<SpaceMapper, Space> implements SpaceService {
    @Autowired
    private SpaceMapper spaceMapper;
    @Autowired
    private RestoreService restoreService;

    @Override
    public FlagResp addGoodsToSpace(Goods goods) {
        spaceMapper.insert(new Space(0,
                goods.getAppkey(),
                goods.getAccount(),
                goods.getRestore(),
                goods.getName(),
                goods.getSignature(),
                goods.getPack(),
                goods.getValue(),
                goods.getWeight(),
                goods.getVolume(),
                0.0
        ));
        return FlagResp.ON().MES("提交成功");
    }

    @Override
    public SpaceInfoListResp getList(String account) {
        LambdaQueryWrapper<Space> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Space::getAccount,account).gt(Space::getValue,0.0);
        List<Space> list=spaceMapper.selectList(wrapper);
        if(list.isEmpty()){
            return new SpaceInfoListResp(false,0,null);
        }
        List<SpaceInfo> result=new ArrayList<>();
        for(Space i:list){
            result.add(new SpaceInfo(i.getAppkey(),
                    i.getName(),
                    i.getSignature()==null?"暂无":i.getSignature(),
                    i.getPack()==null?"暂无":i.getPack(),
                    i.getValue(),
                    i.getWeight(),
                    i.getVolume(),
                    i.getRestore(),
                    i.getProcess()
            ));
        }
        return new SpaceInfoListResp(true,result.size(),result);
    }

    @Override
    public SpaceInfoListResp getUpdateList(SpaceOriginReq req) {
        SpaceInfoListResp resp=getList(req.getAccount());
        if(!resp.isFlag()){
            return resp;
        }
        List<SpaceInfo> list=resp.getList();
        for(int i=0;i<list.size();++i){
            SpaceInfo info=list.get(i);
            if(Objects.equals(info.getAppkey(),req.getAppkey())){
                list.remove(i);
                info.setProcess(info.getProcess()-req.getValue());
                list.add(info);
                return new SpaceInfoListResp(true,list.size(),list);
            }
        }
        return resp;
    }

    @Override
    public Space getOneByKey(String key) {
        return spaceMapper.selectOne(PrimaryWrapperHandler.getSpaceKeyQuery(key));
    }

    @Override
    public FlagResp reserveOrder(String appkey, double value) {
        Space space=getOneByKey(appkey);
        space.setProcess(space.getProcess()+value);
        spaceMapper.update(space, PrimaryWrapperHandler.getSpaceKeyUpdate(appkey));
        return FlagResp.ON().MES("提交成功");
    }

    @Override
    public FlagResp resetOrder(String appkey, double value) {
        Space space=getOneByKey(appkey);
        space.setProcess((space.getProcess()-value)<0?0:(space.getProcess()-value));
        spaceMapper.update(space,PrimaryWrapperHandler.getSpaceKeyUpdate(appkey));
        return FlagResp.ON().MES("提交成功");
    }

    @Override
    public FlagResp processOrder(String appkey, double value) {
        Space space=getOneByKey(appkey);
        double rates=(space.getValue()-value)/space.getValue();
        space.setProcess(space.getProcess()-value);
        if(!((space.getValue()-value)>0)){
            restoreService.restoreBack(space.getRestore(),space.getVolume());
            space.setValue(0.0);
            space.setWeight(0.0);
            space.setVolume(0.0);
            space.setProcess(0.0);
            spaceMapper.updateById(space);
            return FlagResp.ON().MES("提交成功");
        }
        restoreService.restoreBack(space.getRestore(),space.getVolume()*(1-rates));
        space.setValue(space.getValue()-value);
        space.setWeight(space.getWeight()*rates);
        space.setVolume(space.getVolume()*rates);
        spaceMapper.update(space,PrimaryWrapperHandler.getSpaceKeyUpdate(appkey));
        return FlagResp.ON().MES("提交成功");
    }

    @Override
    public boolean preOrderExamine(String appkey, double value, int sign, double origin) {
        Space space=getOneByKey(appkey);
        switch(sign) {
            case 1->{ return space.getValue()>=space.getProcess()+value; }
            case 2->{ return space.getValue()>=space.getProcess()-origin+value; }
            default->{ return false; }
        }
    }

    @Override
    public SpaceInfo getSpaceInfo(String appkey) {
        Space space=getOneByKey(appkey);
        return new SpaceInfo(space.getAppkey(),
                    space.getName(),
                    space.getSignature()==null?"暂无":space.getSignature(),
                    space.getPack()==null?"暂无":space.getPack(),
                    space.getValue(),
                    space.getWeight(),
                    space.getVolume(),
                    space.getRestore(),
                    space.getProcess()
        );
    }
}
