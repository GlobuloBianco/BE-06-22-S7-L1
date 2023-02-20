package it.S6L5.GestioneDispositivi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.S6L5.GestioneDispositivi.entities.Dispositivo;
import it.S6L5.GestioneDispositivi.repository.DispositivoRepository;

@Service
public class DispositivoService {
	
	@Autowired
	private DispositivoRepository repo;

	public Dispositivo save(Dispositivo d) {
		return repo.save(d);
	}
	
	public Optional<Dispositivo> getById(int id){
		return repo.findById(id);
	}

	public List<Dispositivo> getAll() {
		return repo.findAll();
	}
	
	public void delete(Dispositivo u) {
		repo.delete(u);
	}
}
