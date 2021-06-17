package com.its.birthdayParty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.its.birthdayParty.model.Korisnik;
import com.its.birthdayParty.model.Rezervacija;
import com.its.birthdayParty.repository.RezervacijaRepository;

@Service
public class RezervacijaServiceImpl implements RezervacijaService{

	@Autowired
	RezervacijaRepository rezervacijaRepository;
	
	@Autowired
	KorisnikService korisnikService;

	@Override
	public List<Rezervacija> getRezervacijeByKorisnikId(Integer id) {
		List<Rezervacija> rezervacije = rezervacijaRepository.getRezervacijeByKorisnikId(id); 
		return rezervacije;
	}

	@Override
	public void makeReservation(Rezervacija rezervacija) {
		this.rezervacijaRepository.save(rezervacija);
	}

	@Override
	public List<Rezervacija> getRezervacijeByMenadzerId(Integer id) {
		return rezervacijaRepository.getRezervacijeByMenadzerId(id);
	}

	@Override
	public void changeStatus(String status, Integer id) {
		Rezervacija rezervacija = rezervacijaRepository.getOne(id);
		Korisnik korisnik = korisnikService.getKorisnikByRezervacijaId(id);
		
		if(status.equals("Potvrđena") && !rezervacija.getStatus().equals("Potvrđena")) {
			korisnik.setPoeni(korisnik.getPoeni() + rezervacija.getBonus_poeni());
			korisnikService.update(korisnik);
		} else if(status.equals("Odbijena") && !rezervacija.getStatus().equals("Odbijena")) {
			korisnik.setPoeni(korisnik.getPoeni() - rezervacija.getBonus_poeni());
			korisnikService.update(korisnik);
		}
		
		this.rezervacijaRepository.changeStatus(status, id);
	}
	
}
