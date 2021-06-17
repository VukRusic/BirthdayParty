package com.its.birthdayParty.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.its.birthdayParty.model.Agencija;
import com.its.birthdayParty.model.Korisnik;
import com.its.birthdayParty.model.Proslava;
import com.its.birthdayParty.model.Rezervacija;
import com.its.birthdayParty.service.AgencijaService;
import com.its.birthdayParty.service.KorisnikService;
import com.its.birthdayParty.service.ProslavaService;
import com.its.birthdayParty.service.RezervacijaService;

@Controller
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;

	@Autowired
	RezervacijaService rezervacijaService;
	
	@Autowired
	AgencijaService agencijaService;

	@Autowired
	ProslavaService proslavaService;

	@PostMapping("/registerKorisnik")
	public String addKorisnik(@ModelAttribute("korisnik") Korisnik korisnik, HttpServletRequest request) {
		korisnikService.register(korisnik);
		request.getSession().setAttribute("user", korisnik);
		return "redirect:/";
	}

	@GetMapping("/showKorisnik")
	public String showKorisnik(Model korisnik, HttpServletRequest request) {
		Korisnik user = (Korisnik) request.getSession().getAttribute("user");
		korisnik.addAttribute("korisnik", user);
		String message = (String)request.getSession().getAttribute("message");
		List<Rezervacija> rezervacije = rezervacijaService.getRezervacijeByKorisnikId(user.getId());
		korisnik.addAttribute("rezervacije", rezervacije);
		korisnik.addAttribute("message", message);
		korisnik.addAttribute("agencija", agencijaService);
		korisnik.addAttribute("proslava", proslavaService);
		return "showKorisnik";
	}

	@PostMapping("/updateKorisnik")
	public String updateKorisnik(@ModelAttribute("korisnik") Korisnik korisnik, HttpServletRequest request) {
		korisnikService.update(korisnik);
		
		Korisnik user = korisnikService.getKorisnikById(korisnik.getId());
		request.getSession().setAttribute("user", user);
		
		if(user.getTip().equals("menadzer")) {
			return "redirect:/menadzer";
		}
		
		return "redirect:/showKorisnik";
	}
	
	@GetMapping("/showAgencija/{id}")
	public String showAgencija(@PathVariable Integer id, Model model) {
		List<Proslava> proslave = proslavaService.getProslaveByAgencijaId(id);
		Agencija agencija = agencijaService.getAgencijaById(id);
		Korisnik korisnik = new Korisnik();
		Rezervacija rezervacija = new Rezervacija();
		model.addAttribute("proslave", proslave);
		model.addAttribute("agencija", agencija);
		model.addAttribute("korisnik", korisnik);
		model.addAttribute("rezervacija", rezervacija);
		return "showAgencija";
	}
	
	@PostMapping("/reserve")
	public String makeReservation(@ModelAttribute("rezervacija") Rezervacija rezervacija, HttpServletRequest request) {
		Korisnik user = (Korisnik) request.getSession().getAttribute("user");
		rezervacija.setKorisnik_id(user.getId());
		rezervacija.setStatus("U procesu");
		
		rezervacijaService.makeReservation(rezervacija);
		request.getSession().setAttribute("message", "Rezervacija je uspešno prosleđena i čeka dalje odobrenje od agencije. "
				+ "Možete pratiti njen status na vašem portalu. Za sve dodatne informacije ili promene kontaktiraje nas.");
		return "redirect:/showKorisnik";
	}

}
