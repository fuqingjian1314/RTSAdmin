package com.sjg.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sjg.sys.dao.UserMapper;
import com.sjg.sys.entity.User;
import com.sjg.sys.service.IUserService;
import com.sjg.sys.utils.Pager;
import com.sjg.sys.utils.Pagination;
@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper userMapper; 

	@Override
	public Pagination<User> getUserPageInfo(long offset) {
		// TODO Auto-generated method stub
//		List<User> users=userMapper.selectUsersPage(offset, 15);
//		long count=userMapper.selectUsersPageCount();
		Pagination<User> page=new Pagination<User>(offset,15L,100l);
//		page.setItems(users);
		return page;
	}
	@Override
	public User queryUnlockedUserByLoginName(String loginName){
		User user = userMapper.queryUserByLoginName(loginName,(byte) 0);
		return user;
	}
	@Override
	public User queryUserByLoginName(String loginName) {
		User user = userMapper.queryUserByLoginName(loginName,null);
		return user;
	}

	@Override
	public int delete(Long id) {
		return userMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int deleteUses(Long[] ids){
		int i=0;
		for (Long id : ids) {
			userMapper.deleteByPrimaryKey(id);
			i++;
		}
		return i;
	}
	@Override
	public int save(User user) {
		return userMapper.insert(user);
	}

	@Override
	public int update(User user) {
		return userMapper.updateByPrimaryKey(user);
	}
	@Override
	public int updateNotNUll(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public User findById(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}


	@Override
	public Pagination<User> getUserInfoPage(long offSet, long pageSize,User user,String roleId) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("offSet", offSet);
			map.put("pageSize", pageSize);
			map.put("user", user);
			map.put("roleId", roleId);
			List<User> users=userMapper.queryUserList(map);
			Long count=userMapper.queryUserListCount(map);
			Pagination<User> page=new Pagination<User>(offSet,pageSize,count);
			page.setItems(users);
			return page;
		
	}
	@Override
	public List<User> getUserListLimit(Long offSet,Long pageSize,User user){
		List<User> users=userMapper.selectUsersPage(offSet, pageSize,user);
		return users;
	}
	@Override
	public Pager<User> getUserListPager(Long offSet,Long pageSize,User user){
		List<User> users=userMapper.selectUsersPage(offSet, pageSize,user);
		Long count=userMapper.selectUsersPageCount(user);
		Pager<User> p=new Pager<User>();
		p.setItems(users);
		p.setRowsCount(count);
		return p;
	}
	
	@Override
	public Pager<User> queryUserListByOrgId(Long offset, Long pageSize, User user) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		map.put("user", user);
		List<User> userL = userMapper.queryUserListByOrgId(map);
		Long count = userMapper.queryCountByOrg(map);
		Pager<User> page = new Pager<>();
		page.setRowsCount(count);
		page.setItems(userL);
		return page;
	}

	@Override
	public List<User> getAllUserList(User user){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<User> users=userMapper.queryUserMessageFromAPP(map);
		return users;
	}
	
	@Override
	public User queryUserById(Long userid) {
		return userMapper.queryUserById(userid);
	}
	
	@Override
	public Pager<User> queryUserListByOrgIdFromAPP(Long offset, Long pageSize, User user) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		map.put("user", user);
		List<User> userL = userMapper.queryUserListByOrgIdFromAPP(map);
//		Long count = userMapper.queryCountByOrg(map);
		Pager<User> page = new Pager<>();
//		page.setRowsCount(Long.parseLong(userL.size()+""));
		page.setItems(userL);
		return page;
	}
	@Override
	public User queryUserByRoleId(Map<String, Object> map) {
		return userMapper.queryUserByRoleId(map);
	}
	
	@Override
	public Map<String, Object> queryUserDetailById(Long userid) {
		return userMapper.queryUserDetailById(userid);
	}
}
