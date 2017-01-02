package com.sjg.sys.service;

import java.util.List;

import com.sjg.sys.entity.Resources;
import com.sjg.sys.entity.vo.ResourceMenuVo;

public interface IResourceService {
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
	 * @param resources
	 * @return int 返回执行sql影响行数 
	 */
	int save(Resources resources);
	/**
	 * 批量保存
	 * @param resources
	 * @return
	 */
	void saves(List<Resources> resources);
	 /**
	  * 更新
	  * @author wenjie
	  * @param resources
	  * @return int 返回执行sql影响行数 
	  */
	int update(Resources resources);
	/**
	  * 更新资源并隐藏
	  * @author fqj
	  * @param resources
	  * @return int 返回执行sql影响行数 
	  */
	void updatesisHide(List<Resources> resourcess);
	/**
	  * 更新资源并显示
	  * @author fqj
	  * @param resources
	  * @return int 返回执行sql影响行数 
	  */
	void updatesisNotHide(List<Resources> resourcess);
	/**
	 * 查询
	 * @author wenjie
	 * @param id 主键
	 * @return  Resources
	 */
	Resources findById(Long id);
	/**
	 * 查询一种资源 
	 * @author wenjie
	 * @param pid 上级id   
	 * @param type资源类型   1菜单、2页面、3数据、4按钮 
	 * 		   若pid为null && type为null则返回所有资源  否则返回 pid || type 条件的资源
	 * @return  List<Resources>
	 */
	List<Resources> findByPidAndType(Long pid,Long type);
	/**
	 * 查询一种资源 包括隐藏的
	 * @param pid 上级id   
	 * @param type资源类型   1菜单、2页面、3数据、4按钮 
	 * 		   若pid为null && type为null则返回所有资源  否则返回 pid || type 条件的资源
	 * @return  List<Resources>
	 */
	public List<Resources> findAllByPidAndType(Long pid,Long type);
	
	/**
	 * 查询用户某种类型的所有权限
	 * @param userId 用户ID
	 * @param type 权限类型  菜单、url、按钮
	 * @param pid 上级ID
	 * @return
	 */
	public List<Resources> queryResources(String userId,String type,String pid);
	/**
	 * 查询用户某种类型的所有权限
	 * @param userId 用户ID
	 * @param type 权限类型  菜单、url、按钮
	 * @return
	 */
	public List<Resources> queryAllResources(long userId,long type);
	/**
	 * 更加角色id才行菜单列表
	 * @param role
	 * @return
	 */
	public List<Resources> queryResourcesListByRole(long roleId);
	
	/**
	 * 通过父菜单ID查询子菜单及子菜单的子项
	 * @param userId 用户ID
	 * @param type 权限类型  菜单、url、按钮
	 * @param pid 上级菜单ID
	 * @return
	 */
	public List<ResourceMenuVo> queryChildMenuList(String userId,String type,String pid);
	/**
	 * 批量删除
	 * @author wenjie
	 * @param list
	 * @return int 返回执行sql影响行数
	 */
	public int deleteList(List list);
	/**
	 * 批量删除
	 * @author fqj
	 * @param list
	 * @return 返回执行sql影响行数
	 */
	public int deleteRealList(List<Resources> list);
	
}
