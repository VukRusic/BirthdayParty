package com.its.birthdayParty.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenadzerController {

	@GetMapping("/menadzer")
	private String showManager(Model menadzer, HttpServletRequest request) {
		return "menadzerPortal";
	}
}
