package com.inteall.image.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 知识库实体
 * @author Administrator
 *
 */
public class Zhishiku implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id; // 编号
	private String title; // 知识库标题
	private String summary; // 摘要
	private Date releaseDate; // 发布日期
	private String releasePersonId;//内容创建者id
	private String releasePersonName;//内容创建者
 	private Integer clickHit; // 查看次数
	private Integer replyHit; // 回复次数
	private String content; // 知识库内容
	private String contentNoTag; // 知识库内容 无网页标签 Lucene分词用
	private ZhishikuType zhishikuType; // 知识库类型
	
	private Integer zhishikuCount; // 知识库数量 非知识库实际属性，主要是 根据发布日期归档查询知识库数量用
	private String releaseDateStr; // 发布日期字符串 只取年和月
	private String keyWord; // 关键字 空格隔开
	private int limit;//每页显示多少行
	private int curr;//分页查询，从哪行开始查询
	
	private List<String> imagesList=new LinkedList<String>(); // 知识库里存在的图片 主要用于列表展示显示缩略图

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getClickHit() {
        return clickHit;
    }

    public void setClickHit(Integer clickHit) {
        this.clickHit = clickHit;
    }

    public Integer getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(Integer replyHit) {
        this.replyHit = replyHit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentNoTag() {
        return contentNoTag;
    }

    public void setContentNoTag(String contentNoTag) {
        this.contentNoTag = contentNoTag;
    }

    public ZhishikuType getZhishikuType() {
        return zhishikuType;
    }

    public void setZhishikuType(ZhishikuType zhishikuType) {
        this.zhishikuType = zhishikuType;
    }

    public Integer getZhishikuCount() {
        return zhishikuCount;
    }

    public void setZhishikuCount(Integer zhishikuCount) {
        this.zhishikuCount = zhishikuCount;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

	public String getReleasePersonId() {
		return releasePersonId;
	}

	public void setReleasePersonId(String releasePersonId) {
		this.releasePersonId = releasePersonId;
	}

	public String getReleasePersonName() {
		return releasePersonName;
	}

	public void setReleasePersonName(String releasePersonName) {
		this.releasePersonName = releasePersonName;
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
