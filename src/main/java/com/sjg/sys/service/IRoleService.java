package com.sjg.sys.service;

import java.util.List;

import com.sjg.sys.entity.Role;
import com.sjg.sys.utils.Pager;
import com.sjg.sys.utils.Pagination;

/**
 * IRoleService 
 * @author wenjie
 *
 */
public interface IRoleService {
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
	 * @param role
	 * @return int 返回执行sql影响行数 
	 */
	int save(Role role);
	/**
	 * 修改
	 * @author wenjie
	 * @param role
	 * @return int 返回执行sql影响行数 
	 */
	int update(Role role);
	/**
	 * 查找
	 * @author wenjie
	 * @param id 主键
	 * @return Role
	 */
	Role findById(Long id);
	/**
	 * 查找
	 * @author wenjie
	 * @param roleKey 角色关键
	 * @return Role
	 */
	Role findByRoleKey(String roleKey);
	/**
	 * 查找所有角色
	 * @author wenjie
	 * @return List<Role>
	 */
	List<Role> findAll();
	/**
	 * 分页查询角色
	 * @param role 查询条件，所有条件必须符合role对象里的属性类型
	 * @return
	 */
	Pagination<Role> queryByPage(Role role,Pagination<Role> pageRow);
	/**
	 * 查询角色列表
	 * @param role
	 * @return
	 */
	public List<Role> findRolelist(Role role);
	
	/**
	 * 判断rolekey是否可用，
	 * 如果不可用返回false,否则返回true
	 * @param key
	 * @return
	 */
	boolean isEnableKey(String key);
	
	/**
	 * 通过ID得到角色
	 * @param id
	 * @return
	 */
	public Role getById(Long id);
	
	/**
	 * 通过ID更新角色
	 * @param role
	 * @return
	 */
	public int updateById(Role role);
	
	/**
	 * 根据用户ID查询对应的角色列表
	 * @param userId
	 * @return
	 */
	public List<Role> queryRoleByUserId(String userId);
	/**
	 * 根据用户ID查询对应用户还没有的角色信息（not in）
	 * @param userId
	 * @return
	 */
	public List<Role> queryRolesWithoutUser(String userId);
	
	/**
	 * 分页列表查询(分页)
	 * @param offSet
	 * @param pageSize
	 * @param role
	 * @return
	 */
	public List<Role> findListLimit(Long offSet, Long pageSize, Role role);
	
	/**
	 * 分页列表查询(分页)
	 * @param offSet
	 * @param pageSize
	 * @param role
	 * @return
	 */
	public Pager<Role> findListPager(Long offSet, Long pageSize, Role role);
	
}
