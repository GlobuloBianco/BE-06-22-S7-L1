package it.S6L5.GestioneDispositivi.controllers;

import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.S6L5.GestioneDispositivi.entities.Utente;

@Controller
@PropertySource("classpath:application.properties")
public class AppController {
	
	@GetMapping("/")
	private String homepage() {
		return "index";
	}
	
	//rotta abilitata a tutti
	@PostMapping("/loggedPage")
	private String loggedPage() {
		return "homepage";
	}
	
	//solo admin
	@GetMapping("/dashboard")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseBody
	public String dashboard() {
		return "Puoi accedere a questa pagina (non decorata ( ͡° ͜ʖ ͡°) ) Solo perché sei un admin. Sentiti potente.";
	}
	
	@GetMapping("/loginPage")
	private String loginPage() {
		return "loginPage";
	}
	
	//prendi dati del utente loggato
	@GetMapping("/profilo")
	@ResponseBody
	public ResponseEntity<Object> profiloUtente(Authentication authentication) {
	    Object currentUser = authentication.getPrincipal();
	    return ResponseEntity.ok(currentUser);
	}
}
