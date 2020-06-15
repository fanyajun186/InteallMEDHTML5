package com.inteall.image.dao;

import java.util.List;
import java.util.Map;

import com.inteall.image.pojo.ZhishikuType;


/**
 * 知识库类型Dao接口
 * @author Administrator
 *
 */
public interface ZhishikuTypeDao {

	/**
	 * 查询所有知识库类型 以及对应的知识库数量
	 * @return
	 */
	public List<ZhishikuType> countList();
	
	/**
	 * 通过id查询知识库类型
	 * @param id
	 * @return
	 */
	public ZhishikuType findById(Integer id);
	
	/**
	 * 分页查询知识库类别信息
	 * @param map
	 * @return
	 */
	public List<ZhishikuType> list();
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加知识库类别信息
	 * @param ZhishikuType
	 * @return
	 */
	public Integer add(ZhishikuType ZhishikuType);
	
	/**
	 * 修改知识库类别信息
	 * @param ZhishikuType
	 * @return
	 */
	public Integer update(ZhishikuType ZhishikuType);
	
	/**
	 * 删除知识库类别信息
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);

	/**
     * 查询知识库类别所有的父节点
     */
    public List<ZhishikuType> parentlist();

    /**
     * 查询知识库类别所有的子节点
     */
    public List<ZhishikuType> childlist();

	public List<ZhishikuType> childlistByparentId(String isParent);

    public List<ZhishikuType> getParentByName(Map<String, Object> querymap);

    public int getCountParentByName(Map<String, Object> querymap);

    public List<ZhishikuType> getChild(Map<String, Object> querymap);

    public int getCountChild(Map<String, Object> querymap);

    public ZhishikuType getById(Map<String, Object> querymap);

}
