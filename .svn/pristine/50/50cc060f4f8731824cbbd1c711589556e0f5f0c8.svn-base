package com.inteall.image.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.inteall.image.pojo.Consultation;
import com.inteall.image.pojo.ConsultationWithBLOBs;

/** 
* @author 韩明君
* @date 创建时间：2018年2月23日 下午13:40:00 
* @version 1.0 
* @parameter  
*/
public interface ConsultationDao {

  List<String> getStateByStuuid(@Param("stuuid") String stuuid,@Param("userid") String userid);

  int save(ConsultationWithBLOBs consultation);

  int getMedicalCount(HashMap<String, Object> queryMap);

  List<ConsultationWithBLOBs> getRecordByCreateUser(HashMap<String, Object> queryMap);

  int updateByCaseId(ConsultationWithBLOBs consultation);

int getCountByDoctor(HashMap<String, Object> queryMap);

List<ConsultationWithBLOBs> getRecordByDoctor(HashMap<String, Object> queryMap);

ConsultationWithBLOBs getConsulTationById(String caseId);

List<ConsultationWithBLOBs> getRecordByPrimaryAudit(HashMap<String, Object> queryMap);

int getCountByPrimaryAudit(HashMap<String, Object> queryMap);

int getCountByUltimateAudit(HashMap<String, Object> queryMap);

List<ConsultationWithBLOBs> getRecordByUltimateAudit(HashMap<String, Object> queryMap);

int saveById(Consultation consultation);

int updateById(Consultation consultation);

Consultation getById(Consultation consultation);

int delByStuuid(HashMap<String, String> getParamMap);

List<Consultation> getByConferee(Consultation consultation);

List<Map> getbodyPartName();

int addHistoryFile(Map<String, Object> map);

}
