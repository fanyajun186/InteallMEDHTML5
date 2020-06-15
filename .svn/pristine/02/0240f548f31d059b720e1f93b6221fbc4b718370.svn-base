package com.inteall.image.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inteall.image.dao.PersonToPersonDao;
import com.inteall.image.pojo.PersonToPerson;
import com.inteall.image.service.PersonToPersonService;

/**
 * @author 韩明君
 * @date 2018年8月22日 下午5:34:18
 * @version 1.0 
 * @parameter 
 */
@Service("personToPersonService")
public class PersonToPersonServiceImpl implements PersonToPersonService {
    @Resource
    private PersonToPersonDao personToPersonDao;
    @Override
    public int getSaveSharePerson(PersonToPerson personToPerson) {
        // TODO Auto-generated method stub
        return personToPersonDao.getSaveSharePerson(personToPerson);
    }
    @Override
    public int getMedicalCount(HashMap<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return personToPersonDao.getMedicalCount(queryMap);
    }
    @Override
    public List<PersonToPerson> getRecord(HashMap<String, Object> queryMap) {
        // TODO Auto-generated method stub
        return personToPersonDao.getRecord(queryMap);
    }

}
