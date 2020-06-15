package com.inteall.image.pojo;

import java.util.Date;

/**
 * 评论实体
 * @author Administrator
 *
 */
public class ZhishikuComment {

    private Integer id; // 编号
    private String userIp; // 用户IP
    private String content; // 评论内容
    private Zhishiku zhishiku; // 被评论的博客
    private Date commentDate; // 评论日期
    private String commentDateStr; // 评论日期
    private String commentPersonId; // 评论人id
    private String commentPersonName; // 评论姓名
    private Integer state; // 审核状态  0 待审核 1 审核通过 2 审核未通过
    private int limit;//每页显示多少行
	private int curr;//分页查询，从哪行开始查询
	
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserIp() {
        return userIp;
    }
    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Zhishiku getZhishiku() {
        return zhishiku;
    }
    public void setZhishiku(Zhishiku zhishiku) {
        this.zhishiku = zhishiku;
    }
    public Date getCommentDate() {
        return commentDate;
    }
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public String getCommentPersonId() {
        return commentPersonId;
    }
    public void setCommentPersonId(String commentPersonId) {
        this.commentPersonId = commentPersonId;
    }
    public String getCommentPersonName() {
        return commentPersonName;
    }
    public void setCommentPersonName(String commentPersonName) {
        this.commentPersonName = commentPersonName;
    }
    public String getCommentDateStr() {
        return commentDateStr;
    }
    public void setCommentDateStr(String commentDateStr) {
        this.commentDateStr = commentDateStr;
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
