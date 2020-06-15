package com.inteall.image.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.MessageLogDao;
import com.inteall.image.service.MessageLogService;

@Service("MessageLogService")
public class MessageLogServiceImpl implements MessageLogService{
	
	@Resource
	private MessageLogDao messageLogDao;
	
	@Override
	public int addMessageInfo(Map<String, Object> ma) {
		return messageLogDao.addMessageInfo(ma);
		 
	}

	@Override
	public int getMessageCount(Map<String, Object> search) {
		return messageLogDao.getMessageCount(search);
	}

	@Override
	public List<Map> getMessageList(Map<String, Object> search) {
		return messageLogDao.getMessageList(search);
	}

	@Override
	public int delMessageInfo(int infoId) {
		return messageLogDao.delMessageInfo(infoId);
	}

}
