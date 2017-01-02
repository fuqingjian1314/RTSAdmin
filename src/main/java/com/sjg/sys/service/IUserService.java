package com.sjg.sys.service;

import java.util.List;
import java.util.Map;

import com.sjg.sys.entity.User;
import com.sjg.sys.utils.Pager;
import com.sjg.sys.utils.Pagination;

public interface IUserService {

	Pagination<User> getUserPageInfo(long offset);
	/**
	 * 删除
	 * @author wenjie
	 * @param id主键
	 * @return  int 返回执行sql影响行数 
	 */
	int delete(Long id);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int deleteUses(Long[] ids);
	/**
	 * 保存
	 * @author wenjie
	 * @param user
	 * @return   int 返回执行sql影响行数 
	 */
	int save(User user);
	/**
	 * 修改
	 * @param user
	 * @return   int 返回执行sql影响行数 
	 */
	int update(User user);
	/**
	 * 只更新不为空的user字段到数据库
	 * @author wenjie
	 * @param user
	 * @return
	 */
	int updateNotNUll(User user);
	/**
	 * 查找
	 * @author wenjie
	 * @param id 主键
	 * @return   int 返回执行sql影响行数 
	 */
	User findById(Long id);
	/**
	 * 登录使用，通过登录名查询用户
	 * @param loginName
	 * @return
	 */
	public User queryUserByLoginName(String loginName);
	/**
	 * 查询没有被禁用的用户
	 * @param loginName
	 * @param locked
	 * @return
	 */
	public User queryUnlockedUserByLoginName(String loginName);
	
	/**
	 * 分页查询用户信息
	 * @param offSet 起始行标
	 * @param pageSize 页大小
	 * @param user 用户查询条件
	 * @param roleId 角色条件
	 * @return
	 */
	public Pagination<User> getUserInfoPage(long offSet,long pageSize,User user,String roleId);
	/**
	 * 获取分页list
	 * @param offSet
	 * @param pageSize
	 * @return
	 */
	public Pager<User> getUserListPager(Long offSet,Long pageSize,User user);
	/**
	 * 获取分页list
	 * @param offSet
	 * @param pageSize
	 * @return
	 */
	public List<User> getUserListLimit(Long offSet,Long pageSize,User user);
	
	/**
	 * 根据机构分页查询
	 * @param offSet
	 * @param pageSize
	 * @param user
	 * @return
	 */
	public Pager<User> queryUserListByOrgId(Long offset,Long pageSize,User user);
	
	/**
	 * 获取当前登录用户所有可控用户
	 * @return 所有可控用户
	 */
	public List<User> getAllUserList(User user);
	
	/**
	 * 获取指定Id的用户
	 * @return 所有可控用户
	 */
	public User queryUserById(Long userid);
	
	/**
	 * APP用户根据机构分页查询可操作的其他用户
	 * @param offSet
	 * @param pageSize
	 * @param user
	 * @return
	 */
	public Pager<User> queryUserListByOrgIdFromAPP(Long offset, Long pageSize, User user);
	
	/**
	 * 查询用户角色
	 * */
	public User queryUserByRoleId(Map<String,Object> map);
	public Map<String, Object> queryUserDetailById(Long userid);
}
