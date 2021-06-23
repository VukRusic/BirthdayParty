package com.its.birthdayParty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.its.birthdayParty.model.Agencija;

@Repository
public interface AgencijaRepository extends JpaRepository<Agencija, Integer>{

	@Query(value = "Select * from agencija a where a.naziv=?1", nativeQuery = true)
	Agencija getAgencija(String naziv);
	
	@Query(value = "Select naziv from agencija a join proslava p on a.id=p.agencija_id where p.id=?1", nativeQuery = true)
	String getAgencijaNaziv(Integer id);
	
	@Query(value = "Select * from agencija a where a.naziv like %?1%", nativeQuery = true)
	List<Agencija> getAgencijeByNaziv(String naziv);
	
	@Query(value = "Select * from agencija a where a.menadzer=?1", nativeQuery = true)
	Agencija getAgencijaByMenadzerId(Integer id);
	
	@Query(value = "Select * from agencija a where a.status='u procesu registracije'", nativeQuery = true)
	List<Agencija> getNewAgencijas();
	
	@Query(value = "Select distinct lokacija from agencija", nativeQuery = true)
	List<String> getLokacije();
	
	@Transactional
	@Modifying
	@Query(value = "update agencija a set a.status=?1, a.poruka=?2 where a.id=?3", nativeQuery = true)
	void changeStatus(String status, String poruka, Integer id);
	
}
