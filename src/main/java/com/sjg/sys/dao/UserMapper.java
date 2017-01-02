package com.sjg.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sjg.sys.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);
    
    List<User> selectUsersPage(@Param("offSet") long offSet,@Param("pageSize") long pageSize,@Param("user") User user);
    
    long selectUsersPageCount(@Param("user") User user);
    
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * 登录：通过登录名查询用户
     * @param loginName
     * @return
     */
    public User queryUserByLoginName(@Param("loginName")String loginName,@Param("locked")Byte locked);
    
    /**
     * 查询后台用户分页
     * @author 王
     * @return 返回一个UserVo对象
     */
    List<User> queryUserList(Map<String, Object> map);
    /**
     * 查询后台用户总数
     * @author 王
     * @return 返回一个Long型记录数
     */
    long queryUserListCount(Map<String,Object> map);
    
    /**
     * 根据机构ID查询用户
     * @param map  user对象   pageSize  offset
     * @return
     */
    List<User> queryUserListByOrgId(Map<String,Object> map);
    
    /**
     * 根据机构ID查询总记录数
     * @param map
     * @return
     */
    Long queryCountByOrg(Map<String,Object> map);
    
    /**
     * 获取登录用户可空用户
     * */
    List<User> queryUserMessageFromAPP(Map<String,Object> map);
    /**
     * 根据机构ID查询用户
     * @param map  user对象   pageSize  offset
     * @return
     */
    public User queryUserById(Long userid);
    
    /**
     * APP用户根据机构ID查询可操作用户
     * @param map  user对象   pageSize  offset
     * @return
     */
    List<User> queryUserListByOrgIdFromAPP(Map<String,Object> map);
    /**
     * 根据角色和ID查用户，限制APP登陆客户仅管理员角色可操作用户信息
     * */
    public User queryUserByRoleId(Map<String,Object> map);
    
    /**
     * APP用户根据ID查询个人详细信息
     * @param map  user对象   pageSize  offset
     * @return
     */
    public Map<String, Object> queryUserDetailById(Long userid);
}