package com.rlsp.faturaSpring;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rlsp.faturaSpring.model.Titulo;

public interface TitulosRepository extends JpaRepository<Titulo, Long> {

}
