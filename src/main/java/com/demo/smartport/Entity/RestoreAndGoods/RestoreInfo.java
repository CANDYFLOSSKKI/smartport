package com.demo.smartport.Entity.RestoreAndGoods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestoreInfo implements Serializable {
    private int id;
    private String mes;
    private double vleft;
}
