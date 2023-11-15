package com.demo.smartport.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.smartport.Table.Entrust;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EntrustMapper extends BaseMapper<Entrust> {
}
