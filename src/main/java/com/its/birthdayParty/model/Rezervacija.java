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
	private Integer proslavaId;
	
	@Column(name="korisnik_id")
	private Integer korisnikId;
	
	@Column(name="ukupna_cena")
	private Float ukupnaCena;
	
	@Column(name="datum")
	private Date datum;
	
	@Column(name = "vreme")
	private String vreme;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getProslavaId() {
		return proslavaId;
	}

	public void setProslavaId(Integer proslavaId) {
		this.proslavaId = proslavaId;
	}

	public Integer getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Integer korisnikId) {
		this.korisnikId = korisnikId;
	}

	public Float getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(Float ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
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
	
}
