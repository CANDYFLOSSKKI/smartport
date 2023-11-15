package com.demo.smartport.Entity.Approval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApprovalInfoListResp implements Serializable {
    private boolean flag;
    private int num;
    private List<ApprovalInfo> list;
}
