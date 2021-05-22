package com.its.birthdayParty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.its.birthdayParty.model.Korisnik;
import com.its.birthdayParty.repository.KorisnikRepository;

@Service
public class KorisnikServiceImpl implements KorisnikService{

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Override
	public List<Korisnik> getAllKorisniks() {
		return korisnikRepository.findAll();
	}

	@Override
	public void register(Korisnik korisnik) {
		this.korisnikRepository.save(korisnik);
	}

	@Override
	public Korisnik login(String email, String password) {
		return korisnikRepository.getKorisnikByEmailPassword(email, password);
	}

}
