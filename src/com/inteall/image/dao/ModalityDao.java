package com.inteall.image.dao;

import java.util.List;

import com.inteall.image.pojo.Modality;

/**
 * @author 李进刚
 * @date 2018年3月1日 下午5:17:51
 * @version 1.0 
 * @parameter 
 */

public interface ModalityDao {

  List<Modality> getAll();

  List<Modality> getmodalityName();

}
