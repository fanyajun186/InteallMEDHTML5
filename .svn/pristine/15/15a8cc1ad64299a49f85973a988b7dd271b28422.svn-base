package com.inteall.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.ReportDao;
import com.inteall.image.pojo.ReportWithBLOBs;
import com.inteall.image.service.ReportService;

/** 
 * @author 韩明君  
 * @date 创建时间：2016年7月9日 上午10:21:52 
 * @version 1.0 
 * @parameter  
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService {
	@Resource
	private ReportDao reportDao;

	@Override
	public int save(ReportWithBLOBs Report) {
		// TODO Auto-generated method stub
		return reportDao.save(Report);
	}

	@Override
	public ReportWithBLOBs getById(String id) {
		// TODO Auto-generated method stub
		return reportDao.getById(id);
	}

	@Override
	public int delById(String id) {
		// TODO Auto-generated method stub
		
		return reportDao.delById(id);
	}

	@Override
	public List<ReportWithBLOBs> getAll(ReportWithBLOBs Report) {
		// TODO Auto-generated method stub
		return reportDao.getAll(Report);
	}

	@Override
	public int updateById(ReportWithBLOBs Report) {
		// TODO Auto-generated method stub
		return reportDao.updateById(Report);
	}
	
	//查询电子签名路径
	@Override
	public String selqmUrl(String baogaoName) {
		return reportDao.selqmUrl(baogaoName);
	}

}
