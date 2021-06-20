package com.its.birthdayParty.service;

import java.util.List;

import com.its.birthdayParty.model.Proslava;

public interface ProslavaService {
	
	List<Proslava> getProslaveByAgencijaId(Integer id);
	String getProslavaNazivById(Integer id);
	Integer getCenaByRezervacijaId(Integer id);
	void createProslava(Proslava proslava);
	Proslava getProslavaById(Integer id);
	void update(Proslava proslava);
	void delete(Integer id);
}
