package com.desafio.controle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desafio.controle.model.Creditos;

public interface CreditoRepository extends JpaRepository<Creditos, Integer> {


}
