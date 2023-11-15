package com.demo.smartport.Entity.ShipAndCraft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShipKeyWordListResp implements Serializable {
    private boolean flag;
    private int num;
    private List<ShipKeyWordInfo> list;
}
