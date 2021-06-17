package com.its.birthdayParty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.its.birthdayParty.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer>{
	
	@Query(value = "Select * from korisnik k where k.email=?1 and k.sifra=?2", nativeQuery = true)
	Korisnik getKorisnikByEmailPassword(String email, String password);
	
	@Query(value = "Select * from korisnik k join rezervacija r on k.Id = r.korisnik_id where r.Id=?1", nativeQuery = true)
	Korisnik getKorisnikByRezervacijaId(Integer id);
}
