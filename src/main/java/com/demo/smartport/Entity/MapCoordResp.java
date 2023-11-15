package com.demo.smartport.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MapCoordResp implements Serializable {
    private float x;
    private float y;
}
