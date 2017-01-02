package com.sjg.sys.dao;

import java.util.List;
import java.util.Map;

import com.sjg.sys.entity.Log;

public interface LogMapper {
    int insert(Log record);

    int insertSelective(Log record);
    
    /**
     * 分页查询日志信息
     * @param params
     * @return
     */
    List<Log> findSystemLogList(Map<String, Object> params);
    
    /**
     * 根据条件获取日志总条数
     * @param params
     * @return
     */
    long findCountByType(Map<String, Object> params);
    
    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    Log selectByPrimaryKey(String id);
    
}