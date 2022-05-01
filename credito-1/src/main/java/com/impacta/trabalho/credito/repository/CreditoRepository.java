package com.impacta.trabalho.credito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.impacta.trabalho.credito.beans.Credito;

public interface CreditoRepository extends JpaRepository<Credito,Long>{

}
