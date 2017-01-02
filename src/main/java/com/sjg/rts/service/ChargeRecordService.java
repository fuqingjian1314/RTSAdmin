package com.sjg.rts.service;

import java.util.List;
import java.util.Map;

import com.sjg.rts.entity.ChargeRecordVO;
import com.sjg.sys.utils.Pagination;

/**
 * 服务层接口类
 * @author Administrator
 * 
 */
public interface ChargeRecordService {
	/**
	 * 根据页面查询条件，分页查询业务数据
	 * @param vo 查询条件
	 * @param page 分页参数
	 * @return 分页业务数据及满足条件总记录数
	 */
	public Map<String, Object> findPageInfos(ChargeRecordVO vo, Pagination page);
	public List<Map<String, Object>> findAllInfos(ChargeRecordVO vo);

	/**
	 * 保存ChargeRecordVO对象
	 * @param vo 新增业务详情
	 */
	public void save(ChargeRecordVO vo);

	/**
	 * 删除ChargeRecordVO对象
	 * @param arr 删除数据列表
	 */
	public void delete(String[] arr);

	/**
	 * 更新ChargeRecordVO对象
	 * @param vo 修改后的业务数据
	 */
	public void update(ChargeRecordVO vo);

	/**
	 * 根据主键获取业务详情
	 * @param id 业务主键
	 * @return 业务详情对象
	 */
	public ChargeRecordVO getChargeRecordVOById(String id);
}
