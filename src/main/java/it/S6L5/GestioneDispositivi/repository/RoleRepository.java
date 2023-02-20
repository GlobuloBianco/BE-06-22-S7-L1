package it.S6L5.GestioneDispositivi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.S6L5.GestioneDispositivi.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}