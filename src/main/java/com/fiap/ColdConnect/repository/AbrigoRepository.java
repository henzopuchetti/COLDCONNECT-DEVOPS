package com.fiap.ColdConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fiap.ColdConnect.model.Abrigo;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long>, JpaSpecificationExecutor<Abrigo> {
}
