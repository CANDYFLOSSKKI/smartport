package com.demo.smartport.Entity.Space;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpaceInfoListResp implements Serializable {
    private boolean flag;
    private int num;
    private List<SpaceInfo> list;
}
