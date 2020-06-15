package com.inteall.image.dao;

import java.util.HashMap;
import java.util.List;


import com.inteall.image.pojo.CollectionMedicalRecord;
import com.inteall.image.pojo.CollectionWithBLOBs;
import com.inteall.image.pojo.MedicalRecord;

/**
 * @author 刘天诚
 * 
 * 创建时间：2018年2月27日-上午10:01:22
 */

public interface CollectionMedicalRecordDao {
	/**
	 * @author 刘天诚
	 * 插入
	 * 创建时间：2018年2月28日-上午16:01:22
	 */
	int insertCollectionMedicalRecord(CollectionMedicalRecord collectionMedicalRecord);
	/**
	 * @author 刘天诚
	 * 插入
	 * 创建时间：2018年3月14日-上午16:01:22
	 */
	int insertMedicalRecord(MedicalRecord medicalRecord);
	/**
	 * @author 刘天诚
	 * 查询
	 * 创建时间：2018年2月27日-上午10:01:22
	 */
	List<CollectionMedicalRecord> getMedicalRecordList(CollectionMedicalRecord collectionMedicalRecord);
	
	/**
	 * @param 刘天诚
	 * 查询
	 * 创建时间：2018年2月27日-上午15:01:22
	 */
	int update(CollectionMedicalRecord collectionMedicalRecord);
	/**
	 * @param 刘天诚
	 * 查询
	 * 创建时间：2018年2月28日-上午11:01:22
	 */
	int deleteCollectionMedicalRecord(CollectionMedicalRecord collectionMedicalRecord);
	
	int getByStuuid(HashMap<String, String> getParamMap);
	
	int getCount(HashMap<String, Object> queryMap);
	
	List<CollectionMedicalRecord> getByCollectionKey(HashMap<String, Object> queryMap);
	
	int delByStuuid(HashMap<String, String> getParamMap);
	
	CollectionMedicalRecord getcollectionMedicalRecordById(String collectionKey);
	
	int updateBycollectionKey(CollectionMedicalRecord collectionMedicalRecord);
	
	String getRecordById(CollectionMedicalRecord collectionMedicalRecord);
}
