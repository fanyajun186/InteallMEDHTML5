package com.inteall.image.pojo;

/** 
* @author 李进刚  
* @date 创建时间：2018年3月21日 
* @version 1.0 
* @parameter  
*/

public class WPACSWorklist {
    private String spsdate;

    private String accessno;

    private String pid;

    private String modality;

    private String cname;

    private String ename;

    private String gender;

    private String age;

    private String dob;

    private String stuuid;

    private String studesc;

    private String modaet;

    private String modname;

    private String aid;

    private String spsid;

    private String spsdesc;

    private String rpid;

    private String rpdesc;

    private String location;

    private String physname;

    private String bodypart;

    private String status;

    private String mppsuid;

    public String getSpsdate() {
        return spsdate;
    }

    public void setSpsdate(String spsdate) {
        this.spsdate = spsdate == null ? null : spsdate.trim();
    }

    public String getAccessno() {
        return accessno;
    }

    public void setAccessno(String accessno) {
        this.accessno = accessno == null ? null : accessno.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality == null ? null : modality.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob == null ? null : dob.trim();
    }

    public String getStuuid() {
        return stuuid;
    }

    public void setStuuid(String stuuid) {
        this.stuuid = stuuid == null ? null : stuuid.trim();
    }

    public String getStudesc() {
        return studesc;
    }

    public void setStudesc(String studesc) {
        this.studesc = studesc == null ? null : studesc.trim();
    }

    public String getModaet() {
        return modaet;
    }

    public void setModaet(String modaet) {
        this.modaet = modaet == null ? null : modaet.trim();
    }

    public String getModname() {
        return modname;
    }

    public void setModname(String modname) {
        this.modname = modname == null ? null : modname.trim();
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    public String getSpsid() {
        return spsid;
    }

    public void setSpsid(String spsid) {
        this.spsid = spsid == null ? null : spsid.trim();
    }

    public String getSpsdesc() {
        return spsdesc;
    }

    public void setSpsdesc(String spsdesc) {
        this.spsdesc = spsdesc == null ? null : spsdesc.trim();
    }

    public String getRpid() {
        return rpid;
    }

    public void setRpid(String rpid) {
        this.rpid = rpid == null ? null : rpid.trim();
    }

    public String getRpdesc() {
        return rpdesc;
    }

    public void setRpdesc(String rpdesc) {
        this.rpdesc = rpdesc == null ? null : rpdesc.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getPhysname() {
        return physname;
    }

    public void setPhysname(String physname) {
        this.physname = physname == null ? null : physname.trim();
    }

    public String getBodypart() {
        return bodypart;
    }

    public void setBodypart(String bodypart) {
        this.bodypart = bodypart == null ? null : bodypart.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMppsuid() {
        return mppsuid;
    }

    public void setMppsuid(String mppsuid) {
        this.mppsuid = mppsuid == null ? null : mppsuid.trim();
    }

    public WPACSWorklist() {
      super();
      // TODO 自动生成的构造函数存根
    }

    public WPACSWorklist(String spsdate, String accessno, String pid, String modality, String cname, String ename,
        String gender, String age, String dob, String stuuid, String studesc, String modaet, String modname, String aid,
        String spsid, String spsdesc, String rpid, String rpdesc, String location, String physname, String bodypart,
        String status, String mppsuid) {
      super();
      this.spsdate = spsdate;
      this.accessno = accessno;
      this.pid = pid;
      this.modality = modality;
      this.cname = cname;
      this.ename = ename;
      this.gender = gender;
      this.age = age;
      this.dob = dob;
      this.stuuid = stuuid;
      this.studesc = studesc;
      this.modaet = modaet;
      this.modname = modname;
      this.aid = aid;
      this.spsid = spsid;
      this.spsdesc = spsdesc;
      this.rpid = rpid;
      this.rpdesc = rpdesc;
      this.location = location;
      this.physname = physname;
      this.bodypart = bodypart;
      this.status = status;
      this.mppsuid = mppsuid;
    }
}