package com.its.birthdayParty.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.birthdayParty.model.Korisnik;
import com.its.birthdayParty.service.AgencijaService;
import com.its.birthdayParty.service.KorisnikService;

@Controller
public class AuthController {
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	private AgencijaService agencijaService;
	
	@GetMapping("/")
	public String homePage(Model model) {
		
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
	
	/*
	 * @PostMapping("/LogIn") public String LogIn(@RequestParam String
	 * email, @RequestParam String sifra, HttpServletRequest request) { Korisnik
	 * korisnik = korisnikService.login(email, sifra);
	 * 
	 * if(korisnik != null) { request.getSession().setAttribute("user", korisnik);
	 * if(korisnik.getTip() == "klijent") { return "redirect:/"; } else { return
	 * "redirect:/"; } } else { return "redirect:/test"; } }
	 */
	
	@GetMapping("/LogIn")
	public String login() {
		return "redirect:/";
	}
	
	@RequestMapping(value="/checkLogin", method = RequestMethod.POST, produces="text/plain")
	@ResponseBody
	public String foo(@RequestParam String email, @RequestParam String sifra,HttpServletRequest request) {
		Korisnik korisnik = korisnikService.login(email, sifra);
		
		if(korisnik != null) {
		request.getSession().setAttribute("user", korisnik);
			if(korisnik.getTip() == "klijent") {
				return "true";
			} else {
				return "true";
			}
		} else {
			return "false";
		}
	}
	
	
}
