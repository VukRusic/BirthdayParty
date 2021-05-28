package com.its.birthdayParty.service;

import java.util.List;

import com.its.birthdayParty.model.Agencija;

public interface AgencijaService {
	List<Agencija> getAllAgencijas();
	Agencija getAgencijaByNaziv(String naziv);
	Agencija getAgencijaById(Integer id);
	String getAgencijaNazivById(Integer id);
}
