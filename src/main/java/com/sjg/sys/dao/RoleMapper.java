package com.sjg.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sjg.sys.entity.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	Role findByRoleKey(String roleKey);

	List<Role> findAll();
	
	/**
	 * 分页查询角色
	 * @param role 查询条件，所有条件必须符合role对象里的属性类型
	 * @return
	 */
	public List<Role> queryByPage(Map<String, Object> map);
	/**
	 * 查询角色列表
	 * @param map
	 * @return
	 */
	public List<Role> findRolelist(Role role);
	
	/**
	 * 查询角色总记录数
	 * @param role 查询条件，所有条件必须符合role对象里的属性类型
	 * @return
	 */
	public Long queryByPageCount(Role role);
	
	/**
	 * 查询Rolekey存在条数
	 * @param roleKey
	 * @return
	 */
	public Long queryNumByKey(String roleKey);
	
	/**
	 * 根据用户ID查询对应的角色列表
	 * (返回对象里的ID为userRole表里的id)
	 * @param userId
	 * @return
	 */
	public List<Role> queryRoleByUserId(@Param(value="userId")String userId);
	
	/**
	 * 根据用户ID查询对应用户还没有的角色信息（not in）
	 * @param userId
	 * @return
	 */
	public List<Role> queryRolesWithoutUser(@Param(value="userId")String userId);
}