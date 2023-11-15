package com.demo.smartport.Entity.Approval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApprovalReserveReq implements Serializable {
    private String appkey;
    private String account;
}
