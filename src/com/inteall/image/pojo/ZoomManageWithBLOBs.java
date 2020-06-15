package com.inteall.image.pojo;

public class ZoomManageWithBLOBs extends ZoomManage {
    private String startUrl;

    private String joinUrl;

    public String getStartUrl() {
        return startUrl;
    }

    public void setStartUrl(String startUrl) {
        this.startUrl = startUrl == null ? null : startUrl.trim();
    }

    public String getJoinUrl() {
        return joinUrl;
    }

    public void setJoinUrl(String joinUrl) {
        this.joinUrl = joinUrl == null ? null : joinUrl.trim();
    }
}