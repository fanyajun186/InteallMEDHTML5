package com.inteall.image.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.CollectionDao;
import com.inteall.image.pojo.Collection;
import com.inteall.image.pojo.CollectionWithBLOBs;
import com.inteall.image.pojo.ConsultationWithBLOBs;
import com.inteall.image.pojo.MedicalRecord;
import com.inteall.image.service.CollectionService;
@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {
	@Resource
	private CollectionDao collectionDao;
	@Override
	public int insertCollection(Collection collection) {
		// TODO 自动生成的方法存根
		return collectionDao.insertCollection(collection);
	}

	@Override
	public int update(Collection collection) {
		// TODO 自动生成的方法存根
		return collectionDao.update(collection);
	}

	@Override
	public Collection getById(String id) {
		// TODO 自动生成的方法存根
		return collectionDao.getById(id);
	}

	@Override
	public int delCollection(Collection collection) {
		// TODO 自动生成的方法存根
		return collectionDao.delCollection(collection);
	}

	@Override
	public List<Collection> getAll(String createId) {
		// TODO 自动生成的方法存根
		return collectionDao.getAll(createId);
	}

	@Override
	public List<Collection> getParentKeyCollection(String collectionKey) {
		
		return collectionDao.getParentKeyCollection(collectionKey);
	}

	@Override
	public Collection getCollectionName(String collectionKey) {
		
		return collectionDao.getCollectionName(collectionKey);
	}



}
