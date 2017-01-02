package com.sjg.sys.dao;

import java.util.List;
import java.util.Map;

import com.sjg.sys.entity.LoginRecord;

public interface LoginRecordMapper {
	
    int insert(LoginRecord record);

    int insertSelective(LoginRecord record);
    
    /**
     * 获取登录日志
     * @param param 查询条件
     * @return
     */
    List<LoginRecord> queryLoginRecordList(Map<String, Object> param);
    
    /**
     * 获取当前访问日志的总计记录数
     * @param param 查询条件
     * @return
     */
    long queryCountLoginRecord(Map<String, Object> param);
    
}