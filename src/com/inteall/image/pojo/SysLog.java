package com.inteall.image.pojo;

import java.util.Date;

public class SysLog {
    private String syslogKey;

    private String bodypartName;

    private String logclass;

    private String logip;

    private Date logtime;

    private String logtype;

    private String loguser;

    private String loguserCode;

    private String modalityCode;

    private String patientId;

    private String patientKey;

    private String medicalRecordKey;

    private String studyId;

    private String patientName;

    private String createPerson;

    private Date createTime;

    private Date modifyTime;

    private String modifyPerson;

    private String isDel;

    private Date delTime;

    private String delPerson;

    private String syslogRemark1;

    private String syslogRemark2;

    private String syslogRemark3;

    public String getSyslogKey() {
        return syslogKey;
    }

    public void setSyslogKey(String syslogKey) {
        this.syslogKey = syslogKey == null ? null : syslogKey.trim();
    }

    public String getBodypartName() {
        return bodypartName;
    }

    public void setBodypartName(String bodypartName) {
        this.bodypartName = bodypartName == null ? null : bodypartName.trim();
    }

    public String getLogclass() {
        return logclass;
    }

    public void setLogclass(String logclass) {
        this.logclass = logclass == null ? null : logclass.trim();
    }

    public String getLogip() {
        return logip;
    }

    public void setLogip(String logip) {
        this.logip = logip == null ? null : logip.trim();
    }

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }

    public String getLogtype() {
        return logtype;
    }

    public void setLogtype(String logtype) {
        this.logtype = logtype == null ? null : logtype.trim();
    }

    public String getLoguser() {
        return loguser;
    }

    public void setLoguser(String loguser) {
        this.loguser = loguser == null ? null : loguser.trim();
    }

    public String getLoguserCode() {
        return loguserCode;
    }

    public void setLoguserCode(String loguserCode) {
        this.loguserCode = loguserCode == null ? null : loguserCode.trim();
    }

    public String getModalityCode() {
        return modalityCode;
    }

    public void setModalityCode(String modalityCode) {
        this.modalityCode = modalityCode == null ? null : modalityCode.trim();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getPatientKey() {
        return patientKey;
    }

    public void setPatientKey(String patientKey) {
        this.patientKey = patientKey == null ? null : patientKey.trim();
    }

    public String getMedicalRecordKey() {
        return medicalRecordKey;
    }

    public void setMedicalRecordKey(String medicalRecordKey) {
        this.medicalRecordKey = medicalRecordKey == null ? null : medicalRecordKey.trim();
    }

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId == null ? null : studyId.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyPerson() {
        return modifyPerson;
    }

    public void setModifyPerson(String modifyPerson) {
        this.modifyPerson = modifyPerson == null ? null : modifyPerson.trim();
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getDelPerson() {
        return delPerson;
    }

    public void setDelPerson(String delPerson) {
        this.delPerson = delPerson == null ? null : delPerson.trim();
    }

    public String getSyslogRemark1() {
        return syslogRemark1;
    }

    public void setSyslogRemark1(String syslogRemark1) {
        this.syslogRemark1 = syslogRemark1 == null ? null : syslogRemark1.trim();
    }

    public String getSyslogRemark2() {
        return syslogRemark2;
    }

    public void setSyslogRemark2(String syslogRemark2) {
        this.syslogRemark2 = syslogRemark2 == null ? null : syslogRemark2.trim();
    }

    public String getSyslogRemark3() {
        return syslogRemark3;
    }

    public void setSyslogRemark3(String syslogRemark3) {
        this.syslogRemark3 = syslogRemark3 == null ? null : syslogRemark3.trim();
    }
}