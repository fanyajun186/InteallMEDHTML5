package com.inteall.image.service;

import java.util.List;

import com.inteall.image.pojo.Modality;

/**
 * @author 李进刚
 * @date 2018年3月1日 下午5:08:20
 * @version 1.0 
 * @parameter 
 */

public interface ModalityService {

  List<Modality> getAll();

  List<Modality> getmodalityName();

}
