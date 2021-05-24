package com.its.birthdayParty.service;

import java.util.List;

import com.its.birthdayParty.model.Proslava;

public interface ProslavaService {
	
	List<Proslava> getProslaveByAgencijaId(Integer id);
	
}
