package com.demo.smartport.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.smartport.Table.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
