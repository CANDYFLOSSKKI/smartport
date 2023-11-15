package com.demo.smartport.Entity.RestoreAndGoods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestoreInfoListResp implements Serializable {
    private boolean flag;
    private int num;
    private List<RestoreInfo> list;
}
