package com.inteall.image.pojo;

import java.util.Date;

public class ConsultationComment {
    private String commentKey;
    
    private String caseId;

    private String imageCommentResult;

    private String imageCommentItem;

    private String imageCommentCreatePerson;

    private Date imageCommentCreateTime;

    private String imageCommentUpdatePerson;

    private Date imageCommentUpdateTime;

    private String reportCommentResult;

    private String reportCommentItem;

    private String reportCommentCreatePerson;

    private Date reportCommentCreateTime;

    private String reportCommentUpdatePerson;

    private Date reportCommentUpdateTime;

    public String getCommentKey() {
        return commentKey;
    }

    public void setCommentKey(String commentKey) {
        this.commentKey = commentKey == null ? null : commentKey.trim();
    }

    public String getImageCommentResult() {
        return imageCommentResult;
    }

    public void setImageCommentResult(String imageCommentResult) {
        this.imageCommentResult = imageCommentResult == null ? null : imageCommentResult.trim();
    }

    public String getImageCommentItem() {
        return imageCommentItem;
    }

    public void setImageCommentItem(String imageCommentItem) {
        this.imageCommentItem = imageCommentItem == null ? null : imageCommentItem.trim();
    }

    public String getImageCommentCreatePerson() {
        return imageCommentCreatePerson;
    }

    public void setImageCommentCreatePerson(String imageCommentCreatePerson) {
        this.imageCommentCreatePerson = imageCommentCreatePerson == null ? null : imageCommentCreatePerson.trim();
    }

    public Date getImageCommentCreateTime() {
        return imageCommentCreateTime;
    }

    public void setImageCommentCreateTime(Date imageCommentCreateTime) {
        this.imageCommentCreateTime = imageCommentCreateTime;
    }

    public String getImageCommentUpdatePerson() {
        return imageCommentUpdatePerson;
    }

    public void setImageCommentUpdatePerson(String imageCommentUpdatePerson) {
        this.imageCommentUpdatePerson = imageCommentUpdatePerson == null ? null : imageCommentUpdatePerson.trim();
    }

    public Date getImageCommentUpdateTime() {
        return imageCommentUpdateTime;
    }

    public void setImageCommentUpdateTime(Date imageCommentUpdateTime) {
        this.imageCommentUpdateTime = imageCommentUpdateTime;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getReportCommentResult() {
        return reportCommentResult;
    }

    public void setReportCommentResult(String reportCommentResult) {
        this.reportCommentResult = reportCommentResult == null ? null : reportCommentResult.trim();
    }

    public String getReportCommentItem() {
        return reportCommentItem;
    }

    public void setReportCommentItem(String reportCommentItem) {
        this.reportCommentItem = reportCommentItem == null ? null : reportCommentItem.trim();
    }

    public String getReportCommentCreatePerson() {
        return reportCommentCreatePerson;
    }

    public void setReportCommentCreatePerson(String reportCommentCreatePerson) {
        this.reportCommentCreatePerson = reportCommentCreatePerson == null ? null : reportCommentCreatePerson.trim();
    }

    public Date getReportCommentCreateTime() {
        return reportCommentCreateTime;
    }

    public void setReportCommentCreateTime(Date reportCommentCreateTime) {
        this.reportCommentCreateTime = reportCommentCreateTime;
    }

    public String getReportCommentUpdatePerson() {
        return reportCommentUpdatePerson;
    }

    public void setReportCommentUpdatePerson(String reportCommentUpdatePerson) {
        this.reportCommentUpdatePerson = reportCommentUpdatePerson == null ? null : reportCommentUpdatePerson.trim();
    }

    public Date getReportCommentUpdateTime() {
        return reportCommentUpdateTime;
    }

    public void setReportCommentUpdateTime(Date reportCommentUpdateTime) {
        this.reportCommentUpdateTime = reportCommentUpdateTime;
    }
}