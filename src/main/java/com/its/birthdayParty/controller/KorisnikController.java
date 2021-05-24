package com.its.birthdayParty.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.its.birthdayParty.model.Korisnik;
import com.its.birthdayParty.model.Rezervacija;
import com.its.birthdayParty.service.KorisnikService;
import com.its.birthdayParty.service.RezervacijaService;

@Controller
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;

	@Autowired
	RezervacijaService rezervacijaService;

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

		List<Rezervacija> rezervacije = rezervacijaService.getRezervacijeByKorisnikId(user.getId());

		korisnik.addAttribute("rezervacije", rezervacije);

		return "showKorisnik";
	}

	@PostMapping("/updateKorisnik")
	public String updateKorisnik(@ModelAttribute("korisnik") Korisnik korisnik, HttpServletRequest request) {
		korisnikService.update(korisnik);
		Korisnik user = korisnikService.getKorisnikById(korisnik.getId());
		request.getSession().setAttribute("user", user);
		return "redirect:showKorisnik";
	}

	/*
	 * @GetMapping("/") public String homePage(Model model) {
	 * //agencije.addAttribute("agencija",
	 * agencijaService.getAgencijaByNaziv("Colosseum kuglana"));
	 * model.addAttribute("listAgencijes", agencijaService.getAllAgencijas());
	 * 
	 * Korisnik korisnik = new Korisnik(); model.addAttribute("korisnik",korisnik);
	 * 
	 * return "index"; }
	 */

	/*
	 * @GetMapping("/show") public String viewShow(Model model) {
	 * model.addAttribute("listKorisniks",korisnikService.getAllKorisniks());
	 * 
	 * return "showKorisniks"; }
	 * 
	 * @GetMapping("/showRegistration") public String viewReg(Model model) {
	 * Korisnik korisnik = new Korisnik(); model.addAttribute("korisnik", korisnik);
	 * return "registration"; }
	 */

}
