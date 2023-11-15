package com.demo.smartport.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.MapCoordResp;
import com.demo.smartport.Entity.ShipAndCraft.*;
import com.demo.smartport.Table.Ship;
import com.demo.smartport.Mapper.ShipMapper;
import com.demo.smartport.Util.Handler.MapCoordHandler;
import com.demo.smartport.Util.Handler.PrimaryWrapperHandler;
import com.demo.smartport.Service.ShipService;
import com.demo.smartport.Util.Handler.StreamMethodsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@CrossOrigin
public class ShipServiceImpl extends ServiceImpl<ShipMapper, Ship> implements ShipService {
    @Autowired
    private ShipMapper shipMapper;
    @Autowired
    private MapCoordHandler mapCoordHandler;
    @Override
    public List<ShipInfo> getList() {
        List<Ship> list=shipMapper.selectList(null);
        List<ShipInfo> list_info=new ArrayList<>();
        for(Ship ship:list){
            list_info.add(transShipToInfo(ship));
        }
        return list_info;
    }
    @Override
    public ShipKeyWordListResp getKeyWordList(String key) {
        List<ShipKeyWordInfo> list=new ArrayList<>();
        List<Ship> result=new ArrayList<>();
        List<LambdaQueryWrapper<Ship>> wrapperList=new ArrayList<>();
        for(int i=0;i<4;++i){
            LambdaQueryWrapper<Ship> wrapper=new LambdaQueryWrapper<>();
            switch(i){
                case 0->{wrapper.like(Ship::getName,key).or().likeLeft(Ship::getName,key).or().likeRight(Ship::getName,key);}
                case 1->{wrapper.like(Ship::getLetter,key).or().likeLeft(Ship::getLetter,key).or().likeRight(Ship::getLetter,key);}
                case 2->{wrapper.like(Ship::getImo,key).or().likeLeft(Ship::getImo,key).or().likeRight(Ship::getImo,key);}
                case 3->{wrapper.like(Ship::getMmsi,key).or().likeLeft(Ship::getMmsi,key).or().likeRight(Ship::getMmsi,key);}
            }
            wrapperList.add(wrapper);
        }
        for(int i=0;i<4;++i){
            result.clear();
            result.addAll(shipMapper.selectList(wrapperList.get(i)));
            switch(i){
                case 0->{for(Ship ship:result){list.add(new ShipKeyWordInfo(ship.getName()+"----->(船名:"+ship.getName()+")",ship.getName()));}}
                case 1->{for(Ship ship:result){list.add(new ShipKeyWordInfo(ship.getName()+"----->(呼号:"+ship.getLetter()+")",ship.getName()));}}
                case 2->{for(Ship ship:result){list.add(new ShipKeyWordInfo(ship.getName()+"----->(IMO:"+ship.getImo()+")",ship.getName()));}}
                case 3->{for(Ship ship:result){list.add(new ShipKeyWordInfo(ship.getName()+"----->(MMSI:"+ship.getMmsi()+")",ship.getName()));}}
            }
        }
        if(list.isEmpty()){
            return new ShipKeyWordListResp(false,0,null);
        }
        list=list.stream().filter(StreamMethodsHandler.distinctByKey(ShipKeyWordInfo::getName)).limit(5).collect(Collectors.toList());
        return new ShipKeyWordListResp(true,list.size(),list);
    }

    @Override
    public ShipInfo getOneByName(String name) {
        return transShipToInfo(shipMapper.selectOne(PrimaryWrapperHandler.getShipNameQuery(name)));
    }

    @Override
    public ShipInfo getOneById(int id) {
        return transShipToInfo(shipMapper.selectOne(PrimaryWrapperHandler.getShipIdQuery(id)));
    }

    @Override
    public String nullValueProcess(String value) {
        return value==null ? "暂无" : value;
    }

    @Override
    public ShipInfo transShipToInfo(Ship ship) {
        ShipInfo info=new ShipInfo();
        info.setId(ship.getId());
        info.setName(ship.getName());
        info.setLetter(nullValueProcess(ship.getLetter()));
        info.setImo(nullValueProcess(ship.getImo()));
        info.setMmsi(nullValueProcess(ship.getMmsi()));
        info.setType(getShipType(ship.getType()));
        info.setLength(ship.getLength());
        info.setWidth(ship.getWidth());
        info.setDraft(ship.getDraft());
        info.setPortrait(ship.getPortrait());
        info.setStatus(getShipStatus(ship.getStatus()));
        info.setHead(ship.getHead());
        info.setTrack(ship.getTrack());
        info.setSpeed(ship.getSpeed());
        info.setArrive(nullValueProcess(ship.getArrive()));
        info.setRefresh(ship.getRefresh());
        info.setDestination(nullValueProcess(ship.getDestination()));
        if(Objects.equals(ship.getLongitude().toLowerCase(),ship.getLongitude())){
            info.setLatitude(Double.parseDouble(ship.getLatitude()));
            info.setLongitude(Double.parseDouble(ship.getLongitude()));
        }else{
            MapCoordResp coord=mapCoordHandler.getCoordResp(MapCoordHandler.getOriginCoord(ship.getLongitude(),ship.getLatitude()));
            info.setLongitude(coord.getX());
            info.setLatitude(coord.getY());
        }
        return info;
    }

    @Override
    public String getShipType(int i) {
        return switch(i){
            case 1->"散货船";
            case 2->"集装箱船";
            case 3->"滚装船";
            case 4->"载驳船";
            case 5->"冷藏船";
            case 6->"矿油船";
            case 7->"客货船";
            case 8->"特种船";
            default -> "暂无";
        };
    }

    @Override
    public String getShipStatus(int i) {
        return switch(i){
            case 1->"在港就绪";
            case 2->"在港作业";
            case 3->"离港";
            case 4->"维修";
            case 5->"故障";
            default -> "暂无";
        };
    }

    @Override
    public int getShipStatus(String status) {
         return switch(status){
            case "在港就绪"->1;
            case "在港作业"->2;
            case "离港"->3;
            case "维修"->4;
            case "故障"->5;
            default -> 0;
        };
    }

    @Override
    public FlagResp updateOneByName(ShipUpdateReq req) {
        Ship ship=shipMapper.selectOne(PrimaryWrapperHandler.getShipNameQuery(req.getName()));
        if(ship==null){
            return FlagResp.OFF().MES("未查找到该船舶信息");
        }
        ship.setHead(req.getHead());
        ship.setTrack(req.getTrack());
        ship.setSpeed(req.getSpeed());
        ship.setLatitude(String.valueOf(req.getLatitude()));
        ship.setLongitude(String.valueOf(req.getLongitude()));
        ship.setDestination(req.getDestination());
        ship.setArrive(req.getArrive());
        ship.setStatus(req.getStatus());
        LocalDateTime localDateTime=LocalDateTime.now();
        String refresh=localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ship.setRefresh(refresh);
        shipMapper.update(ship,PrimaryWrapperHandler.getShipNameUpdate(ship.getName()));
        return FlagResp.ON().MES("修改成功");
    }

    @Override
    public FlagResp updateStatus(ShipStatusReq req) {
        Ship ship=shipMapper.selectOne(PrimaryWrapperHandler.getShipNameQuery(req.getName()));
        ship.setStatus(getShipStatus(req.getStatus()));
        shipMapper.update(ship,PrimaryWrapperHandler.getShipNameUpdate(ship.getName()));
        return FlagResp.ON();
    }
}
