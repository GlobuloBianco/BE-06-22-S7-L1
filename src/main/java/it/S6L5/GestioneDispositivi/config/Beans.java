package it.S6L5.GestioneDispositivi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.S6L5.GestioneDispositivi.entities.DispDispositivo;
import it.S6L5.GestioneDispositivi.entities.Dispositivo;
import it.S6L5.GestioneDispositivi.entities.Role;
import it.S6L5.GestioneDispositivi.entities.RoleType;
import it.S6L5.GestioneDispositivi.entities.TipoDispositivo;
import it.S6L5.GestioneDispositivi.entities.Utente;

@Configuration
public class Beans {
	
    @Autowired
    private PasswordEncoder encoder;

    @Bean
    public PasswordEncoder WebpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Scope("prototype")
    public Utente utente(String username, String nome, String cognome, String email, String password) {
        String hashedPassword = encoder.encode(password);
        return Utente.builder()
            .username(username)
            .nome(nome)
            .cognome(cognome)
            .email(email)
            .password(hashedPassword)
            .build();
    }
	
	@Bean
	@Scope("prototype")
	public Dispositivo dispositivo(TipoDispositivo tipo) {
		return Dispositivo.builder()
				.tipo(tipo)
				.disp(DispDispositivo.DISPONIBILE)
				.build();
	}
	
	@Bean
	@Scope("prototype")
	public Role role(RoleType rt) {
		return Role.builder()
				.type(rt)
				.build();
	}
}