package com.sjg.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sjg.sys.entity.RoleResources;
import com.sjg.sys.service.IAuthorityService;
import com.sjg.sys.service.IRoleResourcesService;
@Service
public class AuthorityServiceImpl implements IAuthorityService {
	@Resource
	private IRoleResourcesService iRoleResourcesService;

	@Override
	public void modifyRoleMenus(List<RoleResources> rrs, Long roleId) {
		iRoleResourcesService.deleteMenusByRoleId(roleId);
		iRoleResourcesService.saves(rrs);

	}

}
