package com.its.birthdayParty.service;

import java.util.List;

import com.its.birthdayParty.model.Korisnik;

public interface KorisnikService {
	List<Korisnik> getAllKorisniks();
	void register(Korisnik korisnik);
	Korisnik login(String email, String password);
	void update(Korisnik korisnik);
	Korisnik getKorisnikById(Integer id);
}
