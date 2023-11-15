package com.demo.smartport.Entity.Approval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApprovalEntrustDealReq implements Serializable {
    private String appkey;
    private String account;
    private boolean result;
    private int restore;
    private String review;
}
