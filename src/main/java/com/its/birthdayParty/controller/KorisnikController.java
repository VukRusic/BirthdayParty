package com.its.birthdayParty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.its.birthdayParty.model.Korisnik;
import com.its.birthdayParty.service.KorisnikService;

@Controller
public class KorisnikController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	@GetMapping("/")
	public String viewShow(Model model) {
		model.addAttribute("listKorisniks",korisnikService.getAllKorisniks());
		return "index";
	}
	
	@GetMapping("/showRegistration")
	public String viewReg(Model model) {
		Korisnik korisnik = new Korisnik();
		model.addAttribute("korisnik", korisnik);
		return "registration";
	}
	
	@PostMapping("/addKorisnik")
	public String addKorisnik(@ModelAttribute("korisnik") Korisnik korisnik) {
		korisnikService.register(korisnik);
		return "redirect:/";
	}
	
	
}
