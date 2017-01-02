package com.sjg.sys.service;

import java.util.List;

import com.sjg.sys.entity.RoleResources;
/**
 * IRoleResourcesService
 * @author wenjie
 *
 */
public interface IRoleResourcesService {
    /**
	 * 删除
	 * @author wenjie
	 * @param id 主键
	 * @return int 返回执行sql影响行数 
	*/
	int delete(Long id);
	/**
	 * 根据角色删除菜单
	 * @param roleId 角色id
	 * @return
	 */
	int deleteMenusByRoleId(Long roleId);
	/**
	 * 保存
	 * @author wenjie
	 * @param roleResources
	 * @return int 返回执行sql影响行数 
	 */
	int save(RoleResources roleResources);
	/**
	 * 批量保存
	 * @param rrs
	 */
	void saves(List<RoleResources> rrs);
	/**
	 * 修改
	 * @author wenjie
	 * @param roleResources
	 * @return int 返回执行sql影响行数 
	 */
	int update(RoleResources roleResources);
	/**
	 * 
	 * @author wenjie
	 * @param id主键
	 * @return RoleResources
	 */
	RoleResources findById(Long id);
 
}
