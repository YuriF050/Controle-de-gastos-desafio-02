package com.desafio.controle.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.controle.model.Debitos;



public interface DebitoRepository extends JpaRepository<Debitos, Integer> {

    
}