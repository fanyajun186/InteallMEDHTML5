package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ReportTraceDao;
import com.inteall.image.pojo.ReportTraceWithBLOBs;
import com.inteall.image.service.ReportTraceService;

/** 
 * @author 韩明君  
 * @date 创建时间：2016年7月9日 上午10:21:52 
 * @version 1.0 
 * @parameter  
 */
@Service("reportTraceService")
public class ReportTraceServiceImpl implements ReportTraceService {
	@Resource
	private ReportTraceDao reportTraceDao;

	@Override
	public int save(ReportTraceWithBLOBs ReportTrace) {
		// TODO Auto-generated method stub
		return reportTraceDao.save(ReportTrace);
	}

	@Override
	public ReportTraceWithBLOBs getById(String id) {
		// TODO Auto-generated method stub
		return reportTraceDao.getById(id);
	}

	@Override
	public int delById(String id) {
		// TODO Auto-generated method stub
		
		return reportTraceDao.delById(id);
	}

	@Override
	public List<ReportTraceWithBLOBs> getAll(ReportTraceWithBLOBs Report) {
		// TODO Auto-generated method stub
		return reportTraceDao.getAll(Report);
	}

	@Override
	public int updateById(ReportTraceWithBLOBs Report) {
		// TODO Auto-generated method stub
		return reportTraceDao.updateById(Report);
	}

}
