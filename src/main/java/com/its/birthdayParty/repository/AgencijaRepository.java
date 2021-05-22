package com.its.birthdayParty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.its.birthdayParty.model.Agencija;

@Repository
public interface AgencijaRepository extends JpaRepository<Agencija, Integer>{

	@Query(value = "Select * from agencija a where a.naziv=?1", nativeQuery = true)
	Agencija getAgencija(String naziv);
	
}
