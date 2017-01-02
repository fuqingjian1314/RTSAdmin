package com.sjg.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sjg.sys.dao.LoginRecordMapper;
import com.sjg.sys.entity.LoginRecord;
import com.sjg.sys.service.ILoginRecordService;
import com.sjg.sys.utils.Pagination;
import com.sjg.sys.utils.PrimaryKeyUtil;
import com.sjg.sys.utils.contantUtil;

@Service
public class LoginRecordServiceImpl implements ILoginRecordService {

	@Resource
	private LoginRecordMapper loginRecordMapper;
	
	@Override
	public int save(LoginRecord loginRecord) {
		PrimaryKeyUtil pk=new PrimaryKeyUtil(1,2);
		loginRecord.setLrId(pk.nextId());
		return loginRecordMapper.insert(loginRecord);
	}

	@Override
	public Pagination<LoginRecord> getLoginRecordPage(long offSet, long pageSize, String lrLoginName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("offSet", offSet);
		param.put("pageSize", pageSize);
		param.put("lrLoginName", lrLoginName);
		List<LoginRecord> loginrecord=loginRecordMapper.queryLoginRecordList(param);
		long seq = offSet;
		for (LoginRecord lr : loginrecord) {
			if(!"".equals(lr.getLrId())){
				lr.setLrId(++seq);
			}
			
			if(contantUtil.lOGIN_L.equals(lr.getlrLoginStatus())){
				lr.setlrLoginStatus("登录");
			}
			if(contantUtil.lOGIN_E.equals(lr.getlrLoginStatus())){
				lr.setlrLoginStatus("退出");
			}

			if(lr.getLrLoginIp() != null && !"".equals(lr.getLrLoginIp())){
				if(lr.getLrLoginIp().equals("0:0:0:0:0:0:0:1")){
					lr.setLrLoginIp("127.0.0.1");
				}
			}

		}
		Long count=loginRecordMapper.queryCountLoginRecord(param);
		Pagination<LoginRecord> page=new Pagination<LoginRecord>(offSet,pageSize,count);
		page.setItems(loginrecord);
		return page;
	}

}
