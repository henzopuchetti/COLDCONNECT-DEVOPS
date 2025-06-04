package com.fiap.ColdConnect.controller;

import com.fiap.ColdConnect.dto.AcaoEmergencialDTO;
import com.fiap.ColdConnect.service.AcaoEmergencialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acoes")
@RequiredArgsConstructor
public class AcaoEmergencialController {

    private final AcaoEmergencialService service;

    @PostMapping
    public ResponseEntity<AcaoEmergencialDTO> criar(@RequestBody @Valid AcaoEmergencialDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<AcaoEmergencialDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoEmergencialDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoEmergencialDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AcaoEmergencialDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
