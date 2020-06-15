package com.inteall.image.pojo;

public class ReportTemplateWithBLOBs extends ReportTemplate {
    private String checkView;

    private String diagnosisResult;

    public String getCheckView() {
        return checkView;
    }

    public void setCheckView(String checkView) {
        this.checkView = checkView == null ? null : checkView.trim();
    }

    public String getDiagnosisResult() {
        return diagnosisResult;
    }

    public void setDiagnosisResult(String diagnosisResult) {
        this.diagnosisResult = diagnosisResult == null ? null : diagnosisResult.trim();
    }
}