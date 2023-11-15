package com.demo.smartport.Entity.ShipAndCraft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CraftBatchReq implements Serializable {
    private List<String> name;
    private String status;
}
