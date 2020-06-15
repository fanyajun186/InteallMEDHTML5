package com.inteall.image.pojo;

import java.util.Date;

public class SysHospital {
    private String sysHospitalKey;

    private String hospName;

    private String hospSname;

    private String hospClass;

    private String hospLevel;

    private String hospOcc;

    private String hospAddPr;

    private String hospAddCy;

    private String hospAddQx;

    private String hospAdd;

    private String hospPostCode;

    private String hospAd;

    private String hospLp;

    private String hospUser;

    private String hospTel;

    private String hospMobile;

    private Byte isCenter;

    private String hospEmail;

    private String hospAbout;

    private String createPerson;

    private Date createTime;

    private String modifyPerson;

    private Date modifyTime;

    private String isDel;

    private String delPerson;

    private Date delTime;

    private String hospRemark1;

    private String hospRemark2;

    private String hospRemark3;
    
    private int limit;//每页显示多少行
    
    private int curr;//分页查询，从哪行开始查询

    public String getSysHospitalKey() {
        return sysHospitalKey;
    }

    public void setSysHospitalKey(String sysHospitalKey) {
        this.sysHospitalKey = sysHospitalKey == null ? null : sysHospitalKey.trim();
    }

    public String getHospName() {
        return hospName;
    }

    public void setHospName(String hospName) {
        this.hospName = hospName == null ? null : hospName.trim();
    }

    public String getHospSname() {
        return hospSname;
    }

    public void setHospSname(String hospSname) {
        this.hospSname = hospSname == null ? null : hospSname.trim();
    }

    public String getHospClass() {
        return hospClass;
    }

    public void setHospClass(String hospClass) {
        this.hospClass = hospClass == null ? null : hospClass.trim();
    }

    public String getHospLevel() {
        return hospLevel;
    }

    public void setHospLevel(String hospLevel) {
        this.hospLevel = hospLevel == null ? null : hospLevel.trim();
    }

    public String getHospOcc() {
        return hospOcc;
    }

    public void setHospOcc(String hospOcc) {
        this.hospOcc = hospOcc == null ? null : hospOcc.trim();
    }

    public String getHospAddPr() {
        return hospAddPr;
    }

    public void setHospAddPr(String hospAddPr) {
        this.hospAddPr = hospAddPr == null ? null : hospAddPr.trim();
    }

    public String getHospAddCy() {
        return hospAddCy;
    }

    public void setHospAddCy(String hospAddCy) {
        this.hospAddCy = hospAddCy == null ? null : hospAddCy.trim();
    }

    public String getHospAddQx() {
        return hospAddQx;
    }

    public void setHospAddQx(String hospAddQx) {
        this.hospAddQx = hospAddQx == null ? null : hospAddQx.trim();
    }

    public String getHospAdd() {
        return hospAdd;
    }

    public void setHospAdd(String hospAdd) {
        this.hospAdd = hospAdd == null ? null : hospAdd.trim();
    }

    public String getHospPostCode() {
        return hospPostCode;
    }

    public void setHospPostCode(String hospPostCode) {
        this.hospPostCode = hospPostCode == null ? null : hospPostCode.trim();
    }

    public String getHospAd() {
        return hospAd;
    }

    public void setHospAd(String hospAd) {
        this.hospAd = hospAd == null ? null : hospAd.trim();
    }

    public String getHospLp() {
        return hospLp;
    }

    public void setHospLp(String hospLp) {
        this.hospLp = hospLp == null ? null : hospLp.trim();
    }

    public String getHospUser() {
        return hospUser;
    }

    public void setHospUser(String hospUser) {
        this.hospUser = hospUser == null ? null : hospUser.trim();
    }

    public String getHospTel() {
        return hospTel;
    }

    public void setHospTel(String hospTel) {
        this.hospTel = hospTel == null ? null : hospTel.trim();
    }

    public String getHospMobile() {
        return hospMobile;
    }

    public void setHospMobile(String hospMobile) {
        this.hospMobile = hospMobile == null ? null : hospMobile.trim();
    }

    public Byte getIsCenter() {
        return isCenter;
    }

    public void setIsCenter(Byte isCenter) {
        this.isCenter = isCenter;
    }

    public String getHospEmail() {
        return hospEmail;
    }

    public void setHospEmail(String hospEmail) {
        this.hospEmail = hospEmail == null ? null : hospEmail.trim();
    }

    public String getHospAbout() {
        return hospAbout;
    }

    public void setHospAbout(String hospAbout) {
        this.hospAbout = hospAbout == null ? null : hospAbout.trim();
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

    public String getModifyPerson() {
        return modifyPerson;
    }

    public void setModifyPerson(String modifyPerson) {
        this.modifyPerson = modifyPerson == null ? null : modifyPerson.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    public String getDelPerson() {
        return delPerson;
    }

    public void setDelPerson(String delPerson) {
        this.delPerson = delPerson == null ? null : delPerson.trim();
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getHospRemark1() {
        return hospRemark1;
    }

    public void setHospRemark1(String hospRemark1) {
        this.hospRemark1 = hospRemark1 == null ? null : hospRemark1.trim();
    }

    public String getHospRemark2() {
        return hospRemark2;
    }

    public void setHospRemark2(String hospRemark2) {
        this.hospRemark2 = hospRemark2 == null ? null : hospRemark2.trim();
    }

    public String getHospRemark3() {
        return hospRemark3;
    }

    public void setHospRemark3(String hospRemark3) {
        this.hospRemark3 = hospRemark3 == null ? null : hospRemark3.trim();
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