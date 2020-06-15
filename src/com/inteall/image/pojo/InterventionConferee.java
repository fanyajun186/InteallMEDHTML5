package com.inteall.image.pojo;

public class InterventionConferee {
    private String interventionConfereeKey;

    private String interventionKey;

    private String sysuserKey;

    private String interventionRemark1;

    private String interventionRemark2;

    private String interventionRemark3;

    public String getinterventionConfereeKey() {
        return interventionConfereeKey;
    }

    public void setinterventionConfereeKey(String interventionConfereeKey) {
        this.interventionConfereeKey = interventionConfereeKey == null ? null : interventionConfereeKey.trim();
    }

    public String getinterventionKey() {
        return interventionKey;
    }

    public void setinterventionKey(String interventionKey) {
        this.interventionKey = interventionKey == null ? null : interventionKey.trim();
    }

    public String getSysuserKey() {
        return sysuserKey;
    }

    public void setSysuserKey(String sysuserKey) {
        this.sysuserKey = sysuserKey == null ? null : sysuserKey.trim();
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
}