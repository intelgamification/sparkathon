package com.gamification.web.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamification.web.dao.AhtStreamDataDAO;
import com.gamification.web.model.AhtStreamData;

@Service
public class AhtStreamDataServiceImpl implements AhtStreamDataService {

	@Autowired
	private AhtStreamDataDAO ahtStreamDataDAO;
	
	@Autowired
	private Mapper dozerMapper;
	
	public AhtStreamData getAhtStream() {
		com.gamification.web.entity.AhtStreamData ahtStreamData=ahtStreamDataDAO.getAhtStream();
		AhtStreamData data = dozerMapper.map(ahtStreamData, AhtStreamData.class);
		return data;
	}

}
