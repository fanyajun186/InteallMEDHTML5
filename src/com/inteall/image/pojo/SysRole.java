package com.inteall.image.pojo;

import java.util.Date;
import java.util.List;

/** 
* @author 韩明君  
* @date 创建时间：2018年2月9日 下午4:43:56 
* @version 1.0 
* @parameter  
*/
public class SysRole {
    private String sysRoleKey;
  
    private String roleName;
  
    private String createPerson;
  
    private Date createTime;
  
    private Date modifyTime;
  
    private String modifyPerson;
  
    private String isDel;
  
    private Date delTime;
  
    private String delPerson;
  
    private String sysRoleRemark1;
  
    private String sysRoleRemark2;
  
    private String sysRoleRemark3;
    
	private int limit;//每页显示多少行
	
	private int curr;//分页查询，从哪行开始查询
	
	private List<SysRight> rights;

	public String getSysRoleKey() {
	  return sysRoleKey;
	}

	public void setSysRoleKey(String sysRoleKey) {
	  this.sysRoleKey = sysRoleKey;
	}

	public String getRoleName() {
	  return roleName;
	}

	public void setRoleName(String roleName) {
	  this.roleName = roleName;
	}

	public String getCreatePerson() {
	  return createPerson;
	}

	public void setCreatePerson(String createPerson) {
	  this.createPerson = createPerson;
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
	  this.modifyPerson = modifyPerson;
	}

	public String getIsDel() {
	  return isDel;
	}

	public void setIsDel(String isDel) {
	  this.isDel = isDel;
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
	  this.delPerson = delPerson;
	}

	public String getSysRoleRemark1() {
	  return sysRoleRemark1;
	}

	public void setSysRoleRemark1(String sysRoleRemark1) {
	  this.sysRoleRemark1 = sysRoleRemark1;
	}

	public String getSysRoleRemark2() {
	  return sysRoleRemark2;
	}

	public void setSysRoleRemark2(String sysRoleRemark2) {
	  this.sysRoleRemark2 = sysRoleRemark2;
	}

	public String getSysRoleRemark3() {
	  return sysRoleRemark3;
	}

	public void setSysRoleRemark3(String sysRoleRemark3) {
	  this.sysRoleRemark3 = sysRoleRemark3;
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

	public List<SysRight> getRights() {
	  return rights;
	}

	public void setRights(List<SysRight> rights) {
	  this.rights = rights;
	}
}
