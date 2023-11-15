package com.demo.smartport.Entity.Approval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApprovalDealReq implements Serializable {
    private String appkey;
    private String account;
    private boolean result;
    private String review;
}
