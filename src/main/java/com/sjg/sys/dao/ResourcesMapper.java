package com.sjg.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sjg.sys.entity.Resources;
import com.sjg.sys.entity.Role;

public interface ResourcesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resources record);

    int insertSelective(Resources record);

    Resources selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resources record);

    int updateByPrimaryKey(Resources record);
    
    /**
     * 查询用户某种类型的所有权限
	 * @param map : userId 用户ID
     * @param map : type 资源类型
     * @param map : pid 上级ID
     * @return
     */
    public List<Resources> queryResourceByUserIdAndType(Map<String, Object> map);
    /**
     * 查询用户所有权限菜单
     * @return
     */
    public List<Resources> queryAllResourceByUserIdAndType(@Param("userId")long userId,@Param("type")long type);
    /**
     * 根据角色查询菜单
     * @param role
     * @return
     */
    public List<Resources> queryResourcesListByRole(Role role);
    
    /**
     * 通过用户ID,资源类型，上级ID得到资源个数
     * @param map : userId 用户ID
     * @param map : type 资源类型
     * @param map : pid 上级ID
     * @return
     */
    public Long queryCountByUserIdAndType(Map<String, Object> map);
    /**
	 * 查询一种资源 
	 * @author wenjie
     * @param map : type 资源类型  1菜单、2页面、3数据、4按钮
     * @param map : pid 上级ID
	 * @return  List<Resources>  若type 为null 则返回所有资源
	 */
	List<Resources> findByPidAndType(Map<String, Object> map);
	/**
	 * 查询资源包括隐藏的
	 * @param map type 资源类型  1菜单、2页面、3数据、4按钮  pid 上级ID
	 * @return
	 */
	List<Resources> findAllByPidAndType(Map<String, Object> map);
	
	/**
	 * 批量删除
	 * @author wenjie
	 * @param list
	 * @return 返回执行sql影响行数
	 */
	public int deleteList(List<Resources> list);
	
	/**
	 * 批量删除
	 * @author fqj
	 * @param list
	 * @return 返回执行sql影响行数
	 */
	public int deleteRealList(List<Resources> list);
}