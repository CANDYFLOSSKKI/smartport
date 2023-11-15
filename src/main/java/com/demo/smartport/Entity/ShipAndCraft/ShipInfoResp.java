package com.demo.smartport.Entity.ShipAndCraft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShipInfoResp implements Serializable {
    private boolean flag;
    private ShipInfo shipinfo;

    public static ShipInfoResp ON(){
        return new ShipInfoResp(true,null);
    }
    public static ShipInfoResp OFF(){
        return new ShipInfoResp(false,null);
    }

    public ShipInfoResp INFO(ShipInfo info){
        this.shipinfo=info;
        return this;
    }
}
