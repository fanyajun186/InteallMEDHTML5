package com.inteall.image.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ZhishikuTypeDao;
import com.inteall.image.pojo.ZhishikuType;
import com.inteall.image.service.ZhishikuTypeService;

/**
 * 知识库类型Service实现类
 * @author Administrator
 *
 */
@Service("zhishikuTypeService")
public class ZhishikuTypeServiceImpl implements ZhishikuTypeService{

	@Resource
	private ZhishikuTypeDao zhishikuTypeDao;
	
	public List<ZhishikuType> countList() {
		return zhishikuTypeDao.countList();
	}

	public List<ZhishikuType> list() {
		return zhishikuTypeDao.list();
	}

	public Long getTotal(Map<String, Object> map) {
		return zhishikuTypeDao.getTotal(map);
	}

	public Integer add(ZhishikuType ZhishikuType) {
		return zhishikuTypeDao.add(ZhishikuType);
	}

	public Integer update(ZhishikuType ZhishikuType) {
		return zhishikuTypeDao.update(ZhishikuType);
	}

	public Integer delete(Integer id) {
		return zhishikuTypeDao.delete(id);
	}

    @Override
    public List<ZhishikuType> parentlist() {
        // TODO Auto-generated method stub
        return zhishikuTypeDao.parentlist();
    }

    @Override
    public List<ZhishikuType> childlist() {
        // TODO Auto-generated method stub
        return zhishikuTypeDao.childlist();
    }

	@Override
	public List<ZhishikuType> childlistByparentId(String isParent) {
		// TODO Auto-generated method stub
		return zhishikuTypeDao.childlistByparentId(isParent);
	}

    @Override
    public List<ZhishikuType> getParentByName(Map<String, Object> querymap) {
        // TODO Auto-generated method stub
        return zhishikuTypeDao.getParentByName(querymap);
    }

    @Override
    public int getCountParentByName(Map<String, Object> querymap) {
        // TODO Auto-generated method stub
        return zhishikuTypeDao.getCountParentByName(querymap);
    }

    @Override
    public int getCountChild(Map<String, Object> querymap) {
        // TODO Auto-generated method stub
        return zhishikuTypeDao.getCountChild(querymap);
    }

    @Override
    public List<ZhishikuType> getChild(Map<String, Object> querymap) {
        // TODO Auto-generated method stub
        return zhishikuTypeDao.getChild(querymap);
    }

    @Override
    public ZhishikuType getById(Map<String, Object> querymap) {
        // TODO Auto-generated method stub
        return zhishikuTypeDao.getById(querymap);
    }


}
