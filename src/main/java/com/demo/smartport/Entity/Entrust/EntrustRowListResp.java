package com.demo.smartport.Entity.Entrust;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntrustRowListResp implements Serializable {
    private boolean flag;
    private int num;
    private List<EntrustRow> list;
}
