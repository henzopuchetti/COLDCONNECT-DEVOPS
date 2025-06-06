package com.fiap.ColdConnect.repository;

import com.fiap.ColdConnect.model.AlertaFrio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;  // IMPORT

import java.util.List;

public interface AlertaFrioRepository extends JpaRepository<AlertaFrio, Long>, JpaSpecificationExecutor<AlertaFrio> {
    List<AlertaFrio> findByRegiaoIgnoreCase(String regiao);
}