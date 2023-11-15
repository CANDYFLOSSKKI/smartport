package com.demo.smartport.Entity.ShipAndCraft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CraftInfoPreResp implements Serializable {
    private boolean flag;
    private String account;
    private String name;
}
