package com.demo.smartport.Table;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("tb_approval")
//港口后台审批用户审批工单
public class Approval implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer id;         //(主键)id
    private String appkey;      //唯一关联字段
    private String type;        //审批类型
    private Integer intro;      //审批信息所在表id

    private Integer flag;       //是否已审批
                                //0:未审批
                                //1:已审批

    private Integer result;     //审批结果
                                //0:审批未通过
                                //1:审批通过

    private String apply;       //申请用户
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED, whereStrategy = FieldStrategy.IGNORED)
    private String approve;     //审批用户
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String review;      //审批意见

    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String appday;      //申请时间
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String comday;      //审批时间
}
