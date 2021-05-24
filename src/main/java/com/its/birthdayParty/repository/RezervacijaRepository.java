package com.its.birthdayParty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.its.birthdayParty.model.Rezervacija;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer>{
	
	@Query(value = "select * from rezervacija r where r.korisnik_id=?1", nativeQuery = true)
	List<Rezervacija> getRezervacijeByKorisnikId(Integer id);
	
}
