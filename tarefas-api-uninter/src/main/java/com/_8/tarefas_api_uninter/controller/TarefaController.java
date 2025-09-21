package com._8.tarefas_api_uninter.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._8.tarefas_api_uninter.exception.ResourceNotFoundException;
import com._8.tarefas_api_uninter.model.Tarefa;
import com._8.tarefas_api_uninter.repository.TarefaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

private final TarefaRepository repository;

public TarefaController(TarefaRepository repository) {
this.repository = repository;
}

@PostMapping
public ResponseEntity<Tarefa> criar(@Valid @RequestBody Tarefa tarefa) {
Tarefa salva = repository.save(tarefa);
return ResponseEntity.created(URI.create("/api/tarefas/" + salva.getId())).body(salva);
}

@GetMapping
public List<Tarefa> listarTodas() {
return repository.findAll();
}

@GetMapping("/{id}")
public Tarefa buscarPorId(@PathVariable Long id) {
return repository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Tarefa " + id + " não encontrada"));
}

@PutMapping("/{id}")
public Tarefa atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa dados) {
Tarefa existente = repository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Tarefa " + id + " não encontrada"));
existente.setNome(dados.getNome());
existente.setDataEntrega(dados.getDataEntrega());
existente.setResponsavel(dados.getResponsavel());
return repository.save(existente);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> remover(@PathVariable Long id) {
Tarefa existente = repository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Tarefa " + id + " não encontrada"));
repository.delete(existente);
return ResponseEntity.noContent().build();
}
}