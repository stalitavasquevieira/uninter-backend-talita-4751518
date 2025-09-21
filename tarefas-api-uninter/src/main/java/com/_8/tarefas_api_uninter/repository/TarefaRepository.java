package com._8.tarefas_api_uninter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com._8.tarefas_api_uninter.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}