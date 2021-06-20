package com.its.birthdayParty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.its.birthdayParty.model.Proslava;
import com.its.birthdayParty.repository.ProslavaRepository;

@Service
public class ProslavaServiceImpl implements ProslavaService{
	
	@Autowired
	ProslavaRepository proslavaRepository;

	@Override
	public List<Proslava> getProslaveByAgencijaId(Integer id) {
		
		List<Proslava> proslave = proslavaRepository.getProslaveByAgencijaId(id);
		
		return proslave;
	}

	@Override
	public String getProslavaNazivById(Integer id) {
		return proslavaRepository.getProslavaNazivById(id);
	}

	@Override
	public void createProslava(Proslava proslava) {
		this.proslavaRepository.save(proslava);
	}

	@Override
	public Proslava getProslavaById(Integer id) {
		return proslavaRepository.getOne(id);
	}

	@Override
	public void update(Proslava proslava) {
		Proslava updateProslava = proslavaRepository.getOne(proslava.getId());
		updateProslava.setCena(proslava.getCena());
		updateProslava.setNazivProslave(proslava.getNazivProslave());
		updateProslava.setOpisProslave(proslava.getOpisProslave());
		updateProslava.setPoeni(proslava.getPoeni());
		if(proslava.getSlika() != null) {
		updateProslava.setSlika(proslava.getSlika());
		}
		this.proslavaRepository.save(updateProslava);
	}

	@Override
	public void delete(Integer id) {
		this.proslavaRepository.deleteById(id);
	}

	@Override
	public Integer getCenaByRezervacijaId(Integer id) {
		return proslavaRepository.getCenaByRezervacijaId(id);
	}
	
}
