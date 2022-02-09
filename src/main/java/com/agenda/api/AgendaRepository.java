package com.agenda.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>{

	List<Agenda> findByNome(String nome);
	
	List<Agenda> findByTelefone(String telefone);
}
