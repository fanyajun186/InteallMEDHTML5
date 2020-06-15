package com.inteall.image.service;

import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.CollectionMedicalRecord;
import com.inteall.image.pojo.CollectionWithBLOBs;
import com.inteall.image.pojo.MedicalRecord;

public interface CollectionMedicalRecordService {
	public List<CollectionMedicalRecord> getMedicalRecordList(CollectionMedicalRecord collectionMedicalRecord);
	
	public int update(CollectionMedicalRecord collectionMedicalRecord);
	
	public int deleteCollectionMedicalRecord(CollectionMedicalRecord collectionMedicalRecord);
	
	public int insertCollectionMedicalRecord(CollectionMedicalRecord collectionMedicalRecord);
	
	public int insertMedicalRecord(MedicalRecord medicalRecord);
	
	public int getByStuuid(HashMap<String, String> getParamMap);

	public int getCount(HashMap<String, Object> queryMap);

	public List<CollectionMedicalRecord> getByCollectionKey(HashMap<String, Object> queryMap);

	public int delByStuuid(HashMap<String, String> getParamMap);

	public CollectionMedicalRecord getcollectionMedicalRecordById(String medicalRecordKey, String collectionKey);

	public int updateBycollectionKey(CollectionMedicalRecord collectionMedicalRecord);

	public String getRecordById(CollectionMedicalRecord collectionMedicalRecord);



}
