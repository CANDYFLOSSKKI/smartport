package com.demo.smartport.Controller;

import com.demo.smartport.Entity.FlagResp;
import com.demo.smartport.Entity.ShipAndCraft.*;
import com.demo.smartport.Table.Ship;
import com.demo.smartport.Service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ship")
@CrossOrigin
public class ShipController {
    @Autowired
    public ShipService shipService;

    @GetMapping("/get/all")
    public ShipInfoListResp getAllShip(){
        List<ShipInfo> list=shipService.getList();
        if(list.size()==0){
            return new ShipInfoListResp(false,0,null);
        }
        return new ShipInfoListResp(true,list.size(),list);
    }

    @GetMapping("/get/key")
    public ShipKeyWordListResp getKeyShip(@RequestParam String key){
        return shipService.getKeyWordList(key);
    }

    @GetMapping("/get/name")
    public ShipInfo getOneShip(@RequestParam String name){
        return shipService.getOneByName(name);
    }

    @GetMapping("/get/id")
    public ShipInfo getOneShip(@RequestParam int id) {
        return shipService.getOneById(id);
    }

    @PostMapping("/update")
    public FlagResp updateOneByName(@RequestBody ShipUpdateReq req){
        return shipService.updateOneByName(req);
    }

}
