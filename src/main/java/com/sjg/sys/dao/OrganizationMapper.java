package com.sjg.sys.dao;

import java.util.List;

import com.sjg.sys.entity.Organization;

public interface OrganizationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
	/**
	 * Organization的name
	 *@author wenjie
	 * @param name
	 * @return
	 */
	Organization findByName(String name);
	/**
	 * 获取所有数据
	 *@author wenjie
	 * @return
	 */
	List<Organization> findAll();
	
	/**
	 * 根据PID向下查询一级
	 * @param pid
	 * @return
	 */
	List<Organization> queryByPid(Long pid);
	
	/**
	 * 通过name关键字模糊查询机构列表
	 * @param organization
	 * @return
	 */
	List<Organization> queryOrg(Organization organization);
}