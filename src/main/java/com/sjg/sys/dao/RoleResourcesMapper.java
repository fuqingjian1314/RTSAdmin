package com.sjg.sys.dao;

import com.sjg.sys.entity.RoleResources;

public interface RoleResourcesMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteMenusByRoleId(Long roleId);

    int insert(RoleResources record);

    int insertSelective(RoleResources record);

    RoleResources selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleResources record);

    int updateByPrimaryKey(RoleResources record);
}