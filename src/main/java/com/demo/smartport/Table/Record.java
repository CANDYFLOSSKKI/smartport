package com.demo.smartport.Table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("tb_record")
public class Record implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String daytime;
    private Integer applicense;
    private Integer appentrust;
    private Integer apporder;
    private Integer apptrue;
    private Integer appfalse;
}
