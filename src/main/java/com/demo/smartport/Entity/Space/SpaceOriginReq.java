package com.demo.smartport.Entity.Space;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpaceOriginReq implements Serializable {
    private String account;
    private String appkey;
    private double value;
}
