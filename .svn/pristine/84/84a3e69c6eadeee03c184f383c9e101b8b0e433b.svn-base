package com.inteall.image.dao;


import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.Collection;
import com.inteall.image.pojo.CollectionWithBLOBs;
import com.inteall.image.pojo.ConsultationWithBLOBs;
import com.inteall.image.pojo.MedicalRecord;

/**
 * @author 刘天诚
 * 
 * 创建时间：2018年2月7日-下午8:10:14
 */
public interface CollectionDao {
	/**
	 * 增加收藏夹
	 * @author 刘天诚
	 * 
	 * 创建时间：2018年2月7日-下午8:10:14
	 */
	int insertCollection(Collection collection);
	/**
	 * 修改收藏夹
	 * @author 刘天诚
	 * 
	 * 创建时间：2018年2月7日-下午8:10:14
	 */
	int update(Collection collection);
	/**
	 * 根据id查找
	 * @author 刘天诚
	 * 
	 * 创建时间：2018年2月7日-下午8:10:14
	 */
	Collection getById(String id);
	/**
	 * 删除
	 * @author 刘天诚
	 * 
	 * 创建时间：2018年2月7日-下午8:10:14
	 */
	int delCollection(Collection collection);
	/**
	 * 查询所有信息
	 * @author 刘天诚
	 * 
	 * 创建时间：2018年2月7日-下午8:10:14
	 */
	List<Collection> getAll(String createId);
	/**
	 * 查询当前目录下的子目录
	 * @author 于津名
	 * 
	 * 创建时间：2018年4月23日-下午4:10:14
	 */
	List<Collection> getParentKeyCollection(String collectionKey);
	/**
	 * 查询当前目录名称
	 * @author 于津名
	 * 
	 * 创建时间：2018年4月23日-下午4:10:14
	 */
	Collection getCollectionName(String collectionKey);
	
	int getMedicalCount(HashMap<String, Object> queryMap);
	
	List<MedicalRecord> getRecordBycollectionKey(HashMap<String, Object> queryMap);
	
	
	
}
