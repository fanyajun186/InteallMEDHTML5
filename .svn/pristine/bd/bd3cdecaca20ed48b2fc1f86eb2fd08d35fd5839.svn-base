package com.inteall.image.dao;

import java.util.HashMap;
import java.util.List;


import com.inteall.image.pojo.BigAnt;
import com.inteall.image.pojo.BigAntUser;
import com.inteall.image.pojo.Group;
import com.inteall.image.pojo.GroupMeidicalRecord;
import com.inteall.image.pojo.MedicalRecord;


/**
 * @author 于津名
 * @date 2018年3月27日 
 * @version 1.0
 * @parameter
 */

public interface BigAntDao {
	

	List<BigAnt> getGroupInfoByUserId(String user_id);

	List<BigAntUser> getUserByGroupId(String groupId);

	int getByStuuid(HashMap<String, String> getParamMap);

	int save(GroupMeidicalRecord groupMeidicalRecord);

	List<MedicalRecord> getRecordByGroupId(HashMap<String, Object> queryMap);

	int getMedicalCount(HashMap<String, Object> queryMap);

	String getRecordById(GroupMeidicalRecord groupMeidicalRecord);

    List<Group> getGroupListByLogin(String loginName);
	
}
