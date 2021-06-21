package com.its.birthdayParty.service;

import java.util.List;

import com.its.birthdayParty.model.Agencija;

public interface AgencijaService {
	List<Agencija> getAllAgencijas();
	List<Agencija> getNewAgencijas();

	Agencija getAgencijaByNaziv(String naziv);
	Agencija getAgencijaById(Integer id);
	String getAgencijaNazivById(Integer id);
	Agencija getAgencijaByMenadzerId(Integer id);
	void update(Agencija agencija);
	void createAgencija(Agencija agencija);
	void registerAgencija(Integer id);
}
