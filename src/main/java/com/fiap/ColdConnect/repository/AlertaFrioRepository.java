package com.fiap.ColdConnect.repository;

import com.fiap.ColdConnect.model.AlertaFrio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaFrioRepository extends JpaRepository<AlertaFrio, Long> {
    List<AlertaFrio> findByRegiaoIgnoreCase(String regiao);
}
