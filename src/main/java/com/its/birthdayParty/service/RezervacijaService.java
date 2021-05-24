package com.its.birthdayParty.service;

import java.util.List;

import com.its.birthdayParty.model.Rezervacija;

public interface RezervacijaService {
	List<Rezervacija> getRezervacijeByKorisnikId(Integer id);
}
