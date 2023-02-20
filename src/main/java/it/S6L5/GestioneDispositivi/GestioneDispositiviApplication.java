package it.S6L5.GestioneDispositivi;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.S6L5.GestioneDispositivi.config.Beans;
import it.S6L5.GestioneDispositivi.entities.Dispositivo;
import it.S6L5.GestioneDispositivi.entities.Role;
import it.S6L5.GestioneDispositivi.entities.RoleType;
import it.S6L5.GestioneDispositivi.entities.TipoDispositivo;
import it.S6L5.GestioneDispositivi.entities.Utente;
import it.S6L5.GestioneDispositivi.services.DispositivoService;
import it.S6L5.GestioneDispositivi.services.RoleService;
import it.S6L5.GestioneDispositivi.services.UtenteService;

@SpringBootApplication
public class GestioneDispositiviApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(GestioneDispositiviApplication.class, args);
	}

	@Autowired
	private UtenteService uServ;
	@Autowired
	private DispositivoService dServ;
	@Autowired
	private RoleService rServ;
	

	
	//--------------------Main--------------------//
	boolean once = false;
	boolean test = false;
	
	@Override
	public void run(String... args) throws Exception {
		
		if(test == true) {
			creaTest();
		}
		
		if(once == true) {
			System.out.println("Creazione in corso....");
			creaOnce();
		}
	}
	
	public void creaTest() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
		Utente u1 = (Utente)ctx.getBean("utente", "mario", "Mario", "Rossi","mariorossi@gmail.it", "test");
		
		Role r2 = (Role)ctx.getBean("role", RoleType.ROLE_ADMIN);
		Dispositivo l1 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.LAPTOP);
		u1.setRoles(new HashSet<>() {{ add(r2); }});
		
		u1.setDispositivi(new HashSet<>() {{ add(l1); }});
		
		rServ.save(r2);
		dServ.save(l1);
		uServ.save(u1);
		
		((AnnotationConfigApplicationContext)ctx).close();
	}
	
	private void creaOnce() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
		
		Utente u1 = (Utente)ctx.getBean("utente", "mario", "Mario", "Rossi","mariorossi@gmail.it", "test");
		Utente u2 = (Utente)ctx.getBean("utente", "luigi", "Luigi", "Verdi","luigiverdi@gmail.it", "test");
	
		Role r1 = (Role)ctx.getBean("role", RoleType.ROLE_USER);
		Role r2 = (Role)ctx.getBean("role", RoleType.ROLE_ADMIN);

		Dispositivo c1 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.CELLULARE);
		Dispositivo l1 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.LAPTOP);
		Dispositivo t1 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.TABLET);

		u1.setRoles(new HashSet<>() {{
			add(r2);
		}});
		
		u1.setDispositivi(new HashSet<>() {{
			add(l1);
		}});
		
		u2.setRoles(new HashSet<>() {{
			add(r1);
		}});
		
		u2.setDispositivi(new HashSet<>() {{
			add(c1);
		}});
		
		rServ.save(r1);
		rServ.save(r2);
		
		dServ.save(c1);
		dServ.save(l1);
		dServ.save(t1);
		
		uServ.save(u1);
		uServ.save(u2);
		
		((AnnotationConfigApplicationContext)ctx).close();
	}
}
