package com.fiap.ColdConnect.repository;

import com.fiap.ColdConnect.model.AcaoEmergencial;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AcaoEmergencialRepository extends JpaRepository<AcaoEmergencial, Long>, JpaSpecificationExecutor<AcaoEmergencial> {
}