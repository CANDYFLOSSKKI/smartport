package com.demo.smartport.Table;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("tb_ship")
//船舶绑定数据
public class Ship implements Serializable {
    //船舶主键/唯一关键静态信息
    @TableId(type= AUTO)
    private Integer id;         //(主键)id
    private String name;        //名称
    private String letter;      //呼号
    private String imo;         //imo
    private String mmsi;        //mmsi

    //船舶静态数据
    private Integer type;        //船舶类型
    private Double length;       //船长(米)
    private Double width;        //船宽(米)
    private Double draft;        //吃水(米)
    private String portrait;     //船舶预览图url

    //船舶动态数据
    private Integer status;      //船舶状态
    private Double head;         //船首向(度)
    private Double track;        //船迹向(度)
    private Double speed;        //航速(节)ar
    private String latitude;     //纬度(N)
    private String longitude;    //经度(E)
    private String destination;  //目的地
    private String arrive;       //预到时间(年/月/日 时:分:秒)
    private String refresh;      //更新时间(年/月/日 时:分:秒)
}
