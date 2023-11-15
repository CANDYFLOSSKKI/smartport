package com.demo.smartport.Entity.ShipAndCraft;

import com.demo.smartport.Table.Ship;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
//获取船舶绑定信息列表响应体
public class ShipInfoListResp implements Serializable {
    private boolean flag;
    private int num;
    private List<ShipInfo> list;
}
