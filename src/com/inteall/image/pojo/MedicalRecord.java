package com.inteall.image.pojo;

import java.util.Date;
import java.util.List;

public class MedicalRecord {
    private String medicalRecordKey;

    private String patientKey;

    private String patientName;

    private String sex;

    private String age;

    private String patientType;

    private String bodypartCode;

    private String bodypartName;

    private String hospital;

    private Integer imageNum;

    private String modalityCode;

    private Boolean printFlag;

    private Date filmTime;

    private String filmNum;

    private Boolean printReport;

    private Date printTime;

    private String seriesinstanceuid;

    private String studyId;

    private Integer seriesNum;

    private String studyinstanceuid;

    private String studyTime;

    private String state;

    private String patientHistory;
    
    private List<CollectionMedicalRecord> collectionMedicalRecord;
    
    
	public List<CollectionMedicalRecord> getCollectionMedicalRecord() {
		return collectionMedicalRecord;
	}

	public void setCollectionMedicalRecord(List<CollectionMedicalRecord> collectionMedicalRecord) {
		this.collectionMedicalRecord = collectionMedicalRecord;
	}

	public String getMedicalRecordKey() {
        return medicalRecordKey;
    }

    public void setMedicalRecordKey(String medicalRecordKey) {
        this.medicalRecordKey = medicalRecordKey == null ? null : medicalRecordKey.trim();
    }

    public String getPatientKey() {
        return patientKey;
    }

    public void setPatientKey(String patientKey) {
        this.patientKey = patientKey == null ? null : patientKey.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType == null ? null : patientType.trim();
    }

    public String getBodypartCode() {
        return bodypartCode;
    }

    public void setBodypartCode(String bodypartCode) {
        this.bodypartCode = bodypartCode == null ? null : bodypartCode.trim();
    }

    public String getBodypartName() {
        return bodypartName;
    }

    public void setBodypartName(String bodypartName) {
        this.bodypartName = bodypartName == null ? null : bodypartName.trim();
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital == null ? null : hospital.trim();
    }

    public Integer getImageNum() {
        return imageNum;
    }

    public void setImageNum(Integer imageNum) {
        this.imageNum = imageNum;
    }

    public String getModalityCode() {
        return modalityCode;
    }

    public void setModalityCode(String modalityCode) {
        this.modalityCode = modalityCode == null ? null : modalityCode.trim();
    }

    public Boolean getPrintFlag() {
        return printFlag;
    }

    public void setPrintFlag(Boolean printFlag) {
        this.printFlag = printFlag;
    }

    public Date getFilmTime() {
        return filmTime;
    }

    public void setFilmTime(Date filmTime) {
        this.filmTime = filmTime;
    }

    public String getFilmNum() {
        return filmNum;
    }

    public void setFilmNum(String filmNum) {
        this.filmNum = filmNum == null ? null : filmNum.trim();
    }

    public Boolean getPrintReport() {
        return printReport;
    }

    public void setPrintReport(Boolean printReport) {
        this.printReport = printReport;
    }

    public Date getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

    public String getSeriesinstanceuid() {
        return seriesinstanceuid;
    }

    public void setSeriesinstanceuid(String seriesinstanceuid) {
        this.seriesinstanceuid = seriesinstanceuid == null ? null : seriesinstanceuid.trim();
    }

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId == null ? null : studyId.trim();
    }

    public Integer getSeriesNum() {
        return seriesNum;
    }

    public void setSeriesNum(Integer seriesNum) {
        this.seriesNum = seriesNum;
    }

    public String getStudyinstanceuid() {
        return studyinstanceuid;
    }

    public void setStudyinstanceuid(String studyinstanceuid) {
        this.studyinstanceuid = studyinstanceuid == null ? null : studyinstanceuid.trim();
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPatientHistory() {
        return patientHistory;
    }

    public void setPatientHistory(String patientHistory) {
        this.patientHistory = patientHistory == null ? null : patientHistory.trim();
    }
}