package com.agenda.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/agenda")
public class AgendaController {

	@Autowired
	private AgendaRepository repository;
	
	@PostMapping
	public void salvar(@RequestBody Agenda agenda) {
		repository.save(agenda);
	}
	
	@GetMapping
	public List<Agenda> listar() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Agenda buscar(@PathVariable Long id) {
		return repository.findById(id).get();
	}
	
	@GetMapping("/nome")
	public List<Agenda> buscarPorNome(@RequestParam String nome){
		return repository.findByNome(nome);
	}
	
	@GetMapping("/telefone")
	public List<Agenda> buscarPorTelefone(@RequestParam String telefone){
		return repository.findByTelefone(telefone);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
