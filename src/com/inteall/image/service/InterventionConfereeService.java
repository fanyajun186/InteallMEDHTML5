package com.inteall.image.service;

import java.util.List;

import com.inteall.image.pojo.InterventionConferee;
import com.inteall.image.pojo.SysUser;

public interface InterventionConfereeService {

	List<SysUser> getByInterventionKey(InterventionConferee interventionConferee);

	int saveList(List<InterventionConferee> conferees);

	int deleteById(InterventionConferee interventionConferee);





}
