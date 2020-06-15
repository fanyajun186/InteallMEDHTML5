package com.inteall.image.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ConsultationDao;
import com.inteall.image.pojo.Consultation;
import com.inteall.image.pojo.ConsultationWithBLOBs;
import com.inteall.image.service.ConsultationService;

/**
 * @author 韩明君
 * @date 2018年2月24日 上午9:41:02
 * @version 1.0 
 * @parameter 
 */

@Service("ConsultationService")
public class ConsultationServiceImpl  implements ConsultationService {
    @Resource
    private ConsultationDao ConsultationDao;

    @Override
    public List<String> getStateByStuuid(String stuuid, String userid) {
        // TODO Auto-generated method stub
        return ConsultationDao.getStateByStuuid(stuuid, userid);
    }

    @Override
    public int save(ConsultationWithBLOBs consultation) {
        // TODO Auto-generated method stub
        return ConsultationDao.save(consultation);
    }

    @Override
    public int getMedicalCount(HashMap<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return ConsultationDao.getMedicalCount(queryMap);
    }

    @Override
    public List<ConsultationWithBLOBs> getRecordByCreateUser(HashMap<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return ConsultationDao.getRecordByCreateUser(queryMap);
    }

    @Override
    public int updateByCaseId(ConsultationWithBLOBs consultation) {
        // TODO Auto-generated method stub
        return ConsultationDao.updateByCaseId(consultation);
    }

    @Override
    public int getCountByDoctor(HashMap<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return ConsultationDao.getCountByDoctor(queryMap);
    }

    @Override
    public List<ConsultationWithBLOBs> getRecordByDoctor(HashMap<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return ConsultationDao.getRecordByDoctor(queryMap);
    }

    @Override
    public ConsultationWithBLOBs getConsulTationById(String caseId) {
        // TODO Auto-generated method stub
        return ConsultationDao.getConsulTationById(caseId);
    }

    @Override
    public int getCountByPrimaryAudit(HashMap<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return ConsultationDao.getCountByPrimaryAudit(queryMap);
    }

    @Override
    public List<ConsultationWithBLOBs> getRecordByPrimaryAudit(HashMap<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return ConsultationDao.getRecordByPrimaryAudit(queryMap);
    }

    @Override
    public int getCountByUltimateAudit(HashMap<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return ConsultationDao.getCountByUltimateAudit(queryMap);
    }

    @Override
    public List<ConsultationWithBLOBs> getRecordByUltimateAudit(HashMap<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return ConsultationDao.getRecordByUltimateAudit(queryMap);
    }

	@Override
	public int saveById(Consultation consultation) {
		// TODO Auto-generated method stub
		return ConsultationDao.saveById(consultation);
	}

	@Override
	public int updateById(Consultation consultation) {
		// TODO Auto-generated method stub
		return ConsultationDao.updateById(consultation);
	}

	@Override
	public Consultation getById(Consultation consultation) {
		// TODO Auto-generated method stub
		return ConsultationDao.getById(consultation);
	}

	@Override
	public int delByStuuid(HashMap<String, String> getParamMap) {
		// TODO Auto-generated method stub
		return ConsultationDao.delByStuuid(getParamMap);
	}

    @Override
    public List<Consultation> getByConferee(Consultation consultation) {
        // TODO Auto-generated method stub
        return ConsultationDao.getByConferee(consultation);
    }
    //获取部位
	@Override
	public List<Map> getbodyPartName() {
		return ConsultationDao.getbodyPartName();
	}
	
	//保存病史附件信息
	@Override
	public int addHistoryFile(Map<String, Object> map) {
		return ConsultationDao.addHistoryFile(map);
	}

}
