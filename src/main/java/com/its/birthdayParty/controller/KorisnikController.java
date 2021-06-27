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
import org.springframework.web.bind.annotation.RequestParam;

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
		
		korisnik.addAttribute("korisnik", korisnikService.getKorisnikById(user.getId()));
		String message = (String)request.getSession().getAttribute("message");
		Rezervacija rezervacija = new Rezervacija();
		List<Rezervacija> rezervacije = rezervacijaService.getRezervacijeByKorisnikId(user.getId());
		korisnik.addAttribute("rezervacije", rezervacije);
		korisnik.addAttribute("rezervacija", rezervacija);
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
		} else if(user.getTip().equals("admin")) {
			return "redirect:/admin";
		}
		
		return "redirect:/showKorisnik";
	}
	
	@GetMapping("/showAllAgencije")
	public String showAllAgencije(Model model, HttpServletRequest request) {
		List<Agencija> agencije = agencijaService.getAllAgencijas();
		List<String> lokacije = agencijaService.getLokacije();
		model.addAttribute("korisnik", new Korisnik());
		model.addAttribute("agencije", agencije);
		model.addAttribute("lokacije", lokacije);
		return "showAllAgencije";
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
	
	@PostMapping("/search")
	public String search(@RequestParam String naziv, Model model) {
		if(naziv != "") {
			List<Agencija> agencije = agencijaService.getAgencijeByNaziv(naziv);
			model.addAttribute("korisnik", new Korisnik());
			model.addAttribute("agencije", agencije);
			return "showAllAgencije";
		} else {
			return "redirect:/showAllAgencije";
		}
	}
	
	@PostMapping("/reserve")
	public String makeReservation(@ModelAttribute("rezervacija") Rezervacija rezervacija, HttpServletRequest request) {
		Korisnik user = (Korisnik) request.getSession().getAttribute("user");
		rezervacija.setKorisnik_id(user.getId());
		
		rezervacijaService.makeReservation(rezervacija);
		request.getSession().setAttribute("message", "Rezervacija je uspešno prosleđena i čeka dalje odobrenje od agencije. "
				+ "Možete pratiti njen status na vašem portalu. Za sve dodatne informacije ili promene kontaktiraje nas.");
		return "redirect:/showKorisnik";
	}
	
	
	@PostMapping("/updateReservacija")
	public String updateReservation(@ModelAttribute("rezervacija") Rezervacija rezervacija, HttpServletRequest request) {
		rezervacijaService.update(rezervacija);
		
		return "redirect:/showKorisnik";
	}
	
	@GetMapping("/deleteRezervacija/{id}")
	public String deleteRezervacija(@PathVariable Integer id) {
		rezervacijaService.delete(id);
		
		return "redirect:/showKorisnik";
	}
	

}
