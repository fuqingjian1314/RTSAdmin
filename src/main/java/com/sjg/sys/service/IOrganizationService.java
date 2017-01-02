package com.sjg.sys.service;

import java.util.List;

import org.aspectj.weaver.ast.Or;

import com.sjg.sys.entity.Organization;
/**
 * IOrganizationService
 * @author wenjie
 *
 */

public interface IOrganizationService {
	/**
	 * 删除
	 * @author wenjie
	 * @param id 主键
	 * @return int 返回执行sql影响行数 
	 */
	int delete(Long id);
	/**
	 * 保存
	 * @author wenjie
	 * @param organization
	 * @return int 返回执行sql影响行数 
	 */
	int save(Organization organization);
	
	/**
	 * 保存
	 * @author wenjie
	 * @param organization
	 * @return int 返回执行sql影响行数 
	 */
	int insertSelective(Organization organization);
	
	/**
	 * 更新
	 * @author wenjie
	 * @param organization
	 * @return int 返回执行sql影响行数 
	 */
	int update(Organization organization);
	
	/**
	 * 更新有值的字段
	 * @author wenjie
	 * @param organization
	 * @return int 返回执行sql影响行数 
	 */
	int updateSelective(Organization organization);
	
	/**
	 * 查询
	 * @author wenjie
	 * @param id
	 * @return Organization
	 */
	Organization findById(Long id);
	/**
	 * 查询
	 * @author wenjie
	 * @param name 
	 * @return Organization
	 */
	Organization findByName(String name);
	/**
	 * 保存
	 * @author wenjie
	 * @param  
	 * @return List<Organization>
	 */
	List<Organization> findAll();
	
	/**
	 * 根据PID查询所有下级     只向下查询一级
	 * @param pid
	 * @return
	 */
	List<Organization> queryByPid(Long pid);
	
	/**
	 * 查询机构，传入一个name关键字，做name模糊查询
	 * @return
	 */
	List<Organization> queryOrg(Organization organization);
	
	
}
