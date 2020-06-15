package com.inteall.image.pojo;

import java.util.Date;
import java.util.List;

public class VideoMedicalRecord {
    private String videoMedicalRecordKey;

    private String videoKey;

    private String RecordKey;

    private String sysuserKey;

    private Date appendTime;

    private String videoMedicalRecordRemark1;

    private String videoMedicalRecordRemark2;

    private String videoMedicalRecordRemark3;
    
    private List<MedicalRecord> medicalRecord;
    private int limit;//每页显示多少行
    private int curr;//分页查询，从哪行开始查询
	public String getVideoMedicalRecordKey() {
		return videoMedicalRecordKey;
	}
	public void setVideoMedicalRecordKey(String videoMedicalRecordKey) {
		this.videoMedicalRecordKey = videoMedicalRecordKey;
	}
	public String getVideoKey() {
		return videoKey;
	}
	public void setVideoKey(String videoKey) {
		this.videoKey = videoKey;
	}
	public String getRecordKey() {
		return RecordKey;
	}
	public void setRecordKey(String recordKey) {
		RecordKey = recordKey;
	}
	public String getSysuserKey() {
		return sysuserKey;
	}
	public void setSysuserKey(String sysuserKey) {
		this.sysuserKey = sysuserKey;
	}
	public Date getAppendTime() {
		return appendTime;
	}
	public void setAppendTime(Date appendTime) {
		this.appendTime = appendTime;
	}
	public String getVideoMedicalRecordRemark1() {
		return videoMedicalRecordRemark1;
	}
	public void setVideoMedicalRecordRemark1(String videoMedicalRecordRemark1) {
		this.videoMedicalRecordRemark1 = videoMedicalRecordRemark1;
	}
	public String getVideoMedicalRecordRemark2() {
		return videoMedicalRecordRemark2;
	}
	public void setVideoMedicalRecordRemark2(String videoMedicalRecordRemark2) {
		this.videoMedicalRecordRemark2 = videoMedicalRecordRemark2;
	}
	public String getVideoMedicalRecordRemark3() {
		return videoMedicalRecordRemark3;
	}
	public void setVideoMedicalRecordRemark3(String videoMedicalRecordRemark3) {
		this.videoMedicalRecordRemark3 = videoMedicalRecordRemark3;
	}
	public List<MedicalRecord> getMedicalRecord() {
		return medicalRecord;
	}
	public void setMedicalRecord(List<MedicalRecord> medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getCurr() {
		return curr;
	}
	public void setCurr(int curr) {
		this.curr = curr;
	}
    
   
}