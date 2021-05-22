package com.its.birthdayParty.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.its.birthdayParty.model.Korisnik;
import com.its.birthdayParty.service.KorisnikService;

@Controller
public class KorisnikController {
	
	@Autowired
	KorisnikService korisnikService;
	
	@PostMapping("/registerKorisnik")
	public String addKorisnik(@ModelAttribute("korisnik") Korisnik korisnik, HttpServletRequest request) {
		korisnikService.register(korisnik);
		request.getSession().setAttribute("user", korisnik);
		return "redirect:/";
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
