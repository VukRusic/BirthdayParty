package com.its.birthdayParty.service;

import java.util.List;

import com.its.birthdayParty.model.Agencija;

public interface AgencijaService {
	List<Agencija> getAllAgencijas();
	List<Agencija> getNewAgencijas();
	List<Agencija> getAgencijeByNaziv(String naziv);
	List<String> getLokacije();

	Agencija getAgencijaByNaziv(String naziv);
	Agencija getAgencijaById(Integer id);
	String getAgencijaNazivById(Integer id);
	Agencija getAgencijaByMenadzerId(Integer id);
	void update(Agencija agencija);
	void createAgencija(Agencija agencija);
	void registerAgencija(Integer id);
	void changeStatus(String status, String poruka, Integer id);
}
