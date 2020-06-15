package com.inteall.image.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.inteall.image.pojo.ConsultationWithBLOBs;
import com.inteall.image.pojo.MedicalLog;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.service.ConsultationService;
import com.inteall.image.service.MedicalLogService;
import com.inteall.image.service.MedicalRecordService;
import com.inteall.image.util.UUid;
import com.inteall.image.util.Util;


@Controller
@RequestMapping(value="/admin/receiveHttp")
public class receiveHttp {
	  @Resource
	  private MedicalRecordService medicalRecordService;
	  @Resource
	  private ConsultationService consulTationService;
	  @Resource
	  private MedicalLogService medicalLogService;
		/**
		 * 影像文件上传
		 * @param files
		 * @return
		 */
		@RequestMapping(value="/uploadPic")
		@ResponseBody
		public Map<String, Object> uploadImg(@RequestParam("dcmImage") MultipartFile files,MultipartHttpServletRequest request) {
			Map<String, Object> result = new HashMap<String, Object>();
			String userID = request.getParameter("userID");
			String userName = request.getParameter("userName");
			// 上传的文件保存路径
			Properties prop = Util.readData("path.properties");
			String filePath = prop.getProperty("dcmPath").trim();// 获取文件保存路径
			String newFileName = "";
			newFileName = files.getOriginalFilename();// 获取文件名称
			String newFilePath = filePath + newFileName;// 新路径
			File newFile = new File(newFilePath);
			if (!newFile.exists()) {
				//String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
				newFileName = files.getOriginalFilename();
				// 上传的dcm文件解析为dcm对象
				DicomObject dcm = dicom(files);
		        if (dcm == null) {// 说明不是dcm文件 直接不存储，返回错误
		        	result.put("code", 1);
		            return result;
		        }
		        synchronized(this){
		        // 判断该dcm图像是否上传过
		        int m = medicalRecordService.getCountByStudy(dcm.getString(Tag.StudyInstanceUID));
		        ConsultationWithBLOBs consultation = new ConsultationWithBLOBs();
		        if (m == 0) {
		            MedicalRecord medicalRecord = new MedicalRecord();
		            medicalRecord.setAge(dcm.getString(Tag.PatientAge));// 患者年龄
		            // medicalRecord.setBodypartCode(dcm.getString(Tag.));
		            medicalRecord.setBodypartName(dcm.getString(Tag.BodyPartExamined));// 检查部位
		            medicalRecord.setFilmNum(dcm.getString(Tag.NumberOfFilms));// 胶片数量
		            // medicalRecord.setFilmTime(dcm.getString(Tag.));
		            // medicalRecord.setHospital(dcm.getString(Tag.));
		            // medicalRecord.setImageNum(dcm.getString(Tag.));
		            String medicalRecordId = UUid.getUUID();
		            medicalRecord.setMedicalRecordKey(medicalRecordId);
		            medicalRecord.setModalityCode(dcm.getString(Tag.Modality));// 图像类型
		            medicalRecord.setPatientHistory(dcm.getString(Tag.AdditionalPatientHistory));// 额外病史
		            medicalRecord.setPatientKey(dcm.getString(Tag.PatientID));// 病人id
		            medicalRecord.setPatientName(dcm.getString(Tag.PatientName));// 病人姓名
		            // medicalRecord.setPatientType(dcm.getString(Tag.PatientSupportType));//
		            // 病人类型
		            // medicalRecord.setPrintFlag(dcm.getString(Tag.));
		            // medicalRecord.setPrintReport(dcm.getString(Tag.));
		            // medicalRecord.setPrintTime(dcm.getString(Tag.));
		            medicalRecord.setSeriesinstanceuid(dcm.getString(Tag.SeriesInstanceUID));// 序列id
		            medicalRecord.setSeriesNum(dcm.getInt(Tag.SeriesNumber));// 几个序列
		            medicalRecord.setSex(dcm.getString(Tag.PatientSex));// 病人性别
		            // medicalRecord.setState(dcm.getString(Tag.));
		            medicalRecord.setStudyId(dcm.getString(Tag.StudyID));// 检查id
		            medicalRecord.setStudyinstanceuid(dcm.getString(Tag.StudyInstanceUID));// 检查uid
		            medicalRecord.setStudyTime(dcm.getString(Tag.StudyDate));// 检查日期
		            consultation.setImageId(medicalRecordId);
		            medicalRecordService.save(medicalRecord);
		        } else {
		        	MedicalRecord record = medicalRecordService.getById(dcm.getString(Tag.StudyInstanceUID));
		            consultation.setImageId(record.getMedicalRecordKey());
		        }
		        // 获取当前用户下该病例的会诊状态
		        List<String> states = consulTationService.getStateByStuuid(dcm.getString(Tag.StudyInstanceUID),userID);

		        boolean saveDcm = true;// 是否可以上传dcm文件 如果该studyuid 有在会诊过程中则不允许上传dcm文件
		        for (String state : states) {// 遍历表中所有该stuyuid病历的状态
		            // 1-未提交、2-申请驳回、3-待会诊、4--初审驳回、5-终审驳回、6待初审、7-待终审、8-已完成
		            if (state.equals("3") || state.equals("4") || state.equals("5") || state.equals("6") || state.equals("7")) {
		                saveDcm = false;
		                break;
		            }
		        }
		        
		        boolean state3 = false;// 判断是否存在状态为1或2的，是否新增病历
		        for (String state : states) {
		            // 1-未提交、2-申请驳回、3-待会诊、4--初审驳回、5-终审驳回、6待初审、7-待终审、8-已完成
		            if (state.equals("2") || state.equals("1")) {
		                state3 = true;
		                break;
		            }
		        }
		        if (saveDcm) {
		            
		            if (!state3) {// 如果不存在未提交或者驳回的情况，则新增一个病历
		                consultation.setCaseId(UUid.getUUID());
		                consultation.setCreatePerson(userID);
		                consultation.setCreatePersonName(userName);
		                consultation.setCreateTime(new Date());
		                consultation.setIsDel("0");
		                consultation.setState("1");
		                int i = consulTationService.save(consultation);
		                if (i > 0) {
		                    MedicalLog medicalLog = new MedicalLog();
		                    medicalLog.setSyslogKey(UUid.getUUID());
		                    medicalLog.setLogtime(new Date());
		                    medicalLog.setLogtype("上传病例");
		                    medicalLog.setLoguser(userName);
		                    medicalLog.setLoguserCode(userName);
		                    medicalLog.setCreatePerson(userID);
		                    medicalLog.setCreateTime(new Date());
		                    medicalLog.setStudyId(dcm.getString(Tag.StudyInstanceUID));
		                    medicalLog.setIsDel("0");
		                    int n=medicalLogService.save(medicalLog);
		                    if(n > 0){
		                    	result.put("code", 0);
		                    }else{
		                    	result.put("code", 1);
		                        result.put("msg", "插入日志出错，请稍后重试或者联系管理员！");
		                    }
		                    result.put("code", 0);
		                } else {
		                	result.put("code", 1);
		                    result.put("msg", "上传dcm文件出错，请稍后重试或者联系管理员！");
		                }
		            } else {// 如果存在未提交或者驳回的情况，则只上传dcm文件
		            	result.put("code", 0);
		            }
		        } else {
		        	result.put("code", 2);
		            result.put("msg", "病历正在会诊中,不允许上传该病历的影像！");
		            return result;
		        }
		       }
			}
			try {
				files.transferTo(newFile);
			} catch (Exception e) {
			    e.printStackTrace();
				result.put("msg", "上传失败!"+e.getMessage());
			};
			if(StringUtils.isEmpty(newFileName)){
				result.put("result", 1); 
				result.put("msg", newFileName+":上传失败!");
			}else{
				result.put("result", 0); 
				result.put("msg", newFileName+":上传成功!");
			}
			return result;
		}  
		/**
	     * dimcom 文件解析
	     * @param args
	     */
	    public  DicomObject dicom(MultipartFile mpf){
	        DicomObject dcmObj = null;
	        DicomInputStream din=null;
	        InputStream inputStream = null;
	        try {
	            inputStream = mpf.getInputStream();
	            din = new DicomInputStream(inputStream);  
	            dcmObj = din.readDicomObject();
	            return dcmObj;
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            return null;
	        } finally{  
	            try {
	                if(din!=null){
	                    din.close();
	                }else{
	                    return null;
	                }
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	    }
}
