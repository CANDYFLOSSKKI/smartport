package com.demo.smartport.Entity.ShipAndCraft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CraftInfo implements Serializable {
    private String name;
    private String letter;
    private String imo;
    private String mmsi;
    private String type;
    private String status;
    private String portrait;
}
