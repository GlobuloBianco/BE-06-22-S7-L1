package it.S6L5.GestioneDispositivi.config;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.S6L5.GestioneDispositivi.entities.Role;
import it.S6L5.GestioneDispositivi.entities.Utente;
import it.S6L5.GestioneDispositivi.services.DispositivoService;
import it.S6L5.GestioneDispositivi.services.UtenteService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UtenteService uServ;
	@Autowired
	DispositivoService dServ;

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/")
				.permitAll()
				.anyRequest()
					.authenticated()
			.and()
				.formLogin()
				 .loginPage("/loginPage")
		           .permitAll()
					.successForwardUrl("/loggedPage")
			.and()
				.logout()
			.and()
				.csrf()
					.disable();
	}
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		Optional<Utente> authUserObj = uServ.getById(1);
		Utente authUser = authUserObj.get();
		String role = "USER";
		Set<Role> roles = authUser.getRoles();
		
		for( Role r : roles ) {
			if( r.getType().toString().contains("ADMIN") ) {
				role = "ADMIN";
				break;
			}
		}
		
		auth.inMemoryAuthentication()
			.withUser( authUser.getUsername() ).password( passwordEncoder().encode( authUser.getPassword() ) )
			.roles(role);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
} 
