package com.sjg.sys.dao;

import java.util.List;

import com.sjg.sys.entity.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    int deleteByuserId(Long userId);
	List<UserRole> findAll();
	
	/**
     * 根据条件查询user-role 关系列表
     * @param ur
     * @return
     */
	List<UserRole> queryList(UserRole userRole);
}