package com.demo.smartport.Entity.RestoreAndGoods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestoreOccupyReq implements Serializable {
    private int id;
    private double value;
}
