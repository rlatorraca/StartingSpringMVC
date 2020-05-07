package com.rlsp.faturaSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rlsp.faturaSpring.model.Titulo;

public interface TitulosRepository extends JpaRepository<Titulo, Long> {

	List<Titulo> findByDescricaoContaining(String descricao);

}
