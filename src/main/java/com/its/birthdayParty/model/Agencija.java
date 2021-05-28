package com.its.birthdayParty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity(name = "agencija")
@Table(name = "agencija")
public class Agencija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "naziv")
	private String naziv;
	
	@Column(name = "lokacija")
	private String lokacija;
	
	@Column(name = "opis")
	private String opis;
	
	@Column(name = "slika")
	private String slika;
	
	@Column(name = "menadzer")
	private Integer menadzer;
	
	@Column(name = "pocetakRV")
	private Integer pocetakRV;
	
	@Column(name = "krajRV")
	private Integer krajRV;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "telefon")
	private String telefon;
	
	@Column(name = "email")
	private String email;

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public Integer getMenadzer() {
		return menadzer;
	}

	public void setMenadzer(Integer menadzer) {
		this.menadzer = menadzer;
	}

	public Integer getPocetakRV() {
		return pocetakRV;
	}

	public void setPocetakRV(Integer pocetakRV) {
		this.pocetakRV = pocetakRV;
	}

	public Integer getKrajRV() {
		return krajRV;
	}

	public void setKrajRV(Integer krajRV) {
		this.krajRV = krajRV;
	}
	
}
