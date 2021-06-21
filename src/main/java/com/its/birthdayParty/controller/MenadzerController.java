package com.its.birthdayParty.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.its.birthdayParty.model.Agencija;
import com.its.birthdayParty.model.Korisnik;
import com.its.birthdayParty.model.Proslava;
import com.its.birthdayParty.model.Rezervacija;
import com.its.birthdayParty.service.AgencijaService;
import com.its.birthdayParty.service.ProslavaService;
import com.its.birthdayParty.service.RezervacijaService;


@Controller
public class MenadzerController {
	
	@Autowired
	AgencijaService agencijaService;
	
	@Autowired
	RezervacijaService rezervacijaService;
	
	@Autowired
	ProslavaService proslavaService;

	@GetMapping("/menadzer")
	private String showManager(Model model, HttpServletRequest request) {
		Rezervacija rezervacija = new Rezervacija();
		Korisnik menadzer = (Korisnik)request.getSession().getAttribute("user");
		Agencija agencije = agencijaService.getAgencijaByMenadzerId(menadzer.getId());
		Proslava proslava = new Proslava();
		List<Rezervacija> rezervacije = rezervacijaService.getRezervacijeByMenadzerId(menadzer.getId());
		List<Proslava> proslave = null;
		model.addAttribute("rezervacija", rezervacija);
		model.addAttribute("rezervacije", rezervacije);
		model.addAttribute("korisnik", menadzer);
		if(agencije != null) {
			model.addAttribute("agencija", agencije);
			proslave = proslavaService.getProslaveByAgencijaId(agencije.getId());
		} else {
			model.addAttribute(new Agencija());
		}
		model.addAttribute("proslave", proslave);
		model.addAttribute("proslava", proslava);
		model.addAttribute("agencijaService", agencijaService);
		model.addAttribute("proslavaService", proslavaService); 
		return "menadzerPortal";
	}
	
	
	@PostMapping("/changeStatus")
	private String changeStatus(@RequestParam String status, @RequestParam Integer id, @RequestParam String poruka) {
		rezervacijaService.changeStatus(status, id, poruka);
		return "redirect:/menadzer";
	}
	
	@PostMapping("/createProslava")
	private String createProslava(@ModelAttribute("proslava") Proslava proslava,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		proslava.setSlika(fileName);
		this.proslavaService.createProslava(proslava);
		String agencija = agencijaService.getAgencijaById(proslava.getAgencijaId()).getNaziv();
		
		String uploadDir = "src/main/resources/static/images/agencije/" + agencija +  "/";
		Path uploadPath = Paths.get(uploadDir);
		saveImage(uploadPath, fileName, multipartFile);
		
		return "redirect:/menadzer";
	}
	
	@GetMapping("/showProslava/{id}")
	private String showProslava(@PathVariable Integer id, Model model) {
		Proslava proslava = proslavaService.getProslavaById(id);
		Agencija agencija = agencijaService.getAgencijaById(proslava.getAgencijaId());
		
		model.addAttribute("agencija", agencija);
		model.addAttribute("proslava", proslava);
		return "showProslava";
	}
	
	@PostMapping("/updateProslava")
	private String updateProslava(@ModelAttribute("proslava") Proslava proslava,
			@RequestParam("image") MultipartFile multipartFile) throws IOException{
		
		
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		if(fileName != "") {
			proslava.setSlika(fileName);

			String agencija = agencijaService.getAgencijaById(proslavaService.getProslavaById(proslava.getId()).getAgencijaId()).getNaziv();
			String uploadDir = "src/main/resources/static/images/agencije/" + agencija + "/";
			Path uploadPath = Paths.get(uploadDir);
			saveImage(uploadPath, fileName, multipartFile);
		}
		
		proslavaService.update(proslava);
		
		return "redirect:/menadzer";
	}
	
	@GetMapping("/deleteProslava/{id}")
	private String deleteProslava(@PathVariable Integer id) {
		this.proslavaService.delete(id);
		
		return "redirect:/menadzer";
	}
	
	@PostMapping("/createAgencija")
	private String createAgencija(@ModelAttribute("agencija") Agencija agencija,
			@RequestParam("image") MultipartFile multipartFileImg,
			@RequestParam("main") MultipartFile multipartFileBack,
			@RequestParam("menadzer") Integer menadzer, HttpServletRequest request) throws IOException {
		
		String agencijaDir = agencija.getNaziv();
		
		String fileNameImg = StringUtils.cleanPath(multipartFileImg.getOriginalFilename());
		if (fileNameImg != "") {

			agencija.setSlika(fileNameImg);

			String uploadDir = "src/main/resources/static/images/agencije/" + agencijaDir + "/";
			Path uploadPath = Paths.get(uploadDir);
			saveImage(uploadPath, fileNameImg, multipartFileImg);
		}

		String fileNameBack = StringUtils.cleanPath(multipartFileBack.getOriginalFilename());
		if (fileNameBack != "") {

			agencija.setPozadina(fileNameBack);

			String uploadDir = "src/main/resources/static/images/agencije/" + agencijaDir + "/";
			Path uploadPath = Paths.get(uploadDir);
			saveImage(uploadPath, fileNameBack, multipartFileBack);
		}
		
		agencija.setMenadzer(menadzer);
		
		request.getSession().setAttribute("message", "Vaša agencija je u fazi provere, naš tim će ubrzo "
				+ "obaviti registraciju i učiniti je dostupnu korisnicima. Za sve dodatne informacije "
				+ "kontaktirajte nas.");
		agencijaService.createAgencija(agencija);

		return "redirect:/menadzer";
	}
	
	@PostMapping("/updateAgencija")
	private String updateAgencija(@ModelAttribute("agencija") Agencija agencija, 
			@RequestParam("image") MultipartFile multipartFileImg, @RequestParam("main") MultipartFile multipartFileBack) throws IOException {
			
		String agencijaDir = agencijaService.getAgencijaById(agencija.getId()).getNaziv();
		
		String fileNameImg = StringUtils.cleanPath(multipartFileImg.getOriginalFilename());
		if(fileNameImg != "") {
			
			agencija.setSlika(fileNameImg);

			String uploadDir = "src/main/resources/static/images/agencije/" + agencijaDir + "/";
			Path uploadPath = Paths.get(uploadDir);
			saveImage(uploadPath, fileNameImg, multipartFileImg);
		}
		
		String fileNameBack = StringUtils.cleanPath(multipartFileBack.getOriginalFilename());
		if(fileNameBack != "") {
			
			agencija.setPozadina(fileNameBack);

			String uploadDir = "src/main/resources/static/images/agencije/" + agencijaDir + "/";
			Path uploadPath = Paths.get(uploadDir);
			saveImage(uploadPath, fileNameBack, multipartFileBack);
		}
		
		agencijaService.update(agencija);
		
		return "redirect:/menadzer";
	}
	
	private void saveImage(Path uploadPath, String fileName, MultipartFile multipartFile) throws IOException {
		
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
	}
	
	
}
