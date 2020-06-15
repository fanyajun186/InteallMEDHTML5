package com.inteall.image.pojo;

import java.util.Date;

public class ReportType {
    private String reportTypeKey;

    private String modality;

    private String parentCode;

    private String typeCode;

    private String name;
    
    private String isParent;
    
    private String sysuserKey;

    private String createPerson;

    private Date createtime;

    private Date modifyTime;

    private String modifyPerson;

    private String isDel;

    private Date delTime;

    private String delPerson;

    private String reportTypeRemark1;

    private String reportTypeRemark2;

    private String reportTypeRemark3;

    public String getReportTypeKey() {
        return reportTypeKey;
    }

    public void setReportTypeKey(String reportTypeKey) {
        this.reportTypeKey = reportTypeKey == null ? null : reportTypeKey.trim();
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality == null ? null : modality.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getIsParent() {
	  return isParent;
	}

	public void setIsParent(String isParent) {
	  this.isParent = isParent;
	}

	public String getSysuserKey() {
	  return sysuserKey;
	}

	public void setSysuserKey(String sysuserKey) {
	  this.sysuserKey = sysuserKey;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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

    public String getReportTypeRemark1() {
        return reportTypeRemark1;
    }

    public void setReportTypeRemark1(String reportTypeRemark1) {
        this.reportTypeRemark1 = reportTypeRemark1 == null ? null : reportTypeRemark1.trim();
    }

    public String getReportTypeRemark2() {
        return reportTypeRemark2;
    }

    public void setReportTypeRemark2(String reportTypeRemark2) {
        this.reportTypeRemark2 = reportTypeRemark2 == null ? null : reportTypeRemark2.trim();
    }

    public String getReportTypeRemark3() {
        return reportTypeRemark3;
    }

    public void setReportTypeRemark3(String reportTypeRemark3) {
        this.reportTypeRemark3 = reportTypeRemark3 == null ? null : reportTypeRemark3.trim();
    }
}