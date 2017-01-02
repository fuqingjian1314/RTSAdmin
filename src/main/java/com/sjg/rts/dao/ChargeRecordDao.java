package com.sjg.rts.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sjg.rts.entity.ChargeRecordVO;

/**
 * DAO层接口类
 * @author Administrator
 * 
 */
@Repository
public interface ChargeRecordDao {
	/**
	 * 获取所有的ChargeRecordVO对象列表及分页信息
	 * @param vo 业务查询条件
	 * @param offSet 数据记录偏移量
	 * @param pageSize 每页显示记录数
	 * @return 满足条件的业务数据对象列表
	 */
	public List<ChargeRecordVO> findPageInfos(ChargeRecordVO vo, int offSet, int pageSize);
	public List<Map<String, Object>> findAllInfos(ChargeRecordVO vo);

	/**
	 * 保存ChargeRecordVO对象
	 * @param vo 需要保存的业务数据
	 */
	public void save(ChargeRecordVO vo);

	/**
	 * 删除ChargeRecordVO对象
	 * @param arr 选择的业务数据主键列表
	 */
	public void delete(String[] arr);

	/**
	 * 更新ChargeRecordVO对象
	 * @param vo 需要更新的业务数据
	 */
	public void update(ChargeRecordVO vo);

	/**
	 * 根据主键获取业务详情
	 * @param id 业务主键
	 * @return 业务详情对象
	 */
	public ChargeRecordVO getChargeRecordVOById(String id);

	/**
	 * 计算满足条件的总记录数据
	 * @param vo 查询条件
	 * @return 满足条件的记录总数
	 */
	public Integer findAllInfosCount(ChargeRecordVO vo);
	
	/**
	 * 给剩余分数字段充值
	 */
	public void addChargeScoreToRestScore(ChargeRecordVO vo);
}
