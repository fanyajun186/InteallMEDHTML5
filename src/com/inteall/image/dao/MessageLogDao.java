package com.inteall.image.dao;

import java.util.List;
import java.util.Map;

public interface MessageLogDao {

	int addMessageInfo(Map<String, Object> ma);

	int getMessageCount(Map<String, Object> search);

	List<Map> getMessageList(Map<String, Object> search);

	int delMessageInfo(int infoId);

}
