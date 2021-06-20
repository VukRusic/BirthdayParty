package com.its.birthdayParty.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.its.birthdayParty.model.Rezervacija;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer>{
	
	@Query(value = "select * from rezervacija r where r.korisnik_id=?1", nativeQuery = true)
	List<Rezervacija> getRezervacijeByKorisnikId(Integer id);
	
	@Query(value = "select * from rezervacija r join proslava p on r.proslava_id=p.id "
			+ "join agencija a on p.agencija_id=a.id where a.menadzer=?1", nativeQuery = true)
	List<Rezervacija> getRezervacijeByMenadzerId(Integer id);
	
	@Transactional
	@Modifying
	@Query(value = "update rezervacija r set r.status=?1, r.poruka=?2 where r.id=?3", nativeQuery = true)
	void changeStatus(String status, String poruka, Integer id);
	
}
