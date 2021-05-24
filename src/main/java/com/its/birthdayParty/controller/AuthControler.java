package com.its.birthdayParty.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.its.birthdayParty.model.Korisnik;
import com.its.birthdayParty.service.AgencijaService;
import com.its.birthdayParty.service.KorisnikService;

@Controller
public class AuthControler {
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	private AgencijaService agencijaService;
	
	@GetMapping("/")
	public String homePage(Model model, HttpServletRequest request) {
		
		model.addAttribute("agencije", agencijaService.getAllAgencijas());
		
		Korisnik korisnik = new Korisnik();
		model.addAttribute("korisnik",korisnik);
	
		return "index";
	}
	
	@GetMapping("/LogOut")
	public String LogOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/LogIn")
	public String LogIn(@RequestParam String email, @RequestParam String sifra,HttpServletRequest request) {
		Korisnik korisnik = korisnikService.login(email, sifra);
		
		if(korisnik != null) {
		request.getSession().setAttribute("user", korisnik);
			return "redirect:/";
		} else {
			return "redirect:/";
		}
	}
	
	
}
