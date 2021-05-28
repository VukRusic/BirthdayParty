package com.its.birthdayParty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.its.birthdayParty.model.Proslava;

@Repository
public interface ProslavaRepository extends JpaRepository<Proslava, Integer>{
	
	@Query(value = "select * from proslava p where p.agencija_id=?1", nativeQuery = true)
	List<Proslava> getProslaveByAgencijaId(Integer id);
	
	@Query(value = "select naziv_proslave from proslava p where p.id=?1", nativeQuery = true)
	String getProslavaNazivById(Integer id);
}
