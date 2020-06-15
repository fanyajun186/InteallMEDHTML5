package com.inteall.image.service;

import java.util.HashMap;
import java.util.List;

import com.inteall.image.pojo.PersonToPerson;

/**
 * @author 韩明君
 * @date 2018年8月22日 下午5:27:17
 * @version 1.0 
 * @parameter 
 */

public interface PersonToPersonService {

    int getSaveSharePerson(PersonToPerson personToPerson);

    int getMedicalCount(HashMap<String, Object> queryMap);

    List<PersonToPerson> getRecord(HashMap<String, Object> queryMap);

}
