package com.inteall.image.pojo;

public class ReportWithBLOBs extends Report {
    private String checkMethod;

    private String checkName;

    private String diagnosisResult;

    private String checkView;

    public String getCheckMethod() {
        return checkMethod;
    }

    public void setCheckMethod(String checkMethod) {
        this.checkMethod = checkMethod == null ? null : checkMethod.trim();
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName == null ? null : checkName.trim();
    }

    public String getDiagnosisResult() {
        return diagnosisResult;
    }

    public void setDiagnosisResult(String diagnosisResult) {
        this.diagnosisResult = diagnosisResult == null ? null : diagnosisResult.trim();
    }

    public String getCheckView() {
        return checkView;
    }

    public void setCheckView(String checkView) {
        this.checkView = checkView == null ? null : checkView.trim();
    }
}