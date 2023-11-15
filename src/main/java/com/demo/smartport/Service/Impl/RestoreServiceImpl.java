package com.demo.smartport.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.RestoreAndGoods.RestoreInfo;
import com.demo.smartport.Entity.RestoreAndGoods.RestoreInfoListResp;
import com.demo.smartport.Entity.RestoreAndGoods.RestoreOccupyReq;
import com.demo.smartport.Mapper.RestoreMapper;
import com.demo.smartport.Service.RestoreService;
import com.demo.smartport.Table.Restore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Service
@CrossOrigin
public class RestoreServiceImpl extends ServiceImpl<RestoreMapper, Restore> implements RestoreService {
    @Autowired
    private RestoreMapper restoreMapper;

    @Override
    public RestoreInfoListResp getInfoList() {
        List<Restore> list=restoreMapper.selectList(null);
        List<RestoreInfo> result=new ArrayList<>();
        for(Restore i:list) {
            result.add(new RestoreInfo(i.getId(),
                    i.getId()+"号堆场 余"+i.getVleft()+"m³",
                    i.getVleft()
            ));
        }
        return new RestoreInfoListResp(true,result.size(),result);
    }

    @Override
    public FlagResp restoreOccupy(RestoreOccupyReq req) {
        Restore restore=restoreMapper.selectById(req.getId());
        restore.setVleft(restore.getVolume()-req.getValue());
        restoreMapper.updateById(restore);
        return FlagResp.ON().MES("提交成功");
    }

    @Override
    public FlagResp restoreBack(int id, double value) {
        Restore restore=restoreMapper.selectById(id);
        restore.setVleft(restore.getVleft()+value);
        restoreMapper.updateById(restore);
        return FlagResp.ON().MES("提交成功");
    }

    @Override
    public List<Restore> getList() {
        return restoreMapper.selectList(null);
    }
}
