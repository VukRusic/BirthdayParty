package com.its.birthdayParty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.its.birthdayParty.model.Rezervacija;
import com.its.birthdayParty.repository.RezervacijaRepository;

@Service
public class RezervacijaServiceImpl implements RezervacijaService{

	@Autowired
	RezervacijaRepository rezervacijaRepository;

	@Override
	public List<Rezervacija> getRezervacijeByKorisnikId(Integer id) {
		List<Rezervacija> rezervacije = rezervacijaRepository.getRezervacijeByKorisnikId(id); 
		return rezervacije;
	}
	
}
