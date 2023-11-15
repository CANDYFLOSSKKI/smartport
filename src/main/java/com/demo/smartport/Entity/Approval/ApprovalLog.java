package com.demo.smartport.Entity.Approval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApprovalLog implements Serializable {
    private String appkey;
    private String type;
    private boolean result;
    private String apply;
    private String appday;
    private String comday;
}
