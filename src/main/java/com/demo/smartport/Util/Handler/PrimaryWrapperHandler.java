package com.demo.smartport.Util.Handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.demo.smartport.Table.*;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
//生成对表结构中主键/唯一字段信息的查询(Query)和更新(Update)条件构造器
public class PrimaryWrapperHandler {
    //User类相关
    public static LambdaQueryWrapper<User> getUserIdQuery(int id){
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getId,id);
        return wrapper;
    }
    public static LambdaUpdateWrapper<User> getUserIdUpdate(int id){
        LambdaUpdateWrapper<User> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,id);
        return wrapper;
    }
    public static LambdaQueryWrapper<User> getUserAccountQuery(String account){
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount,account);
        return wrapper;
    }
    public static LambdaUpdateWrapper<User> getUserAccountUpdate(String account){
        LambdaUpdateWrapper<User> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(User::getAccount,account);
        return wrapper;
    }
    public static LambdaQueryWrapper<User> getUserComplexQuery(String account,String password){
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount,account);
        wrapper.eq(User::getPassword,password);
        return wrapper;
    }

    //Ship类相关
    public static LambdaQueryWrapper<Ship> getShipIdQuery(int id){
        LambdaQueryWrapper<Ship> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Ship::getId,id);
        return wrapper;
    }
    public static LambdaUpdateWrapper<Ship> getShipIdUpdate(int id){
        LambdaUpdateWrapper<Ship> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Ship::getId,id);
        return wrapper;
    }
    public static LambdaQueryWrapper<Ship> getShipNameQuery(String name){
        LambdaQueryWrapper<Ship> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Ship::getName,name);
        return wrapper;
    }
    public static LambdaUpdateWrapper<Ship> getShipNameUpdate(String name){
        LambdaUpdateWrapper<Ship> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Ship::getName,name);
        return wrapper;
    }

    //License类相关
    public static LambdaQueryWrapper<License> getLicenseIdQuery(int id){
        LambdaQueryWrapper<License> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(License::getId,id);
        return wrapper;
    }
    public static LambdaUpdateWrapper<License> getLicenseIdUpdate(int id){
        LambdaUpdateWrapper<License> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(License::getId,id);
        return wrapper;
    }
    public static LambdaQueryWrapper<License> getLicenseAccountQuery(String account){
        LambdaQueryWrapper<License> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(License::getAccount,account);
        return wrapper;
    }
    public static LambdaUpdateWrapper<License> getLicenseAccountUpdate(String account){
        LambdaUpdateWrapper<License> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(License::getAccount,account);
        return wrapper;
    }
    public static LambdaQueryWrapper<License> getLicenseKeyQuery(String key){
        LambdaQueryWrapper<License> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(License::getAppkey,key);
        return wrapper;
    }


    //Craft类相关
    public static LambdaQueryWrapper<Craft> getCraftIdQuery(int id){
        LambdaQueryWrapper<Craft> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Craft::getId,id);
        return wrapper;
    }
    public static LambdaQueryWrapper<Craft> getCraftAccountQuery(String account){
        LambdaQueryWrapper<Craft> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Craft::getAccount,account);
        return wrapper;
    }
    public static LambdaQueryWrapper<Craft> getCraftNameQuery(String name){
        LambdaQueryWrapper<Craft> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Craft::getName,name);
        return wrapper;
    }

    //Approval类相关
    public static LambdaQueryWrapper<Approval> getApprovalKeyQuery(String key){
        LambdaQueryWrapper<Approval> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Approval::getAppkey,key);
        return wrapper;
    }
    public static LambdaUpdateWrapper<Approval> getApprovalKeyUpdate(String key){
        LambdaUpdateWrapper<Approval> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Approval::getAppkey,key);
        return wrapper;
    }
    public static LambdaQueryWrapper<Approval> getApprovalLicenseQuery(String account){
        LambdaQueryWrapper<Approval> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Approval::getType,"LICENSE").eq(Approval::getApply,account);
        return wrapper;
    }
    public static LambdaUpdateWrapper<Approval> getApprovalLicenseUpdate(String account){
        LambdaUpdateWrapper<Approval> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Approval::getType,"LICENSE").eq(Approval::getApply,account);
        return wrapper;
    }

    //Entrust类相关
    public static LambdaQueryWrapper<Entrust> getEntrustAccountQuery(String account){
        LambdaQueryWrapper<Entrust> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Entrust::getAccount,account);
        return wrapper;
    }
    public static LambdaUpdateWrapper<Entrust> getEntrustAccountUpdate(String account){
        LambdaUpdateWrapper<Entrust> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Entrust::getAccount,account);
        return wrapper;
    }
    public static LambdaQueryWrapper<Entrust> getEntrustKeyQuery(String key){
        LambdaQueryWrapper<Entrust> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Entrust::getAppkey,key);
        return wrapper;
    }
    public static LambdaUpdateWrapper<Entrust> getEntrustKeyUpdate(String key){
        LambdaUpdateWrapper<Entrust> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Entrust::getAppkey,key);
        return wrapper;
    }


    //Goods类相关
    public static LambdaQueryWrapper<Goods> getGoodsAccountQuery(String account){
        LambdaQueryWrapper<Goods> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getAccount,account);
        return wrapper;
    }
    public static LambdaUpdateWrapper<Goods> getGoodsAccountUpdate(String account){
        LambdaUpdateWrapper<Goods> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Goods::getAccount,account);
        return wrapper;
    }
    public static LambdaQueryWrapper<Goods> getGoodsKeyQuery(String key){
        LambdaQueryWrapper<Goods> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getAppkey,key);
        return wrapper;
    }
    public static LambdaUpdateWrapper<Goods> getGoodsKeyUpdate(String key){
        LambdaUpdateWrapper<Goods> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Goods::getAppkey,key);
        return wrapper;
    }

    //Order类相关
    public static LambdaQueryWrapper<Order> getOrderAccountQuery(String account){
        LambdaQueryWrapper<Order> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Order::getAccount,account);
        return wrapper;
    }
    public static LambdaUpdateWrapper<Order> getOrderAccountUpdate(String account){
        LambdaUpdateWrapper<Order> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Order::getAccount,account);
        return wrapper;
    }
    public static LambdaQueryWrapper<Order> getOrderKeyQuery(String key){
        LambdaQueryWrapper<Order> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Order::getAppkey,key);
        return wrapper;
    }
    public static LambdaUpdateWrapper<Order> getOrderKeyUpdate(String key){
        LambdaUpdateWrapper<Order> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Order::getAppkey,key);
        return wrapper;
    }

    //Space类相关
    public static LambdaQueryWrapper<Space> getSpaceAccountQuery(String account){
        LambdaQueryWrapper<Space> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Space::getAccount,account);
        return wrapper;
    }
    public static LambdaUpdateWrapper<Space> getSpaceAccountUpdate(String account){
        LambdaUpdateWrapper<Space> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Space::getAccount,account);
        return wrapper;
    }
    public static LambdaQueryWrapper<Space> getSpaceKeyQuery(String key){
        LambdaQueryWrapper<Space> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Space::getAppkey,key);
        return wrapper;
    }
    public static LambdaUpdateWrapper<Space> getSpaceKeyUpdate(String key){
        LambdaUpdateWrapper<Space> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Space::getAppkey,key);
        return wrapper;
    }
}
