package com.fiap.ColdConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fiap.ColdConnect.model.LeituraTemperatura;

public interface LeituraTemperaturaRepository extends JpaRepository<LeituraTemperatura, Long>, JpaSpecificationExecutor<LeituraTemperatura> {
}

