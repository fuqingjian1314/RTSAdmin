package com.sjg.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sjg.sys.dao.UserRoleMapper;
import com.sjg.sys.entity.UserRole;
import com.sjg.sys.service.IUserRoleService;

@Service
public class UserRoleServiceImpl implements IUserRoleService {
	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	public int delete(Long id) {
		return userRoleMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int modifyUserRoles(Long userId,String roleIds,String roleKeys){
		String[] ids=roleIds.split(",");
		String[] keys=roleKeys.split(",");

		int delnum=userRoleMapper.deleteByuserId(userId);
		int i=0;
		for (String id : ids) {
			UserRole ur=new UserRole();
			ur.setRoleId(Long.parseLong(id));
			ur.setUserId(userId);
			ur.setRoleKey(keys[i]);
			userRoleMapper.insert(ur);
			i++;
		}
		return delnum;
	}
	@Override
	public int save(UserRole record) {
		return userRoleMapper.insert(record);
	}

	@Override
	public int update(UserRole record) {
		return userRoleMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserRole> findList() {
		return userRoleMapper.findAll();
	}

	@Override
	public UserRole findById(Long id) {
		return userRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserRole> queryList(UserRole ur) {
		return userRoleMapper.queryList(ur);
	}

	@Override
	public int insertSelective(UserRole record){
		return userRoleMapper.insertSelective(record);
	}
	 
}
