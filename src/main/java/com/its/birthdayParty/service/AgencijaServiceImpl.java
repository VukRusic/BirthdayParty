package com.its.birthdayParty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.its.birthdayParty.model.Agencija;
import com.its.birthdayParty.repository.AgencijaRepository;

@Service
public class AgencijaServiceImpl implements AgencijaService{
	
	@Autowired
	AgencijaRepository agencijaRepository;
	
	@Override
	public List<Agencija> getAllAgencijas() {
		return agencijaRepository.findAll();
	}

	@Override
	public Agencija getAgencijaByNaziv(String naziv) {
		return agencijaRepository.getAgencija(naziv);
	}

	@Override
	public Agencija getAgencijaById(Integer id) {
		return agencijaRepository.getOne(id);
	}

	@Override
	public String getAgencijaNazivById(Integer id) {
		return agencijaRepository.getAgencijaNaziv(id);
	}

	@Override
	public Agencija getAgencijaByMenadzerId(Integer id) {
		return agencijaRepository.getAgencijaByMenadzerId(id);
	}

	@Override
	public void update(Agencija agencija) {
		Agencija agencijaUpdate = agencijaRepository.getOne(agencija.getId());
		
		agencijaUpdate.setNaziv(agencija.getNaziv());
		agencijaUpdate.setEmail(agencija.getEmail());
		agencijaUpdate.setKrajRV(agencija.getKrajRV());
		agencijaUpdate.setPocetakRV(agencija.getPocetakRV());
		agencijaUpdate.setLokacija(agencija.getLokacija());
		agencijaUpdate.setTelefon(agencija.getTelefon());
		agencijaUpdate.setOpis(agencija.getOpis());
		
		if(agencija.getSlika() != null) {
			agencijaUpdate.setSlika(agencija.getSlika());
		}
		
		if(agencija.getPozadina() != null) {
			agencijaUpdate.setPozadina(agencija.getPozadina());
		}
		
		this.agencijaRepository.save(agencijaUpdate);
	}

	@Override
	public void createAgencija(Agencija agencija) {
		this.agencijaRepository.save(agencija);
	}

}
