package com.demo.smartport.Entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRowListResp implements Serializable {
    private boolean flag;
    private int num;
    private List<OrderRow> list;
}
