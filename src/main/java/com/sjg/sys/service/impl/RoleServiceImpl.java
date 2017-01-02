package com.sjg.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sjg.sys.dao.RoleMapper;
import com.sjg.sys.entity.Role;
import com.sjg.sys.entity.User;
import com.sjg.sys.service.IRoleService;
import com.sjg.sys.utils.CommonUtils;
import com.sjg.sys.utils.Pager;
import com.sjg.sys.utils.Pagination;
@Service
public class RoleServiceImpl implements IRoleService {
	@Resource
	private RoleMapper roleMapper;
	@Override
	public int delete(Long id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int save(Role role) {
		
		User user = CommonUtils.getSessionUser();
		if(user == null ){
			return -1;
		}
		
		//设置添加角色的附加信息
		role.setCreaterId(user.getId());
		role.setCreaterName(user.getName());
		role.setUpdaterId(user.getId());
		role.setUpdaterName(user.getName());
		role.setCreateTime(new Date());
		role.setUpdateTime(new Date());
		
		return roleMapper.insert(role);
	}

	@Override
	public int update(Role role) {
		return roleMapper.updateByPrimaryKey(role);
	}

	@Override
	public Role findById(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public Role findByRoleKey(String roleKey) {
		return roleMapper.findByRoleKey(roleKey);
	}

	@Override
	public List<Role> findAll() {
		return roleMapper.findAll();
	}

	@Override
	public Pagination<Role> queryByPage(Role role,Pagination<Role> page) {
		Map<String, Object> map = new HashMap<>();
		map.put("role", role);
		map.put("beginIndex", page.getCurIndex());
		map.put("pageSize", page.getPageSize());
		List<Role> roles = roleMapper.queryByPage(map);
		Long totalRecord = roleMapper.queryByPageCount(role);
		page.setItems(roles);
		page.setRowsCount(totalRecord);
		return page;
	}
	@Override
	public List<Role> findRolelist(Role role) {
		return roleMapper.findRolelist(role);
	}
	
	@Override
	public boolean isEnableKey(String key){
		Long num = roleMapper.queryNumByKey(key);
		if(num != null && num >0){
			return false;
		}
		return true;
	}

	@Override
	public Role getById(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateById(Role role){
		User user = CommonUtils.getSessionUser();
		if(user == null || user.getId()== null ){
			return -1;
		}
		//修改附加信息
		role.setUpdaterId(user.getId());
		role.setUpdaterName(user.getName());
		role.setUpdateTime(new Date());
		
		return roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public List<Role> queryRoleByUserId(String userId) {
		return roleMapper.queryRoleByUserId(userId);
	}

	@Override
	public List<Role> queryRolesWithoutUser(String userId) {
		return roleMapper.queryRolesWithoutUser(userId);
	}

	@Override
	public List<Role> findListLimit(Long offSet, Long pageSize, Role role) {
		Map<String, Object> map = new HashMap<>();
		map.put("role", role);
		map.put("beginIndex", offSet);
		map.put("pageSize", pageSize);
		List<Role> roles = roleMapper.queryByPage(map);
		
		return roles;
	}

	@Override
	public Pager<Role> findListPager(Long offSet, Long pageSize, Role role) {
		Map<String, Object> map = new HashMap<>();
		map.put("role", role);
		map.put("beginIndex", offSet);
		map.put("pageSize", pageSize);
		List<Role> roles = roleMapper.queryByPage(map);
		Long totalRecord = roleMapper.queryByPageCount(role);
		
		Pager<Role> pager = new Pager<>();
		pager.setItems(roles);
		pager.setRowsCount(totalRecord);
		return pager;
	}



}
