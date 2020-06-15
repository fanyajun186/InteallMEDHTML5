package com.inteall.image.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ZhishikuDao;
import com.inteall.image.pojo.Zhishiku;
import com.inteall.image.service.ZhishikuService;


/**
 * 知识库Service实现类
 * @author Administrator
 *
 */
@Service("zhishikuService")
public class ZhishikuServiceImpl implements ZhishikuService{

	@Resource
	private ZhishikuDao zhishikuDao;
	
	public List<Zhishiku> countList() {
		return zhishikuDao.countList();
	}

	public List<Zhishiku> list(Map<String, Object> map) {
		return zhishikuDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return zhishikuDao.getTotal(map);
	}

	public Zhishiku findById(Integer id) {
		return zhishikuDao.findById(id);
	}

	public Integer update(Zhishiku zhishiku) {
		return zhishikuDao.update(zhishiku);
	}

	public Zhishiku getLastzhishiku(Integer id) {
		return zhishikuDao.getLastZhishiku(id);
	}

	public Zhishiku getNextzhishiku(Integer id) {
		return zhishikuDao.getNextZhishiku(id);
	}

	public Integer add(Zhishiku zhishiku) {
		return zhishikuDao.add(zhishiku);
	}

	public Integer delete(Integer id) {
		return zhishikuDao.delete(id);
	}

	public Integer getzhishikuByTypeId(Integer typeId) {
		return zhishikuDao.getZhishikuByTypeId(typeId);
	}

	@Override
	public int getCount(Zhishiku zhishiku) {
		// TODO Auto-generated method stub
		return zhishikuDao.getCount(zhishiku);
	}

	@Override
	public List<Zhishiku> getAll(Zhishiku zhishiku) {
		// TODO Auto-generated method stub
		return zhishikuDao.getAll(zhishiku);
	}

	@Override
	public Zhishiku findById(int parseInt) {
		// TODO Auto-generated method stub
		return zhishikuDao.findById(parseInt);
	}

	@Override
	public int delById(int parseInt) {
		// TODO Auto-generated method stub
		return zhishikuDao.delById(parseInt);
	}

	

}
