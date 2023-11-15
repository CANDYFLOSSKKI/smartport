package com.demo.smartport.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.smartport.Entity.Entrust.EntrustHandleReq;
import com.demo.smartport.Entity.Entrust.EntrustUpdateReq;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.RestoreAndGoods.GoodsRestoreReq;
import com.demo.smartport.Mapper.GoodsMapper;
import com.demo.smartport.Service.GoodsService;
import com.demo.smartport.Service.SpaceService;
import com.demo.smartport.Table.Goods;
import com.demo.smartport.Util.Handler.PrimaryWrapperHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Service
@CrossOrigin
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private SpaceService spaceService;

    @Override
    public FlagResp addOne(EntrustHandleReq req,String appkey) {
        goodsMapper.insert(new Goods(0,
                req.getAccount(),
                appkey,
                req.getGoods().getName(),
                req.getGoods().getSignature(),
                req.getGoods().getPack(),
                req.getGoods().getValue(),
                req.getGoods().getWeight(),
                req.getGoods().getVolume(),
                0
        ));
        Goods good=goodsMapper.selectOne(PrimaryWrapperHandler.getGoodsKeyQuery(appkey));
        if(good==null){
            return FlagResp.OFF();
        }
        return FlagResp.ON();
    }

    @Override
    public Goods getOneByKey(String appkey) {
        return goodsMapper.selectOne(PrimaryWrapperHandler.getGoodsKeyQuery(appkey));
    }

    @Override
    public FlagResp updateOne(EntrustUpdateReq req) {
        Goods good=goodsMapper.selectOne(PrimaryWrapperHandler.getGoodsKeyQuery(req.getAppkey()));
        good.setName(req.getName());
        good.setSignature(req.getSignature());
        good.setPack(req.getPack());
        good.setValue(req.getValue());
        good.setWeight(req.getWeight());
        good.setVolume(req.getVolume());
        goodsMapper.update(good,PrimaryWrapperHandler.getGoodsKeyUpdate(req.getAppkey()));
        return FlagResp.ON().MES("提交成功");
    }

    @Override
    public FlagResp delOne(String appkey) {
        goodsMapper.delete(PrimaryWrapperHandler.getGoodsKeyQuery(appkey));
        return FlagResp.ON().MES("删除成功");
    }

    @Override
    public FlagResp restoreOne(GoodsRestoreReq req) {
        Goods good=goodsMapper.selectOne(PrimaryWrapperHandler.getGoodsKeyQuery(req.getAppkey()));
        good.setRestore(req.getRestore());
        goodsMapper.update(good,PrimaryWrapperHandler.getGoodsKeyUpdate(req.getAppkey()));
        spaceService.addGoodsToSpace(good);
        return FlagResp.ON().MES("提交成功");
    }
}
