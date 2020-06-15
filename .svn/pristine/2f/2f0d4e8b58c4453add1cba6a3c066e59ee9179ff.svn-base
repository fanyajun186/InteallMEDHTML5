package com.inteall.image.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inteall.image.pojo.Consultation;
import com.inteall.image.pojo.ConsultationWithBLOBs;

/**
 * @author 韩明君
 * @date 2018年2月24日 上午9:44:01
 * @version 1.0 
 * @parameter 
 */

public interface ConsultationService {

  List<String> getStateByStuuid(String stuuid,String userid);

  int save(ConsultationWithBLOBs consultation);

  int getMedicalCount(HashMap<String, Object> queryMap);

  List<ConsultationWithBLOBs> getRecordByCreateUser(HashMap<String, Object> queryMap);

  int updateByCaseId(ConsultationWithBLOBs consultation);

int getCountByDoctor(HashMap<String, Object> queryMap);

List<ConsultationWithBLOBs> getRecordByDoctor(HashMap<String, Object> queryMap);

ConsultationWithBLOBs getConsulTationById(String caseId);

int getCountByPrimaryAudit(HashMap<String, Object> queryMap);

List<ConsultationWithBLOBs> getRecordByPrimaryAudit(HashMap<String, Object> queryMap);

int getCountByUltimateAudit(HashMap<String, Object> queryMap);

List<ConsultationWithBLOBs> getRecordByUltimateAudit(HashMap<String, Object> queryMap);

int saveById(Consultation consultation);

int updateById(Consultation consultation);

Consultation getById(Consultation consultation);

int delByStuuid(HashMap<String, String> getParamMap);

List<Consultation> getByConferee(Consultation consultation);

List<Map> getbodyPartName();

//保存病史附件信息
int addHistoryFile(Map<String, Object> map);

  
}
