package it.S6L5.GestioneDispositivi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.S6L5.GestioneDispositivi.entities.Utente;
import it.S6L5.GestioneDispositivi.repository.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository repo;

	public Utente save(Utente u) {
		return repo.save(u);
	}
	
	public Optional<Utente> getById(int i) {
		return repo.findById(i);
	}

	public List<Utente> getAll() {
		return repo.findAll();
	}

	public void delete(Utente u) {
		repo.delete(u);
	}

	public Utente getByUsername(String username) {
		return repo.findByUsername(username);
	}

}
