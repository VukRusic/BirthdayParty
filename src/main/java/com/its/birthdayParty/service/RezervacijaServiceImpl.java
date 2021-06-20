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
	public void changeStatus(String status, Integer id, String poruka) {
		Rezervacija rezervacija = rezervacijaRepository.getOne(id);
		Korisnik korisnik = korisnikService.getKorisnikByRezervacijaId(id);
		
		if(status.equals("Potvrđena") && !rezervacija.getStatus().equals("Potvrđena")) {
			korisnik.setPoeni(korisnik.getPoeni() + rezervacija.getBonus_poeni());
			korisnikService.update(korisnik);
			poruka = "potvrđena";	
		} else if((status.equals("Odbijena") || status.equals("U procesu provere")) && !rezervacija.getStatus().equals("U procesu provere") && !rezervacija.getStatus().equals("Odbijena")) {
			korisnik.setPoeni(korisnik.getPoeni() - rezervacija.getBonus_poeni());
			korisnikService.update(korisnik);
		} 
		
		this.rezervacijaRepository.changeStatus(status, poruka, id);
	}

	@Override
	public Rezervacija getRezervacijaById(Integer id) {
		return this.rezervacijaRepository.getOne(id);
	}

	@Override
	public void update(Rezervacija rezervacija) {
		Korisnik korisnik = korisnikService.getKorisnikByRezervacijaId(rezervacija.getId());
		Rezervacija rezervacijaUpdate = rezervacijaRepository.getOne(rezervacija.getId());

		if(rezervacijaRepository.getOne(rezervacija.getId()).getStatus().equals("Potvrđena")){
			korisnik.setPoeni(korisnik.getPoeni() - rezervacijaRepository.getOne(rezervacija.getId()).getBonus_poeni());
			korisnikService.update(korisnik);
		}

		rezervacijaUpdate.setBonus_poeni(rezervacija.getBonus_poeni());
		rezervacijaUpdate.setDatum(rezervacija.getDatum());
		rezervacijaUpdate.setPoruka("Vaša rezervacija je u procesu provere, ubrzo ćete dobiti odgovor. Hvala na strpljenju!");
		rezervacijaUpdate.setStatus("U procesu provere");
		rezervacijaUpdate.setUkupna_cena(rezervacija.getUkupna_cena());
		

		rezervacijaRepository.save(rezervacijaUpdate);
	}

	@Override
	public void delete(Integer id) {
		Rezervacija rezervacija = rezervacijaRepository.getOne(id);
		Korisnik korisnik = korisnikService.getKorisnikByRezervacijaId(id);
		if(rezervacija.getStatus().equals("Potvrđena")) {
			korisnik.setPoeni(korisnik.getPoeni() - rezervacija.getBonus_poeni());
			korisnikService.update(korisnik);
		}
		this.rezervacijaRepository.deleteById(id);
	}

}
