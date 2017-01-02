package com.sjg.sys.service;

import com.sjg.sys.entity.LoginRecord;
import com.sjg.sys.utils.Pagination;

public interface ILoginRecordService {

	/**
	 * 保存登录信息
	 * @param loginRecord
	 * @return 返回sql受影响的行
	 */
	int save(LoginRecord loginRecord);
	
	/**
	 * 分页查询访问日志
	 * @param offSet     起始行标
	 * @param pageSize	显示数据的条数
	 * @param lrLoginName	登录名
	 * @return
	 */
	Pagination<LoginRecord> getLoginRecordPage(long offSet, long pageSize,String lrLoginName) ;
	
}
