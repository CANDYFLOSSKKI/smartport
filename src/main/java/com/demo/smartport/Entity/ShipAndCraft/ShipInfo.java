package com.demo.smartport.Entity.ShipAndCraft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShipInfo implements Serializable {
    private int id;
    private String name;
    private String letter;
    private String imo;
    private String mmsi;
    private String type;
    private double length;
    private double width;
    private double draft;
    private String portrait;
    private String status;
    private double head;
    private double track;
    private double speed;
    private double latitude;
    private double longitude;
    private String destination;
    private String arrive;
    private String refresh;
}
