package com.sjg.sys.service;

import java.util.List;
import java.util.Map;

import com.sjg.sys.entity.Dictionary;
import com.sjg.sys.entity.vo.DictionaryVo;

/**
 * IDictionaryService
 * @author wenjie
 *
 */
public interface IDictionaryService {
	/**
	 * 删除
	 * @author wenjie
	 * @param id
	 * @return int 返回执行sql影响行数 
	 */
	int delete(Long id);
	/**
	 * 保存
	 * @author wenjie
	 * @param dictionary
	 * @return int 返回执行sql影响行数 
	 */
	int save(Dictionary dictionary);
	/**
	 * 更新
	 * @author wenjie
	 * @param dictionary 
	 * @return int 返回执行sql影响行数 
	 */
	int update(Dictionary dictionary);
	
	/**
	 * 更新
	 * @author wenjie
	 * @param dictionary 
	 * @return int 返回执行sql影响行数 
	 */
	int updateSelective(Dictionary dictionary);
	
	
	/**
	 * 查找
	 * @author wenjie
	 * @param id
	 * @return 返回Dictionary
	 */
	Dictionary findById(Long id);
	 /**
     * 筛选字典记录
     * @author wenjie
     * @param map[id,pid,code,value] 若都为空返回所有记录
     * @return List<Dictionary>
     */
	List<Dictionary> findByMulCond(Map<String, Object> parm);
	/**
     * 筛选字典记录
     * @author wenjie
     * @param map[id,pid,code,value] 若都为空返回所有记录
     * @return List<DictionaryVo>
     */
	List<DictionaryVo> findByMulCondRelation(Map<String, Object> parm);
	/**
	 * 
	 * @author wenjie
	 * @param list
	 * @return  int 返回执行sql影响行数
	 */
	int deleteList(List list);
	
	/**
	 * 查询子级字典数据
	 * @author 王
	 * @return
	 */
	List<DictionaryVo> selectchildrenList(Long pid);
	/**
	 * 根据父级 code 获取下级
	 * @author wenjie
	 * @param code
	 * @return
	 */
	List<Dictionary> findByPCode(String code);
	/**
	 * 根据code 获取
	 * @author wenjie
	 * @param code
	 * @return
	 */
	Dictionary findByCode(String code);
	/**
	 * 根据code 判断是否存在
	 * @author wenjie
	 * @param id 排除的id 可为空
	 * @param code
	 * @return
	 */
	List<Dictionary> checkCode(Long id, String code);
	/**
	 * 根据id修改
	 * @author 王
	 * @param dictionary
	 * @return
	 */
	int updateNotNull(Dictionary dictionary);
	
	/**
	 * 查询已推荐
	 * @author 王
	 * @param code
	 * @return
	 */
	List<Dictionary>findRecommendByCode(String code);
	/**
	 * 检测  value是否存在
	 * @param id 排除的id 可为空
	 * @param value
	 * @return
	 */
	List<Dictionary> checkValue(Long id,String value);
}
