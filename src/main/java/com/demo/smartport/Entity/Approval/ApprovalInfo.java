package com.demo.smartport.Entity.Approval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApprovalInfo implements Serializable {
    private String appkey;         //唯一关联字段
    private String type;           //审批类型
    private Integer intro;         //审批信息所在表id

    private String apply;          //申请用户
    private String appday;         //申请时间
}
