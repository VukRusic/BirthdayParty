package com.its.birthdayParty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "proslava")
@Table(name="proslava")
public class Proslava {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name="agencija_id")
	private Integer agencijaId;
	
	@Column(name="naziv_proslave")
	private String nazivProslave;
	
	@Column(name="opis_proslave")
	private String opisProslave;
	
	@Column(name="cena")
	private Float cena;
	
	@Column(name="slika")
	private String slika;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Integer getAgencijaId() {
		return agencijaId;
	}

	public void setAgencijaId(Integer agencijaId) {
		this.agencijaId = agencijaId;
	}

	public String getNazivProslave() {
		return nazivProslave;
	}

	public void setNazivProslave(String nazivProslave) {
		this.nazivProslave = nazivProslave;
	}

	public String getOpisProslave() {
		return opisProslave;
	}

	public void setOpisProslave(String opisProslave) {
		this.opisProslave = opisProslave;
	}

	public Float getCena() {
		return cena;
	}

	public void setCena(Float cena) {
		this.cena = cena;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slka) {
		this.slika = slka;
	}
	
}
