package com.inteall.image.pojo;

import java.util.Date;

public class Courseware {
    private String coursewareKey;

    private String coursewareName;

    private String coursewareDesc;

    private Long coursewareSize;

    private Date coursewareCreateTime;

    private String coursewareCreatePerson;

    private Date coursewareModifyTime;

    private String coursewareModifyPerson;

    private String videoTurorialsKey;

    private String createPerson;

    private Date createTime;

    private Date modifyTime;

    private String modifyPerson;

    private String isDel;

    private Date delTime;

    private String delPerson;

    private String coursewareRemark1;

    private String coursewareRemark2;

    private String coursewareRemark3;

    public String getCoursewareKey() {
        return coursewareKey;
    }

    public void setCoursewareKey(String coursewareKey) {
        this.coursewareKey = coursewareKey == null ? null : coursewareKey.trim();
    }

    public String getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(String coursewareName) {
        this.coursewareName = coursewareName == null ? null : coursewareName.trim();
    }

    public String getCoursewareDesc() {
        return coursewareDesc;
    }

    public void setCoursewareDesc(String coursewareDesc) {
        this.coursewareDesc = coursewareDesc == null ? null : coursewareDesc.trim();
    }

    public Long getCoursewareSize() {
        return coursewareSize;
    }

    public void setCoursewareSize(Long coursewareSize) {
        this.coursewareSize = coursewareSize;
    }

    public Date getCoursewareCreateTime() {
        return coursewareCreateTime;
    }

    public void setCoursewareCreateTime(Date coursewareCreateTime) {
        this.coursewareCreateTime = coursewareCreateTime;
    }

    public String getCoursewareCreatePerson() {
        return coursewareCreatePerson;
    }

    public void setCoursewareCreatePerson(String coursewareCreatePerson) {
        this.coursewareCreatePerson = coursewareCreatePerson == null ? null : coursewareCreatePerson.trim();
    }

    public Date getCoursewareModifyTime() {
        return coursewareModifyTime;
    }

    public void setCoursewareModifyTime(Date coursewareModifyTime) {
        this.coursewareModifyTime = coursewareModifyTime;
    }

    public String getCoursewareModifyPerson() {
        return coursewareModifyPerson;
    }

    public void setCoursewareModifyPerson(String coursewareModifyPerson) {
        this.coursewareModifyPerson = coursewareModifyPerson == null ? null : coursewareModifyPerson.trim();
    }

    public String getVideoTurorialsKey() {
        return videoTurorialsKey;
    }

    public void setVideoTurorialsKey(String videoTurorialsKey) {
        this.videoTurorialsKey = videoTurorialsKey;
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

    public String getCoursewareRemark1() {
        return coursewareRemark1;
    }

    public void setCoursewareRemark1(String coursewareRemark1) {
        this.coursewareRemark1 = coursewareRemark1 == null ? null : coursewareRemark1.trim();
    }

    public String getCoursewareRemark2() {
        return coursewareRemark2;
    }

    public void setCoursewareRemark2(String coursewareRemark2) {
        this.coursewareRemark2 = coursewareRemark2 == null ? null : coursewareRemark2.trim();
    }

    public String getCoursewareRemark3() {
        return coursewareRemark3;
    }

    public void setCoursewareRemark3(String coursewareRemark3) {
        this.coursewareRemark3 = coursewareRemark3 == null ? null : coursewareRemark3.trim();
    }
}