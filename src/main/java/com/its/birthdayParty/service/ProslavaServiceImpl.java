package com.its.birthdayParty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.its.birthdayParty.model.Proslava;
import com.its.birthdayParty.repository.ProslavaRepository;

@Service
public class ProslavaServiceImpl implements ProslavaService{
	
	@Autowired
	ProslavaRepository proslavaRepository;

	@Override
	public List<Proslava> getProslaveByAgencijaId(Integer id) {
		
		List<Proslava> proslave = proslavaRepository.getProslaveByAgencijaId(id);
		
		return proslave;
	}
	
}
