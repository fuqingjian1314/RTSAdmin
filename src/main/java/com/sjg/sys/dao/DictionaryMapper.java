package com.sjg.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sjg.sys.entity.Dictionary;
import com.sjg.sys.entity.vo.DictionaryVo;

public interface DictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
    
    /**
     * 筛选字典记录
     * @author wenjie
     * @param map[id,pid,code,value]
     * @return List<Dictionary>
     */
	List<Dictionary> findByMulCond(Map<String, Object> map);
	 /**关联 查询 childrenList
     * 筛选字典记录
     * @author wenjie
     * @param map[id,pid,,code,value]
     * @return List<DictionaryVo>
     */
	List<DictionaryVo> findByMulCondRelation(Map<String, Object> map);
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
	 * @param pcode
	 * @return
	 */
	List<Dictionary> findByPCode(String pcode);
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
	 * @param id
	 * @param code
	 * @return
	 */
	List<Dictionary> checkCode(@Param("id")Long id, @Param("code")String code);
	
	/**
	 * 查询已推荐的
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
	List<Dictionary> checkValue(@Param("id")Long id, @Param("value")String value);
}