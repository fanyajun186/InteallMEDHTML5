package com.inteall.image.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ZhishikuCommentDao;
import com.inteall.image.pojo.Zhishiku;
import com.inteall.image.pojo.ZhishikuComment;
import com.inteall.image.service.ZhishikuCommentService;


/**
 * 评论Service实现类
 * @author Administrator
 *
 */
@Service("zhishikuCommentService")
public class ZhishikuCommentServiceImpl implements ZhishikuCommentService{

    @Resource
    private ZhishikuCommentDao zhishikuCommentDao;
    
    public int add(ZhishikuComment comment) {
        return zhishikuCommentDao.add(comment);
    }

    public Long getTotal(Map<String, Object> map) {
        return zhishikuCommentDao.getTotal(map);
    }

    public Integer delete(Integer id) {
        return zhishikuCommentDao.delete(id);
    }

    public int update(ZhishikuComment comment) {
        return zhishikuCommentDao.update(comment);
    }

	@Override
	public int getCount(ZhishikuComment zhishikuComment) {
		// TODO Auto-generated method stub
		return zhishikuCommentDao.getCount(zhishikuComment);
	}


	@Override
	public List<ZhishikuComment> getplAll(ZhishikuComment zhishikuComment) {
		// TODO Auto-generated method stub
		return zhishikuCommentDao.getplAll(zhishikuComment);
	}

	@Override
	public List<ZhishikuComment> list(Integer integer) {
		// TODO Auto-generated method stub
		return zhishikuCommentDao.list(integer);
	}

	@Override
	public int delplById(int parseInt) {
		// TODO Auto-generated method stub
		return zhishikuCommentDao.delplById(parseInt);
	}


}
