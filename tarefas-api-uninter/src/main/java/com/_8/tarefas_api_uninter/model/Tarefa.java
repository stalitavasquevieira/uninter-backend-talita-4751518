package com._8.tarefas_api_uninter.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tarefas")
public class Tarefa {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message = "nome é obrigatório")
@Column(nullable = false)
private String nome;

@NotNull(message = "dataEntrega é obrigatória")
@JsonFormat(pattern = "yyyy-MM-dd")
@Column(name = "data_entrega", nullable = false)
private LocalDate dataEntrega;

@NotBlank(message = "responsavel é obrigatório")
@Column(nullable = false)
private String responsavel;

public Tarefa() {}

public Tarefa(String nome, LocalDate dataEntrega, String responsavel) {
this.nome = nome;
this.dataEntrega = dataEntrega;
this.responsavel = responsavel;
}

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getNome() { return nome; }
public void setNome(String nome) { this.nome = nome; }
public LocalDate getDataEntrega() { return dataEntrega; }
public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }
public String getResponsavel() { return responsavel; }
public void setResponsavel(String responsavel) { this.responsavel = responsavel; }
}