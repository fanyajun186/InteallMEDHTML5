package com.inteall.image.pojo;

import java.util.Date;

public class Intervention extends MedicalRecord{
    private String interventionKey;

    private String medicalRecordKey;

    private String reportKey;

    private String zoomKey;

    private String state;

    private Date modifyTime;

    private String createPerson;

    private Date createTime;

    private Date commitTime;

    private String commitType;

    private String groupId;

    private String commitTargetPerson;

    private String isAccept;

    private String acceptPersonName;

    private String acceptPersonLogin;

    private Date acceptTime;

    private Date rejectTime;

    private Date reportCommitTime;

    private String primaryAuditName;

    private String primaryAuditLogin;

    private Date primaryTime;

    private String primarySuggestion;

    private Date primaryRejectTime;

    private String ultimateAuditName;

    private String ultimateAuditLogin;

    private Date ultimateTime;

    private String ultimateSuggestion;

    private Date ultimateRejectTime;

    private String interventionType;

    private Integer minute;

    private String isDel;

    private Date delTime;

    private String interventionRemark1;

    private String interventionRemark2;

    private String interventionRemark3;

    private String interventionRemark4;

    private String interventionRemark5;

    private String interventionRemark6;

    public String getinterventionKey() {
        return interventionKey;
    }

    public void setinterventionKey(String interventionKey) {
        this.interventionKey = interventionKey == null ? null : interventionKey.trim();
    }

    public String getMedicalRecordKey() {
        return medicalRecordKey;
    }

    public void setMedicalRecordKey(String medicalRecordKey) {
        this.medicalRecordKey = medicalRecordKey == null ? null : medicalRecordKey.trim();
    }

    public String getReportKey() {
        return reportKey;
    }

    public void setReportKey(String reportKey) {
        this.reportKey = reportKey == null ? null : reportKey.trim();
    }

    public String getZoomKey() {
        return zoomKey;
    }

    public void setZoomKey(String zoomKey) {
        this.zoomKey = zoomKey == null ? null : zoomKey.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public String getCommitType() {
        return commitType;
    }

    public void setCommitType(String commitType) {
        this.commitType = commitType == null ? null : commitType.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getCommitTargetPerson() {
        return commitTargetPerson;
    }

    public void setCommitTargetPerson(String commitTargetPerson) {
        this.commitTargetPerson = commitTargetPerson == null ? null : commitTargetPerson.trim();
    }

    public String getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(String isAccept) {
        this.isAccept = isAccept == null ? null : isAccept.trim();
    }

    public String getAcceptPersonName() {
        return acceptPersonName;
    }

    public void setAcceptPersonName(String acceptPersonName) {
        this.acceptPersonName = acceptPersonName == null ? null : acceptPersonName.trim();
    }

    public String getAcceptPersonLogin() {
        return acceptPersonLogin;
    }

    public void setAcceptPersonLogin(String acceptPersonLogin) {
        this.acceptPersonLogin = acceptPersonLogin == null ? null : acceptPersonLogin.trim();
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Date getRejectTime() {
        return rejectTime;
    }

    public void setRejectTime(Date rejectTime) {
        this.rejectTime = rejectTime;
    }

    public Date getReportCommitTime() {
        return reportCommitTime;
    }

    public void setReportCommitTime(Date reportCommitTime) {
        this.reportCommitTime = reportCommitTime;
    }

    public String getPrimaryAuditName() {
        return primaryAuditName;
    }

    public void setPrimaryAuditName(String primaryAuditName) {
        this.primaryAuditName = primaryAuditName == null ? null : primaryAuditName.trim();
    }

    public String getPrimaryAuditLogin() {
        return primaryAuditLogin;
    }

    public void setPrimaryAuditLogin(String primaryAuditLogin) {
        this.primaryAuditLogin = primaryAuditLogin == null ? null : primaryAuditLogin.trim();
    }

    public Date getPrimaryTime() {
        return primaryTime;
    }

    public void setPrimaryTime(Date primaryTime) {
        this.primaryTime = primaryTime;
    }

    public String getPrimarySuggestion() {
        return primarySuggestion;
    }

    public void setPrimarySuggestion(String primarySuggestion) {
        this.primarySuggestion = primarySuggestion == null ? null : primarySuggestion.trim();
    }

    public Date getPrimaryRejectTime() {
        return primaryRejectTime;
    }

    public void setPrimaryRejectTime(Date primaryRejectTime) {
        this.primaryRejectTime = primaryRejectTime;
    }

    public String getUltimateAuditName() {
        return ultimateAuditName;
    }

    public void setUltimateAuditName(String ultimateAuditName) {
        this.ultimateAuditName = ultimateAuditName == null ? null : ultimateAuditName.trim();
    }

    public String getUltimateAuditLogin() {
        return ultimateAuditLogin;
    }

    public void setUltimateAuditLogin(String ultimateAuditLogin) {
        this.ultimateAuditLogin = ultimateAuditLogin == null ? null : ultimateAuditLogin.trim();
    }

    public Date getUltimateTime() {
        return ultimateTime;
    }

    public void setUltimateTime(Date ultimateTime) {
        this.ultimateTime = ultimateTime;
    }

    public String getUltimateSuggestion() {
        return ultimateSuggestion;
    }

    public void setUltimateSuggestion(String ultimateSuggestion) {
        this.ultimateSuggestion = ultimateSuggestion == null ? null : ultimateSuggestion.trim();
    }

    public Date getUltimateRejectTime() {
        return ultimateRejectTime;
    }

    public void setUltimateRejectTime(Date ultimateRejectTime) {
        this.ultimateRejectTime = ultimateRejectTime;
    }

    public String getinterventionType() {
        return interventionType;
    }

    public void setinterventionType(String interventionType) {
        this.interventionType = interventionType == null ? null : interventionType.trim();
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
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

    public String getinterventionRemark1() {
        return interventionRemark1;
    }

    public void setinterventionRemark1(String interventionRemark1) {
        this.interventionRemark1 = interventionRemark1 == null ? null : interventionRemark1.trim();
    }

    public String getinterventionRemark2() {
        return interventionRemark2;
    }

    public void setinterventionRemark2(String interventionRemark2) {
        this.interventionRemark2 = interventionRemark2 == null ? null : interventionRemark2.trim();
    }

    public String getinterventionRemark3() {
        return interventionRemark3;
    }

    public void setinterventionRemark3(String interventionRemark3) {
        this.interventionRemark3 = interventionRemark3 == null ? null : interventionRemark3.trim();
    }

    public String getinterventionRemark4() {
        return interventionRemark4;
    }

    public void setinterventionRemark4(String interventionRemark4) {
        this.interventionRemark4 = interventionRemark4 == null ? null : interventionRemark4.trim();
    }

    public String getinterventionRemark5() {
        return interventionRemark5;
    }

    public void setinterventionRemark5(String interventionRemark5) {
        this.interventionRemark5 = interventionRemark5 == null ? null : interventionRemark5.trim();
    }

    public String getinterventionRemark6() {
        return interventionRemark6;
    }

    public void setinterventionRemark6(String interventionRemark6) {
        this.interventionRemark6 = interventionRemark6 == null ? null : interventionRemark6.trim();
    }
}