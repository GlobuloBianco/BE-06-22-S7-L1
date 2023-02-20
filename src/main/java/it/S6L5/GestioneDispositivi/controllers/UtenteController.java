package it.S6L5.GestioneDispositivi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import it.S6L5.GestioneDispositivi.entities.Utente;
import it.S6L5.GestioneDispositivi.services.UtenteService;

@Controller
@RequestMapping("/utenti")
public class UtenteController {
	
	@Autowired
	private UtenteService serv;
	
	@GetMapping("")
	public ResponseEntity<List<Utente>> getAll() {
		List<Utente> utenti = serv.getAll();
		
		if( utenti.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(utenti, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable Integer id) {
		Optional<Utente> personObj = serv.getById(id);
		ResponseEntity<Object> check = checkExists(personObj);
		if( check != null ) return check;
		
		return new ResponseEntity<>(personObj.get(), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody Utente u) {
		Utente utente = serv.save(u);
		
		return new ResponseEntity<>(utente, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(
			@PathVariable Integer id,
			@RequestBody Utente _utente) {
		
		Optional<Utente> utenteObj = serv.getById(id);
		
		ResponseEntity<Object> check = checkExists(utenteObj);
		if( check != null ) return check;
		
		Utente utente = utenteObj.get();
		utente.setUsername( _utente.getUsername() );
		utente.setNome( _utente.getNome() );
		utente.setCognome( _utente.getCognome() );
		utente.setEmail( _utente.getEmail() );
		utente.setPassword( _utente.getPassword() );
		serv.save(utente);
		
		return new ResponseEntity<>(utente, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		Optional<Utente> personObj = serv.getById(id);
		ResponseEntity<Object> check = checkExists(personObj);
		if( check != null ) return check;
		
		serv.delete(personObj.get());
		return new ResponseEntity<>(
				String.format("Utente con id %d cancellato correttamente!", id), HttpStatus.OK);
	}
	
	
	private ResponseEntity<Object> checkExists(Optional<Utente> obj) {
		if( !obj.isPresent() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return null;
	}
	
}
