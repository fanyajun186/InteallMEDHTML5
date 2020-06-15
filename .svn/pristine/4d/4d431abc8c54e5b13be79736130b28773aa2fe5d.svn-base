package com.inteall.image.service;

import java.util.List;
import java.util.Map;

import com.inteall.image.pojo.Zhishiku;


/**
 * 知识库Service接口
 * @author Administrator
 *
 */
public interface ZhishikuService {

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
	
	/**
	 * 更新知识库信息
	 * @param zhishiku
	 * @return
	 */
	public Integer update(Zhishiku zhishiku); 
	
	/**
	 * 获取上一个知识库
	 * @param id
	 * @return
	 */
	public Zhishiku getLastzhishiku(Integer id);
	
	/**
	 * 获取下一个知识库
	 * @param id
	 * @return
	 */
	public Zhishiku getNextzhishiku(Integer id);
	
	/**
	 * 添加知识库信息
	 * @param zhishiku
	 * @return
	 */
	public Integer add(Zhishiku zhishiku);
	
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
	public Integer getzhishikuByTypeId(Integer typeId);

	public int getCount(Zhishiku zhishiku);

	public List<Zhishiku> getAll(Zhishiku zhishiku);

	public Zhishiku findById(int parseInt);

	public int delById(int parseInt);
}
