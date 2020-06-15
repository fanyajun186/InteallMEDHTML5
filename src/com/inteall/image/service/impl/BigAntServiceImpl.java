package com.inteall.image.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.inteall.image.dao.BigAntDao;
import com.inteall.image.pojo.BigAnt;
import com.inteall.image.pojo.BigAntUser;
import com.inteall.image.pojo.Group;
import com.inteall.image.pojo.GroupMeidicalRecord;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.service.BigAntService;

@Service("BigAntService")
public class BigAntServiceImpl implements BigAntService{
	
	@Resource
	private BigAntDao bigAntDao;

	@Override
	public List<BigAnt> getGroupInfoByUserId(String user_id) {

		return bigAntDao.getGroupInfoByUserId(user_id);
	}

	@Override
	public List<BigAntUser> getUserByGroupId(String groupId) {
	  // TODO Auto-generated method stub
	  return bigAntDao.getUserByGroupId(groupId);
	}

	@Override
	public int getByStuuid(HashMap<String, String> getParamMap) {
	  // TODO Auto-generated method stub
	  return bigAntDao.getByStuuid(getParamMap);
	}

	@Override
	public int save(GroupMeidicalRecord groupMeidicalRecord) {
	  // TODO Auto-generated method stub
	  return bigAntDao.save(groupMeidicalRecord);
	}

	@Override
	public List<MedicalRecord> getRecordByGroupId(HashMap<String, Object> queryMap) {
	  // TODO Auto-generated method stub
	  return bigAntDao.getRecordByGroupId(queryMap);
	}

	@Override
	public int getMedicalCount(HashMap<String, Object> queryMap) {
	  // TODO Auto-generated method stub
	  return bigAntDao.getMedicalCount(queryMap);
	}

	@Override
	public String getRecordById(GroupMeidicalRecord groupMeidicalRecord) {
		// TODO Auto-generated method stub
		return bigAntDao.getRecordById(groupMeidicalRecord);
	}

    @Override
    public List<Group> getGroupListByLogin(String loginName) {
        // TODO Auto-generated method stub
        return bigAntDao.getGroupListByLogin(loginName);
    }
}
