package com.inteall.image.pojo;

import java.io.Serializable;

/**
 * 知识库类型实体
 * @author Administrator
 *
 */
public class ZhishikuType implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Integer id;  // 编号
    private String typeName; // 知识库类型名称
    private Integer zhishikuCount; // 数量
    private Integer orderNo; // 排序  从小到大排序显示
    private Integer parentId;
    private Integer isParent;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public Integer getzhishikuCount() {
        return zhishikuCount;
    }
    public void setzhishikuCount(Integer zhishikuCount) {
        this.zhishikuCount = zhishikuCount;
    }
    public Integer getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public Integer getIsParent() {
        return isParent;
    }
    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    
    
}
