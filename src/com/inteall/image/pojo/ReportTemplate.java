package com.inteall.image.pojo;

import java.util.Date;

public class ReportTemplate {
    private String reportTemplateKey;

    private String reportTemplateName;

    private String reportTypeKey;

    private Date createTime;

    private String createPerson;

    private Date modifyTime;

    private String modifyPerson;

    private String isDel;

    private Date delTime;

    private Integer delPerson;

    private String reportTemplateRemark1;

    private String reportTemplateRamark2;

    private String reportTemplateRamark3;

    public String getReportTemplateKey() {
        return reportTemplateKey;
    }

    public void setReportTemplateKey(String reportTemplateKey) {
        this.reportTemplateKey = reportTemplateKey == null ? null : reportTemplateKey.trim();
    }

    public String getReportTemplateName() {
        return reportTemplateName;
    }

    public void setReportTemplateName(String reportTemplateName) {
        this.reportTemplateName = reportTemplateName == null ? null : reportTemplateName.trim();
    }

    public String getReportTypeKey() {
        return reportTypeKey;
    }

    public void setReportTypeKey(String reportTypeKey) {
        this.reportTypeKey = reportTypeKey == null ? null : reportTypeKey.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
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

    public Integer getDelPerson() {
        return delPerson;
    }

    public void setDelPerson(Integer delPerson) {
        this.delPerson = delPerson;
    }

    public String getReportTemplateRemark1() {
        return reportTemplateRemark1;
    }

    public void setReportTemplateRemark1(String reportTemplateRemark1) {
        this.reportTemplateRemark1 = reportTemplateRemark1 == null ? null : reportTemplateRemark1.trim();
    }

    public String getReportTemplateRamark2() {
        return reportTemplateRamark2;
    }

    public void setReportTemplateRamark2(String reportTemplateRamark2) {
        this.reportTemplateRamark2 = reportTemplateRamark2 == null ? null : reportTemplateRamark2.trim();
    }

    public String getReportTemplateRamark3() {
        return reportTemplateRamark3;
    }

    public void setReportTemplateRamark3(String reportTemplateRamark3) {
        this.reportTemplateRamark3 = reportTemplateRamark3 == null ? null : reportTemplateRamark3.trim();
    }
}