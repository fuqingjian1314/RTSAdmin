package com.sjg.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sjg.sys.dao.RoleResourcesMapper;
import com.sjg.sys.entity.RoleResources;
import com.sjg.sys.service.IRoleResourcesService;
@Service
public class RoleResourcesServiceImpl implements IRoleResourcesService {
	@Resource
	private RoleResourcesMapper roleResourcesMapper;
	@Override
	public int delete(Long id) {
		return roleResourcesMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int save(RoleResources roleResources) {
		return roleResourcesMapper.insert(roleResources);
	}

	@Override
	public int update(RoleResources roleResources) {
		return roleResourcesMapper.updateByPrimaryKey(roleResources);
	}

	@Override
	public RoleResources findById(Long id) {
		return roleResourcesMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteMenusByRoleId(Long roleId) {
		return roleResourcesMapper.deleteMenusByRoleId(roleId);
	}

	@Override
	public void saves(List<RoleResources> rrs) {
		for (RoleResources roleResources : rrs) {
			roleResourcesMapper.insert(roleResources);
		}
		
	}

}
