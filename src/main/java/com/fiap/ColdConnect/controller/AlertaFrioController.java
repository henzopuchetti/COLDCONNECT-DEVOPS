package com.fiap.ColdConnect.controller;

import com.fiap.ColdConnect.model.AlertaFrio;
import com.fiap.ColdConnect.service.AlertaFrioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaFrioController {

    private final AlertaFrioService service;

@GetMapping
public ResponseEntity<List<AlertaFrio>> listarPorRegiao(@RequestParam(required = false) String regiao) {
    List<AlertaFrio> alertas;

    if (regiao != null && !regiao.isEmpty()) {
        alertas = service.buscarPorRegiao(regiao);
    } else {
        alertas = service.listarTodos();
    }

    return ResponseEntity.ok(alertas);
}

    @GetMapping("/{id}")
    public ResponseEntity<AlertaFrio> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlertaFrio> salvar(@RequestBody @Valid AlertaFrio alerta) {
        AlertaFrio salvo = service.salvar(alerta);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaFrio> atualizar(@PathVariable Long id, @RequestBody @Valid AlertaFrio alerta) {
        try {
            AlertaFrio atualizado = service.atualizar(id, alerta);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
