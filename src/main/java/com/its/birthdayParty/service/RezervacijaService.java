package com.its.birthdayParty.service;

import java.util.List;

import com.its.birthdayParty.model.Rezervacija;

public interface RezervacijaService {
	List<Rezervacija> getRezervacijeByKorisnikId(Integer id);
	void makeReservation(Rezervacija rezervacija);
	List<Rezervacija> getRezervacijeByMenadzerId(Integer id);
	void changeStatus(String status, Integer id, String poruka);
	Rezervacija getRezervacijaById(Integer id);
	void update(Rezervacija rezervacija);
	void delete(Integer id);
}
