package com.sjg.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sjg.sys.dao.OrganizationMapper;
import com.sjg.sys.entity.Organization;
import com.sjg.sys.service.IOrganizationService;
@Service
public class OrganizationServiceImpl implements IOrganizationService {
	@Resource
	private OrganizationMapper organizationMapper;
	@Override
	public int delete(Long id) {
		return organizationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int save(Organization record) {
		return organizationMapper.insert(record);
	}

	
	@Override
	public int update(Organization record) {
		return organizationMapper.updateByPrimaryKey(record);
	}

	@Override
	public Organization findById(Long id) {
		return organizationMapper.selectByPrimaryKey(id);
	}

	@Override
	public Organization findByName(String name) {
		return organizationMapper.findByName(name);
	}

	@Override
	public List<Organization> findAll() {
		return organizationMapper.findAll();
	}

	@Override
	public List<Organization> queryByPid(Long pid) {
		return organizationMapper.queryByPid(pid);
	}

	@Override
	public int updateSelective(Organization organization) {
		return organizationMapper.updateByPrimaryKeySelective(organization);
	}

	@Override
	public int insertSelective(Organization organization) {
		// TODO Auto-generated method stub
		return organizationMapper.insertSelective(organization);
	}

	@Override
	public List<Organization> queryOrg(Organization organization) {
		return organizationMapper.queryOrg(organization);
	}

}
