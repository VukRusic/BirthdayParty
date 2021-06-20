package com.its.birthdayParty.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity(name = "rezervacija")
@Table(name = "rezervacija")
public class Rezervacija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name="proslava_id")
	private Integer proslava_id;
	
	@Column(name="korisnik_id")
	private Integer korisnik_id;
	
	@Column(name="ukupna_cena")
	private Float ukupna_cena;
	
	@Column(name="bonus_poeni")
	private Integer bonus_poeni;
	
	@Column(name="datum")
	private Date datum;
	
	@Column(name = "vreme")
	private String vreme;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "poruka")
	private String poruka;

	public String getPoruka() {
		return poruka;
	}

	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return Id;
	}

	public Integer getProslava_id() {
		return proslava_id;
	}

	public void setProslava_id(Integer proslava_id) {
		this.proslava_id = proslava_id;
	}

	public Integer getKorisnik_id() {
		return korisnik_id;
	}

	public void setKorisnik_id(Integer korisnik_id) {
		this.korisnik_id = korisnik_id;
	}

	public Float getUkupna_cena() {
		return ukupna_cena;
	}

	public void setUkupna_cena(Float ukupna_cena) {
		this.ukupna_cena = ukupna_cena;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getBonus_poeni() {
		return bonus_poeni;
	}

	public void setBonus_poeni(Integer bonus_poeni) {
		this.bonus_poeni = bonus_poeni;
	}

	
	
}
