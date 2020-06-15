package com.inteall.image.pojo;

import java.util.Date;

public class PersonToPerson extends MedicalRecord{
    private String personToPersonKey;

    private String medicalRecordKey;

    private String targetPerson;

    private String createPerson;

    private Date createTime;
    
    private String createName;
    
    private String beShareName;

    public String getPersonToPersonKey() {
        return personToPersonKey;
    }

    public void setPersonToPersonKey(String personToPersonKey) {
        this.personToPersonKey = personToPersonKey == null ? null : personToPersonKey.trim();
    }

    public String getMedicalRecordKey() {
        return medicalRecordKey;
    }

    public void setMedicalRecordKey(String medicalRecordKey) {
        this.medicalRecordKey = medicalRecordKey == null ? null : medicalRecordKey.trim();
    }

    public String getTargetPerson() {
        return targetPerson;
    }

    public void setTargetPerson(String targetPerson) {
        this.targetPerson = targetPerson == null ? null : targetPerson.trim();
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

	public String getBeShareName() {
		return beShareName;
	}

	public void setBeShareName(String beShareName) {
		this.beShareName = beShareName;
	}
    
}