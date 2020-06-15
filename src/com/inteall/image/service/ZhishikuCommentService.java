package com.inteall.image.service;

import java.util.List;
import java.util.Map;

import com.inteall.image.pojo.Zhishiku;
import com.inteall.image.pojo.ZhishikuComment;

/**
 * 评论Service接口
 * @author Administrator
 *
 */
public interface ZhishikuCommentService {

    /**
     * 添加评论
     * @param comment
     * @return
     */
    public int add(ZhishikuComment comment);
    
    /**
     * 修改评论
     * @param comment
     * @return
     */
    public int update(ZhishikuComment comment);
    
    /**
     * 查找用户评论信息
     * @param map
     * @return
     */
    public List<ZhishikuComment> list(Integer integer);
    
    
    /**
     * 获取总记录数
     * @param map
     * @return
     */
    public Long getTotal(Map<String,Object> map);
    
    /**
     * 删除评论信息
     * @param id
     * @return
     */
    public Integer delete(Integer id);

	public int getCount(ZhishikuComment zhishikuComment);

	public List<ZhishikuComment> getplAll(ZhishikuComment zhishikuComment);

	public int delplById(int parseInt);
}
