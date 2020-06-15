package com.inteall.image.dao;

import java.util.List;
import java.util.Map;

import com.inteall.image.pojo.Zhishiku;

/**
 * 知识库Dao接口
 * @author Administrator
 *
 */
public interface ZhishikuDao {

	/**
	 * 根据日期月份分组查询
	 * @return
	 */
	public List<Zhishiku> countList();
	
	/**
	 * 分页查询知识库
	 * @return
	 */
	public List<Zhishiku> list(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 通过Id查找实体
	 * @param id
	 * @return
	 */
	public Zhishiku findById(Integer id);
	
	/**
	 * 更新知识库信息
	 * @param Zhishiku
	 * @return
	 */
	public Integer update(Zhishiku Zhishiku); 
	
	/**
	 * 获取上一个知识库
	 * @param id
	 * @return
	 */
	public Zhishiku getLastZhishiku(Integer id);
	
	/**
	 * 获取下一个知识库
	 * @param id
	 * @return
	 */
	public Zhishiku getNextZhishiku(Integer id);
	
	/**
	 * 添加知识库信息
	 * @param Zhishiku
	 * @return
	 */
	public Integer add(Zhishiku Zhishiku);
	
	/**
	 * 删除知识库信息
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
	
	/**
	 * 查询指定的知识库类别下的知识库数量
	 * @param typeId
	 * @return
	 */
	public Integer getZhishikuByTypeId(Integer typeId);

	public int getCount(Zhishiku zhishiku);

	public List<Zhishiku> getAll(Zhishiku zhishiku);

	public int delById(int parseInt);
	
	
}
