package com.sjg.sys.service;

import java.util.List;

import com.sjg.sys.entity.UserRole;
/**
 * IUserRoleService
 * @author wenjie
 *
 */
public interface IUserRoleService {
	/**
	 * 根据主键删除记录
	 * @param id
	 * @return int 返回执行sql影响行数 
	 */
    int delete(Long id);
    /**
     * 根据userId删除用户所有角色
     * @param userId
     * @return
     */
    int modifyUserRoles(Long userId,String roleIds,String roleKeys);
	/***
	 * 插入所有字段数据
	 * @param record
	 * @return int 返回执行sql影响行数 
	 */
    int save(UserRole record);
	/**
	 * 更新所有字段
	 * @param record
	 * @return int 返回执行sql影响行数 
	 */
    int update(UserRole record);
   /**
    *根据id 查询一个 UserRole
    * @param id
    * @return UserRole
    */
    UserRole findById(Long id);
	/**
	 * 查询所有 UserRole
	 * @return  List<UserRole>
	 */
    List<UserRole> findList();
    
    /**
     * 根据条件查询user-role 关系列表
     * @param ur
     * @return
     */
    public List<UserRole> queryList(UserRole ur);
    
    /**
     * 插入不为空的字段
     * @param record
     * @return
     */
    public int insertSelective(UserRole record);
}
