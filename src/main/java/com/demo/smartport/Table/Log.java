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
@TableName("tb_log")
public class Log implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private String daytime;
}
