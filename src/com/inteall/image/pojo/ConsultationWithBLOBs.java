package com.inteall.image.pojo;

public class ConsultationWithBLOBs extends Consultation {
    private String commitRequest;

    private String rejectReason;

    private String primaryReject;

    private String ultimateReject;
    
    private String acceptPersonName;
    
    public String getAcceptPersonName() {
		return acceptPersonName;
	}

	public void setAcceptPersonName(String acceptPersonName) {
		this.acceptPersonName = acceptPersonName;
	}

	public String getCommitRequest() {
        return commitRequest;
    }

    public void setCommitRequest(String commitRequest) {
        this.commitRequest = commitRequest == null ? null : commitRequest.trim();
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason == null ? null : rejectReason.trim();
    }

    public String getPrimaryReject() {
        return primaryReject;
    }

    public void setPrimaryReject(String primaryReject) {
        this.primaryReject = primaryReject == null ? null : primaryReject.trim();
    }

    public String getUltimateReject() {
        return ultimateReject;
    }

    public void setUltimateReject(String ultimateReject) {
        this.ultimateReject = ultimateReject == null ? null : ultimateReject.trim();
    }


}