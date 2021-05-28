package com.its.birthdayParty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.its.birthdayParty.model.Agencija;
import com.its.birthdayParty.repository.AgencijaRepository;

@Service
public class AgencijaServiceImpl implements AgencijaService{
	
	@Autowired
	AgencijaRepository agencijaRepository;
	
	@Override
	public List<Agencija> getAllAgencijas() {
		return agencijaRepository.findAll();
	}

	@Override
	public Agencija getAgencijaByNaziv(String naziv) {
		return agencijaRepository.getAgencija(naziv);
	}

	@Override
	public Agencija getAgencijaById(Integer id) {
		return agencijaRepository.getOne(id);
	}

	@Override
	public String getAgencijaNazivById(Integer id) {
		return agencijaRepository.getAgencijaNaziv(id);
	}

}
