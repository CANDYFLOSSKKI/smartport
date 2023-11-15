package com.demo.smartport.Entity.ShipAndCraft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShipUpdateReq implements Serializable {
    private String name;
    private double head;
    private double track;
    private double speed;
    private double latitude;
    private double longitude;
    private String destination;
    private String arrive;
    private int status;
}
