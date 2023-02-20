package it.S6L5.GestioneDispositivi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.S6L5.GestioneDispositivi.entities.Role;
import it.S6L5.GestioneDispositivi.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repo;
	
	public Role save(Role r) {
		return repo.save(r);
	}

}