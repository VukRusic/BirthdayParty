package com.its.birthdayParty.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.its.birthdayParty.model.Agencija;
import com.its.birthdayParty.model.Korisnik;
import com.its.birthdayParty.service.AgencijaService;
import com.its.birthdayParty.service.KorisnikService;

@Controller
public class AdminController {
	
	@Autowired
	AgencijaService agencijaService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@GetMapping("/admin")
	public String showAdmin(Model model, HttpServletRequest request) {
		Korisnik admin = (Korisnik)request.getSession().getAttribute("user");
		List<Agencija> agencije = agencijaService.getAllAgencijas();
		List<Agencija> newAgencije = agencijaService.getNewAgencijas();
		List<Korisnik> allKorisniks = korisnikService.getAllKorisniks();
		model.addAttribute("korisnici", allKorisniks);
		model.addAttribute("korisnik", admin);
		model.addAttribute("agencije", agencije);
		model.addAttribute("agencija", new Agencija());
		model.addAttribute("newAgencije", newAgencije);
		return "adminPortal";
	}
	
	
	@GetMapping("/registerAgencija/{id}")
	public String registerAgencija(@PathVariable Integer id) {
		agencijaService.registerAgencija(id);
		
		return "redirect:/admin";
	}
	
}
